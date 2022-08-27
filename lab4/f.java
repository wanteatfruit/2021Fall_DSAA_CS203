import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    final private static int double_size = 2202;
    final private static int size = 1100;
    private static final BlockNode head = new BlockNode();

    // final int size=1449;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s = in.next();
        int n = in.nextInt();
        BlockNode cur = head;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[][] ops = new int[100002][2];
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        // init block
        for (int i = 0; i < len; ++i) {
            if (i % (size - 1) == 0) { // each node index at 1498
                cur.next = new BlockNode();
                cur = cur.next;

            }
            cur.add(chars[i]);
        }
        int opCount = 0;

        for (int i = 0; i < n; ++i) {
            int operation = in.nextInt();

            switch (operation) {
                case 1:
                    char c = in.next().charAt(0);
                    int p = in.nextInt();
                    insert(c, p, len);
                    for (int j = 0; j < left.size(); j++) {
                        if (p >= left.get(j) && p <= right.get(j)) {
                            right.add(right.get(j) + 1);
                            right.set(j, p - 1);
                            left.add(p + 1);
                        }
                        if (p < left.get(j)) {
                            left.set(j, left.get(i) + 1);
                            right.set(j, right.get(i) + 1);
                        }
                    }
                    break;
                case 2:
                    int pos = in.nextInt();
                    char target = find(pos);
                    for (int j = 0; j < left.size(); j++) {
                        if (pos >= left.get(j) && pos <= right.get(j)) {
                            target = (char) ('a' + 'z' - target);
                        }
                    }

                    out.println(target);
                    break;
                case 3:
                    int l = in.nextInt();
                    int r = in.nextInt();
                    left.add(l);
                    right.add(r);
                    ops[opCount][0] = l;
                    ops[opCount][1] = r;
                    // transform(l,r);
                    break;
            }
            opCount++;
        }
        out.close();

    }

    public static void insert(char c, int p, int s) {
        BlockNode cur = head;
        if (p >= s) {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.add(c);
        } else {
            cur = cur.next;
            int total = 0;
            int countInNode;
            while (total + cur.index < p) { // search all nodes
                total += cur.index;
                cur = cur.next;
            }
            countInNode = cur.index;
            int targetPos = p - 1 - total;// add char here, push other to i+1
            for (int i = countInNode; i >= targetPos; i--) {
                cur.chars[i + 1] = cur.chars[i];
            }
            cur.chars[targetPos] = c;
            ++cur.index;
        }
        split(cur);
    }

    public static char find(int p) {
        BlockNode cur = head; // each node have 1449, head is dummy
        cur = cur.next;
        int total = 0;
        while (total + cur.index < p) { // search all nodes
            total += cur.index;
            cur = cur.next;
        }
        return cur.chars[p - 1 - total];
    }

    public static void split(BlockNode node) { // split at 2899
        if (node.index >= double_size) {
            BlockNode split = new BlockNode();
            for (int i = size; i <= node.index; ++i) {
                split.add(node.chars[i]);
            }
            split.index = node.index;
            node.index = size - 1;
            split.next = node.next;
            node.next = split;
        }
    }

    public static void transform(int l, int r) { // maintain index at bottom
        BlockNode cur = head; // each node have 1449, head is dummy
        cur = cur.next;
        int total = 0;
        int step = r - l + 1;
        while (total + cur.index < l) { // search all nodes to find the pos
            cur = cur.next;
            total += cur.index;
        }
        int curNumsLeft = cur.index - (l - 1 - total) + 1;
        if (step <= curNumsLeft) {
            int curIndex = cur.index;
            for (int i = l - 1 - total; i < step; ++i) {
                cur.chars[i] = (char) ('a' + 'z' - cur.chars[i]);
            }
        }

        else { // need to next nodes
               // go thru cur first
            for (int i = l - 1 - total; i < cur.index; ++i) {
                cur.chars[i] = (char) ('a' + 'z' - cur.chars[i]);
            }
            // steps left needed in next nodes
            step -= curNumsLeft;
            cur = cur.next;

            while (step > 0) {

                int currentSize = cur.index;
                if (step <= currentSize) {
                    for (int i = 0; i < step; i++) {
                        cur.chars[i] = (char) ('a' + 'z' - cur.chars[i]);
                    }
                } else {
                    for (int i = 0; i < currentSize; ++i) {
                        cur.chars[i] = (char) ('a' + 'z' - cur.chars[i]);
                    }
                    step = step - currentSize;
                    cur = cur.next;
                }
            }
        }

    }
}

class BlockNode {
    BlockNode next;
    char[] chars = new char[2831];
    // final int size=1449;
    int index = 0;

    public BlockNode() {
        this.index = 0;
    }

    public void add(char c) {
        this.chars[index] = c;
        ++index;
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
