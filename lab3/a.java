//Java Fast IO
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();

        for (int s = 0; s < T; s++) {
            // QReader in = new QReader();

            int n = in.nextInt();
            int m = in.nextInt();
            long[] a = new long[n];
            long[] b = new long[m];

            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
            }
            for (int j = 0; j < m; j++) {
                b[j] = in.nextLong();
            }
            long pair = findPairs(a, b);
            long[] result;
            result = merge(a, b);
            out.println(pair);
            for (long value : result) {
                out.print(value + " ");
            }
            out.println("");

        }

        out.close();
    }

    public static long findPairs(long[] a, long[] b) {

        int pointer1 = 0;
        int pointer2 = 0;
        long temp1;
        long temp2 = 0;
        long count = 0;
        while (pointer1 < a.length) {
            if (pointer2 < b.length && a[pointer1] > b[pointer2]) { // legal
                pointer2++;
                temp2 = pointer2;

            } else {
                count = count + temp2;
                pointer1++;
            }
        }

        return count;
    }

    public static long[] merge(long[] left, long[] right) {
        long[] result = new long[left.length + right.length];

        int pointer1 = 0;
        int pointer2 = 0;
        int pointer3 = 0;

        while (pointer3 < result.length) {
            if (pointer2 == right.length) {
                result[pointer3] = left[pointer1];
                pointer1++;
            } else if (pointer1 < left.length && left[pointer1] <= right[pointer2]) {
                result[pointer3] = left[pointer1];
                pointer1++;
            } else {
                result[pointer3] = right[pointer2];
                pointer2++;
            }
            pointer3++;
        }

        return result;

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