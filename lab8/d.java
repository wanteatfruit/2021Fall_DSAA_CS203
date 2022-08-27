import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long[] heap = new long[1000000];
    static long sum = 0;
    static long total = 0;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            heap[i + 1] = a;
            insert(i + 1);
        }
        if (n == 1) {
            out.print(0);
            out.close();
            System.exit(0);
        }
        long count = 0;
        int size = n;
        while (count < n - 1) {
            count++;
            long min1 = heap[1];
            delete(1, size);
            size -= 1;
            long min2 = heap[1];
            delete(1, size);
            size -= 1;
            sum = min1 + min2;
            size += 1;
            heap[size] = sum;
            insert(size);
            total += sum;
        }
        out.print(total);
        out.close();

    }

    static void insert(int cur) {
        int par = cur / 2;
        while (par >= 1) {
            if (heap[cur] < heap[par]) { // swap
                long temp = heap[cur];
                heap[cur] = heap[par];
                heap[par] = temp;
                cur = par;
                par = cur / 2;
            } else
                break;
        }
    }

    static void delete(int cur, int size) {
        heap[1] = heap[size];
        int child = cur * 2;
        // size--;
        while (child < size) {
            if ((heap[child] >= heap[cur] && heap[child + 1] >= heap[cur]) || heap[child] == 0) {
                break;
            } else {
                int key;
                long tmp;
                if (heap[child] > heap[child + 1]) {
                    tmp = heap[child + 1];
                    key = child + 1;
                } else {
                    tmp = heap[child];
                    key = child;
                }
                heap[key] = heap[cur];
                heap[cur] = tmp;
                cur = key;
                child = cur * 2;
            }
        }
        heap[size] = 0;

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
