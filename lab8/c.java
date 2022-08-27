import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] heapK = new long[5000001];

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n + 1];
        long[] b = new long[m + 1];

        long[] res = new long[k + 1];
        for (int i = 1; i < n + 1; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 1; i < m + 1; i++) {
            b[i] = in.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);
        int count = 0;

        for (int i = 1; i < n + 1; i++) {
            count++;
            heapK[count] = a[i] * b[1];
            insert(count, heapK);
        }

        int size = count;
        int curRow = 1;
        int curCol = 1;
        count = 0;
        int[] colIndices = new int[n + 1];
        Arrays.fill(colIndices, 1);
        while (count < k) {
            count++;
            res[count] = heapK[1];
            curRow = 1;
            // put in next num
            // n+1 rows, m+1 cols
            boolean noInsert = false;
            for (int i = 1; i < n + 1; i++) {
                int bIndex = colIndices[i];
                long cur = a[i] * b[bIndex];
                if (cur == heapK[1]) {
                    if (bIndex + 1 < m + 1) {
                        curRow = i;
                        curCol = bIndex + 1;
                        colIndices[i]++;
                        break;
                    } else {
                        noInsert = true;
                        break;
                    }
                }
            }

            delete(1, size, heapK);
            if (noInsert && size >= 1) {
                size--;

            } else if (size < 1) {

            } else {
                heapK[size] = a[curRow] * b[curCol];
                insert(size, heapK);
            }
        }
        for (int i = 1; i < res.length; i++) {
            out.print(res[i] + " ");
        }

        out.close();
    }
    // static void build()

    static void insert(int cur, long[] heap) {
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

    static void delete(int cur, int size, long[] heap) {
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

    static void adjustDown(int cur, long[] heap, int size) {
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
