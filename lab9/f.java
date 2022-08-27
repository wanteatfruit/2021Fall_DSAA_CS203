import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static fnode[] nodes = new fnode[300000];
    static long res = 0;
    static final long mod = (long) (1e9 + 7);

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            // each case
            res = 0;
            for (int j = 0; j < nodes.length; j++) {
                nodes[j] = new fnode();
                nodes[j].ind = j;
            }
            int n = in.nextInt(); // nodes
            int m = in.nextInt(); // edges
            long[] a = new long[n + 1];
            long[] b = new long[n + 1];
            for (int j = 1; j < n + 1; j++) {
                int ai = in.nextInt();
                int bi = in.nextInt();
                a[j] = ai;
                b[j] = bi;
                nodes[j].a = ai;
                nodes[j].b = bi;
            }
            for (int j = 0; j < m; j++) {
                int u = in.nextInt();
                int v = in.nextInt(); // edge from u to v
                nodes[v].degree++;
                nodes[u].edges.add(nodes[v]);
                nodes[v].fathers.add(nodes[u]);
            }

            // topo
            ArrayList<fnode> sorted = new ArrayList<>(); // sorted
            for (int j = 1; j < n + 1; j++) {
                sorted.add(nodes[j]);
            }
            long[] f = new long[n + 1];

            for (int j = 0; j < sorted.size(); j++) {
                fnode cur = sorted.get(j);
                ArrayList<Long> fFathers = new ArrayList<>(); // f(father)
                ArrayList<Long> wFathers = new ArrayList<>(); // weights(a)
                for (int k = 0; k < cur.fathers.size(); k++) {
                    fnode father = cur.fathers.get(k);
                    fFathers.add(f[(int) father.ind]);
                    wFathers.add(father.a);
                }
                long fCur = 0;
                for (int k = 0; k < cur.fathers.size(); k++) {
                    fCur = (long) ((fCur + wFathers.get(k)) % (1e9 + 7));
                    fCur = (fCur + fFathers.get(k)) % mod;
                }
                f[(int) cur.ind] = fCur;
            }

            // sum for f(j)*bj
            long sum = 0;
            for (int j = 1; j < n + 1; j++) {
                long mul = (f[j] * nodes[j].b) % mod;
                sum = (sum + mul) % mod;
            }
            out.println(sum);

            //
        } // end of each case
        out.close();
    }

    static class fnode {
        long a;
        long b;
        long ind;
        long degree = 0;
        ArrayList<fnode> edges = new ArrayList<>();
        ArrayList<fnode> fathers = new ArrayList<>();
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