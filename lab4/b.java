import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] res = new int[n];
        if (n == 1) {
            out.print("1");
            out.close();

        } else if (m == 1) {
            for (int i = 0; i < n; i++) {
                res[i] = i + 1;
            }
            for (int i = 0; i < n; i++) {
                out.print(res[i] + " ");
            }
            out.close();
        } else {
            Node head = new Node(1);
            Node cur = head;
            for (int i = 2; i <= n - 1; i++) {
                cur.next = new Node(i);
                cur = cur.next;
            }

            cur.next = new Node(n);
            cur = head;
            int pos = 0;
            int tempPos = 0;
            int roundCount = 0;

            while (Node.length(head) > 0) {
                cur = head;

                int len = Node.length(cur);
                for (int i = 1; i <= len; i += m) {

                    if (i == 1) { // remove head
                        res[pos] = cur.val;
                        tempPos++;
                        head = cur.next;
                        cur.next = null;
                        cur = head;
                        pos++;

                    } else {
                        int count = 1;

                        while (count < m - 1) {
                            count++;
                            cur = cur.next;
                        }
                        res[pos] = cur.next.val;
                        cur.next = cur.next.next;
                        cur = cur.next;
                        pos++;
                    }
                }
                roundCount++;
            }

            for (int re : res) {
                out.print(re + " ");
            }
            out.close();

        }

    }
}

class Node {
    int val;
    Node next;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node next) {

        this.next = next;
        this.val = val;

    }

    boolean hasNext(Node A) {
        return A != null;
    }

    static int length(Node a) {
        int l = 0;
        while (a != null) {
            l++;
            a = a.next;
        }
        return l;
    }

    static int remove(Node a, int index) {
        int ind = 1;
        int res = -1;
        while (ind + 1 < index) {
            a = a.next;
            ind++;
        }
        if (a.next.next != null) {
            a.next = a.next.next;
            res = a.next.next.val;
        } else {
            res = a.next.val;
            a.next = null;
        }
        return res;
    }

    static int get(Node a, int index) {
        int ind = 1;
        Node temp = a;
        while (ind < index) {
            temp = temp.next;
            ind++;
        }
        return temp.val;
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
