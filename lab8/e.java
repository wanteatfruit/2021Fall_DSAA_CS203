import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long[] smallHeap = new long[5000001];
    static long[] bigHeap = new long[5000001];
    static int smallSize = 0;
    static int bigSize = 0;
    static int smallInsert = 0;
    static int bigInsert = 0;
    static int index = 1;

    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        long[] a = new long[n + 1];
        for (int i = 1; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        int k = 1;

        long[] res = new long[n + 1];

        smallSize = 0;
        smallInsert = 1;
        bigInsert = 1;

        while (index < n + 1) {
            k = (index + 1) / 2;

            // insert
            if (a[index] <= bigHeap[1]) {
                bigHeap[bigInsert] = a[index];
                insertB(bigInsert, bigHeap);
                bigSize++;
                bigInsert++;
            } else {
                smallHeap[smallInsert] = a[index];
                insertS(smallInsert, smallHeap);
                smallSize++;
                smallInsert++;
            }

            maintain(smallHeap, bigHeap, k);

            res[index] = bigHeap[1];

            index++;

        }

        for (int i = 1; i < n + 1; i++) {
            out.print(res[i] + " ");
        }
        out.close();

    }

    static void maintain(long[] smallHeap, long[] bigHeap, int k) {
        while (bigSize < k) {
            long cur = smallHeap[1];
            delete(1, smallSize, smallHeap);
            smallSize--;
            smallInsert--;
            bigHeap[bigInsert] = cur;
            insertB(bigInsert, bigHeap);
            bigSize++;
            bigInsert++;
        }
        while (bigSize > k) {
            long cur = bigHeap[1];
            deleteB(1, bigSize, bigHeap);
            bigSize--;
            bigInsert--;
            smallHeap[smallInsert] = cur;
            insertS(smallInsert, smallHeap);
            smallSize++;
            smallInsert++;
        }

    }

    static void insertS(int cur, long[] heap) {
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

    static void insertB(int cur, long[] heap) {
        int par = cur / 2;
        while (par >= 1) {
            if (heap[cur] > heap[par]) { // swap
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

    static void deleteB(int cur, int size, long[] heap) {
        heap[1] = heap[size];
        int child = cur * 2;
        // size--;
        while (child < size) {
            if ((heap[child] <= heap[cur] && heap[child + 1] <= heap[cur]) || heap[child] == 0) {
                break;
            } else {
                int key;
                long tmp;
                if (heap[child] < heap[child + 1]) {
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