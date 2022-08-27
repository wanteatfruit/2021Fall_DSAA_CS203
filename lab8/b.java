import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] heap = new int[2000000];
        for (int i = 1; i < n + 1; i++) {
            heap[i] = in.nextInt();
        }
        int cur = 1;
        int max = 1;
        // max=judge(cur,n+1,heap,max);
        while (2 * cur < n + 1) {
            int left = 2 * cur;
            int right = 2 * cur + 1;
            if (heap[left] > heap[cur] && heap[right] > heap[cur]) {
                max = 0;
            } else if (heap[left] < heap[cur] && heap[right] < heap[cur]) {
                max = 1;
            } else {
                max = 2;
                break;
            }
            cur = left;
        }
        while (2 * cur + 1 < n + 1) {
            int left = 2 * cur;
            int right = 2 * cur + 1;
            if (heap[left] > heap[cur] && heap[right] > heap[cur]) {
                max = 0;
            } else if (heap[left] < heap[cur] && heap[right] < heap[cur]) {
                max = 1;
            } else {
                max = 2;
                break;
            }
            cur = right;
        }

        switch (max) {
            case 0:
                out.print("Min");
                break;
            case 1:
                out.print("Max");
                break;
            default:
                out.print("Neither");
        }
        out.close();

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