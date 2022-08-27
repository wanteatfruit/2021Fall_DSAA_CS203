import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[][] nodes = new int[810][810];// store nodes, start from
    static int[][] ind = new int[810][810];
    static int[] vert = new int[60000000];
    static Comparator<edge_c> edgesort = Comparator.comparingLong(o -> o.val);

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        ArrayList<edge_c> edges = new ArrayList<>();
        int n = in.nextInt();
        int num = 1;
        int col;
        for (int i = 0; i < vert.length; i++) {
            vert[i] = i;
        }
        for (int i = 1; i <= 2 * n + 1; i++) {
            if (i <= n + 1) {// first half
                col = n - i + 2;
            } else {
                col = i - n;
            }
            int cnt = 2 * n + 1 - Math.abs(i - n - 1);
            for (int j = 1; j <= cnt; j++) {
                int w = in.nextInt();
                nodes[i][col] = w;
                ind[i][col] = num;
                num++;
                col += 2;
            }
        }
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                int cur = nodes[i][j];
                if (nodes[i][j] != 0) { // has a node
                    // only build right, right down. left down
                    if (j + 2 < nodes.length && nodes[i][j + 2] != 0) {
                        edge_c tmp = new edge_c((long) cur * nodes[i][j + 2], ind[i][j], ind[i][j + 2]);
                        edges.add(tmp);
                    }
                    if (i + 1 < nodes.length && j + 1 < nodes.length && nodes[i + 1][j + 1] != 0) {
                        edge_c tmp = new edge_c((long) cur * nodes[i + 1][j + 1], ind[i][j], ind[i + 1][j + 1]);
                        edges.add(tmp);
                    }
                    if (i + 1 < nodes.length && j - 1 >= 0 && nodes[i + 1][j - 1] != 0) {
                        edge_c tmp = new edge_c((long) cur * nodes[i + 1][j - 1], ind[i][j], ind[i + 1][j - 1]);
                        edges.add(tmp);
                    }
                }
            }
        }
        edges.sort(edgesort);
        long sum = 0;
        for (int i = 0; i < edges.size(); i++) {
            edge_c cur = edges.get(i);
            int v1 = findfather(cur.son);
            int v2 = findfather(cur.dad);
            if (v1 != v2) {
                sum = sum + cur.val;
                // merge
                // v1=findfather(v1);
                // v2=findfather(v2);
                vert[v1] = v2;
            } else {
                continue;
            }
        }
        out.print(sum);
        out.close();

    }

    static int findfather(int v) {
        if (v != vert[v]) {
            vert[v] = findfather(vert[v]);
        }
        return vert[v];
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

class edge_c {
    long val;
    int son;
    int dad;

    public edge_c(long v, int s, int d) {
        val = v;
        son = s;
        dad = d;
    }
}