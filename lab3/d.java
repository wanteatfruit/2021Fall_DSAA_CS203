import java.util.Arrays;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        QReader in = new QReader();

        QWriter out = new QWriter();
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        long[] sorted = mergeSort(a);

        for (int i = 1; i < a.length - 1; i = i + 2) {
            long temp = sorted[i];
            sorted[i] = sorted[i + 1];
            sorted[i + 1] = temp;
        }

        for (int i = 0; i < n; i++) {
            out.print(sorted[i] + " ");
        }
        out.close();
    }

    public static long[] mergeSort(long[] a) {
        if (a.length < 2) {
            return a;
        } else {
            int mid = a.length / 2;
            long[] left = Arrays.copyOfRange(a, 0, mid);
            long[] right = Arrays.copyOfRange(a, mid, a.length);
            return merge(mergeSort(left), mergeSort(right));
        }
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