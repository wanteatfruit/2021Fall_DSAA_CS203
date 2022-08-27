import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static Node rootBun = new Node(-1, null);
    static Node rootG = new Node(-1, null);

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int m = in.nextInt();
        long sum = 0;

        for (int i = 0; i < m; i++) {
            int type = in.nextInt(); // 1 is girl, 0 is bunny
            int val = in.nextInt();
            if (rootG.key == -1 && rootBun.key == -1) { // both empty
                if (type == 1) {
                    rootG.key = val;
                } else
                    rootBun.key = val;
            } else if (rootG.key == -1) { // one is empty
                if (type == 1) { // girl,need to find the smallest
                    Node toDeletePre = findPre(rootBun, val);
                    Node toDeleteSuc = findSuccessor(rootBun, val);
                    if (toDeleteSuc == null && toDeletePre == null) {

                    } else if (toDeletePre == null) {
                        sum = sum + Math.abs(toDeleteSuc.key - val);
                        delete(toDeleteSuc, 0);
                    } else if (toDeleteSuc == null) {
                        sum = sum + Math.abs(toDeletePre.key - val);
                        delete(toDeletePre, 0);
                    } else if (Math.abs(toDeletePre.key - val) <= Math.abs(toDeleteSuc.key - val)) {
                        sum = sum + Math.abs(toDeletePre.key - val);
                        delete(toDeletePre, 0);
                    } else {
                        sum = sum + Math.abs(toDeleteSuc.key - val);
                        delete(toDeleteSuc, 0);
                    }
                } else { // bun, need to insert
                    insert(rootBun, val, 0);
                }
            } else if (rootBun.key == -1) {
                if (type == 1) {
                    insert(rootG, val, 1);
                } else {// what if can't find pre or suc? method will return itself (root) Wrong! e.g.
                        // 3-5. 4 target, have pre and suc.
                    Node toDeletePre = findPre(rootG, val);
                    Node toDeleteSuc = findSuccessor(rootG, val);
                    if (toDeleteSuc == null && toDeletePre == null) {

                    } else if (toDeletePre == null) {
                        sum = sum + Math.abs(toDeleteSuc.key - val);
                        delete(toDeleteSuc, 1);
                    } else if (toDeleteSuc == null) {
                        sum = sum + Math.abs(toDeletePre.key - val);
                        delete(toDeletePre, 1);
                    } else if (Math.abs(toDeletePre.key - val) <= Math.abs(toDeleteSuc.key - val)) {
                        sum = sum + Math.abs(toDeletePre.key - val);
                        delete(toDeletePre, 1);
                    } else {
                        sum = sum + Math.abs(toDeleteSuc.key - val);
                        delete(toDeleteSuc, 1);
                    }

                }
            }
        }

        out.print(sum);
        out.close();
    }

    public static void findHeight(Node root) {
        if (root.left == null && root.right == null) {
            root.height = 0;
        } else if (root.left == null) {
            root.height = root.right.height + 1;
        } else if (root.right == null) {
            root.height = root.left.height + 1;
        } else {
            root.height = Math.max(root.left.height, root.right.height) + 1;
        }
    }

    public static void setBalance(Node root) { // find balance for one node
        if (root == null) {
            return;
        }
        findHeight(root);
        root.balance = height(root.left) - height(root.right);
    }

    public static void insert(Node cur, int key, int type) {
        if (cur == null) {
            cur = new Node(key, null);
        } else {
            // didn't consider duplicate keys
            if (key > cur.key) {
                if (cur.right == null) {
                    cur.right = new Node(key, cur);
                    // cur.right.height = 0;
                    balanceTree(cur, type);
                    return;
                }
                insert(cur.right, key, type);

            } else {
                if (cur.left == null) {
                    cur.left = new Node(key, cur);
                    // cur.left.height = 0;
                    balanceTree(cur, type);
                    return;
                }
                insert(cur.left, key, type);
            }
        }
    }

    public static void delete(Node cur, int type) {
        if (cur.left == null && cur.right == null) { // case 1: cur is leaf
            Node tmp = cur.parent;
            if (tmp == null) { // only one leaf in tree.
                cur.key = -1; // only suitable in lab8f
                return;
            }
            if (tmp.key > cur.key) { // left child
                tmp.left = null;
            } else {
                tmp.right = null;
            }
            cur.parent = null;
            balanceTree(tmp, type);
        } else if (cur.right == null) { // case 3: cur has no right tree
            Node tmp = cur.left;
            Node par = cur.parent;
            if (par == null) {// means it's root
                cur.left = null;
                cur = tmp;
                cur.parent = null;
                balanceTree(cur, type);
                return;
            }
            if (par.left == cur) {
                par.left = tmp;
            }
            if (par.right == cur) {
                par.right = tmp;
            }
            tmp.parent = par;
            cur.parent = null;
            balanceTree(tmp, type);
        } else { // case 2:has right tree
            Node v = findSuccessor(cur.right, cur.key);
            cur.key = v.key;
            if (v.left == null && v.right == null) { // case 2.1
                Node par = v.parent;
                if (par.left == v) {
                    par.left = null;
                } else {
                    par.right = null;
                }
                v.parent = null;
                balanceTree(par, type);
            } else { // case 2.2: v has right child
                Node tmp = v.right;
                Node par = v.parent;
                if (par.left == v) {
                    par.left = tmp;
                } else {
                    par.right = tmp;
                }
                tmp.parent = par;
                v.parent = null;
                balanceTree(tmp, type);
            }
        }
    }

    public static Node findSuccessor(Node cur, int target) {
        if (cur == null) {
            return null;
        }
        Node tmp = cur; // if only itself is successor
        int diff = 2000000000;
        Node res = null;
        while (cur != null) {
            if (cur.key - target >= 0 && diff >= cur.key - target) {
                diff = cur.key - target;
                res = cur;
            }
            if (target < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return res;
    }

    public static Node findPre(Node cur, int target) {
        if (cur == null) {
            return null;
        }

        int diff = 2000000000;
        Node res = null;
        while (cur != null) {
            if (target - cur.key >= 0 && diff >= target - cur.key) {
                diff = target - cur.key;
                res = cur;
            }
            if (target <= cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return res;
    }

    public static int height(Node root) { // avoid null pointers
        if (root != null) {
            return root.height;
        } else
            return -1;
    }

    public static void balanceTree(Node cur, int type) { // balance =left-right
        setBalance(cur);
        if (cur.balance == 2) { // left higher than right
            if (height(cur.left.left) >= height(cur.left.right)) { // LL case
                cur = rotateRight(cur);
            } else
                cur = rotateLR(cur); // LR case
        } else if (cur.balance == -2) {
            if (height(cur.right.right) >= height(cur.right.left)) {
                cur = rotateLeft(cur);
            } else
                cur = rotateRL(cur);
        }
        if (cur.parent != null) { // recursively balance parents
            balanceTree(cur.parent, type);
        } else if (type == 1) { // 关键
            rootG = cur;
        } else
            rootBun = cur;
    }

    public static Node rotateRight(Node a) {// left-left case in lecture slides
        Node b = a.left;

        b.parent = a.parent;
        a.left = b.right;
        b.right = a;
        if (a.left != null) {
            a.left.parent = a;
        }
        a.parent = b;

        if (b.parent != null) { // b must be smaller than a, so after rotate b take a's place
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
        setBalance(a);
        setBalance(b);

        return b;

    }

    public static Node rotateLeft(Node a) {// right-right case
        Node b = a.right;
        b.parent = a.parent;

        a.right = b.left;
        b.left = a;
        if (a.right != null)
            a.right.parent = a;
        a.parent = b;
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
        setBalance(a);
        setBalance(b);

        return b;
    }

    public static Node rotateLR(Node a) { // LR case in slides
        a.left = rotateLeft(a.left); // a.left=c
        return rotateRight(a); // return c, c takes place of a
    }

    public static Node rotateRL(Node a) { //
        a.right = rotateRight(a.right);
        return rotateLeft(a);
    }

}

// needs to create array of nodes to quickly locate nodes with target key, didnt
// consider duplicate key

class Node {
    int key;
    int balance;
    int height;
    Node left;
    Node right;
    Node parent;

    Node(int key, Node parent) {
        this.key = key;
        this.parent = parent;
    }

}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
