import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[] vert = new int[100001];
    static Comparator<edge_k> edgesort = Comparator.comparingLong(o -> o.val);
    static ArrayList<edge_k> list = new ArrayList<edge_k>();

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(); // nodes
        int m = in.nextInt(); // edges
        for (int i = 0; i < vert.length; i++) {
            vert[i] = i;// initial
        }
        for (int i = 1; i < m + 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            edge_k temp = new edge_k(u, v, w);
            list.add(temp);
        }
        long sum = 0;
        list.sort(edgesort);
        for (int i = 0; i < list.size(); i++) {
            edge_k cur = list.get(i);
            int v1 = findfather(cur.start);
            int v2 = findfather(cur.end);
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

class edge_k {
    int start;
    int end;
    int val;

    public edge_k(int s, int e, int v) {
        start = s;
        end = e;
        val = v;
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