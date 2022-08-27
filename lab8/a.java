import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] heap = new int[6000000];
        int[] res = new int[n + 1];
        // Heap[] nodes=new Heap[2000000000];
        heap[1] = in.nextInt();
        res[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            int a = in.nextInt();
            int cur = i;
            heap[i] = a;
            int count = 0;
            int par = i / 2;
            while (par >= 1) {
                if (heap[cur] > heap[par]) { // swap
                    int temp = heap[cur];
                    heap[cur] = heap[par];
                    heap[par] = temp;
                    cur = par;
                    par = cur / 2;
                    count++;

                } else
                    break;
            }
            res[i] = count;
        }
        for (int i = 1; i < res.length; i++) {
            out.print(res[i] + " ");
        }
        out.close();
    }

    static void insert(Heap node) {
        if (node.parent == null) {
            return;
        }
    }
}

class Heap {
    Heap left;
    Heap right;
    Heap parent;
    int val;

    public Heap(int val) {
        this.val = val;
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