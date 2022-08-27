import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        DoubleLink[] nodes = new DoubleLink[n + 1];
        DoubleLink[] tail = new DoubleLink[n + 1];

        for (int i = 0; i < n + 1; i++) {
            nodes[i] = new DoubleLink(i);
            tail[i] = nodes[i];
        }

        for (int i = 0; i < n; i++) {
            int temp = in.nextInt();
        }

        for (int i = 0; i < p; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            DoubleLink headA = nodes[a];
            while (nodes[a].next != null) {
                nodes[a] = nodes[a].next;
            }
            nodes[a].next = nodes[b];
            nodes[b].pre = nodes[a];
            nodes[a] = headA;
        }
        for (int i = 1; i < n + 1; i++) {
            if (tail[i].pre == null && hasQ(q, tail[i])) { // a head oflink
                out.print(findQ(q, tail[i]) + " ");
            }
        }
        out.close();
    }

    public static boolean hasQ(int q, DoubleLink doubleLinks) {
        int count = 1;
        while (count < q) {
            if (doubleLinks.next == null) {
                return false;
            }
            doubleLinks = doubleLinks.next;
            count++;
        }
        return true;
    }

    public static int findQ(int q, DoubleLink doubleLink) {
        int count = 1;

        while (count < q) {
            doubleLink = doubleLink.next;
            count++;
        }
        return doubleLink.val;
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

class DoubleLink {
    int val;
    DoubleLink next;
    DoubleLink pre;

    public DoubleLink(int val) {
        this.val = val;
    }
}
