import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> firstdfs = new ArrayList<>();
    static node_f[] nodes = new node_f[100001];
    static re_f[] r_nodes = new re_f[100001];
    static int scc_cnt = 0;
    static int[] out_scc = new int[100001];
    static int[] in_scc = new int[100001];

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new node_f();
            nodes[i].ind = i;
            r_nodes[i] = new re_f();
            r_nodes[i].ind = i;
        }
        for (int i = 1; i < m + 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].edges.add(nodes[v]);
            r_nodes[v].edges.add(r_nodes[u]);
        }
        // first dfs, get reversed list
        for (int i = 1; i < n + 1; i++) {
            if (r_nodes[i].color == 0) {
                dfs1(r_nodes[i]);
            }
        }
        // second dfs, separate into different scc
        for (int i = firstdfs.size() - 1; i >= 0; i--) {
            if (nodes[firstdfs.get(i)].color == 0) {
                scc_cnt++;
                dfs2(nodes[firstdfs.get(i)]);
            }
        }
        // iterate all nodes, get degree
        for (int i = 1; i < n + 1; i++) {
            node_f cur = nodes[i];
            int cur_scc = cur.belong;
            for (int j = 0; j < cur.edges.size(); j++) {
                node_f edge = cur.edges.get(j);
                if (edge.belong != cur_scc) {
                    out_scc[cur_scc] = 1; // have degree
                    in_scc[edge.belong] = 1;
                }
            }
        }
        int out_0 = 0;
        int in_0 = 0;
        for (int i = 1; i <= scc_cnt; i++) {
            if (out_scc[i] == 0) {
                out_0++;
            }
            if (in_scc[i] == 0) {
                in_0++;
            }
        }
        int res = Math.max(out_0, in_0);
        if (scc_cnt == 1) {
            out.print(0);
        } else {
            out.print(res);
        }
        out.close();
    }

    static void dfs1(re_f root) {
        root.color = 1;
        for (int i = 0; i < root.edges.size(); i++) {
            re_f a = root.edges.get(i);
            if (a.color == 0) {
                dfs1(a);
            }
        }
        root.color = 2;
        firstdfs.add(root.ind);
    }

    static void dfs2(node_f root) {
        root.color = 1;
        root.belong = scc_cnt;
        for (int i = 0; i < root.edges.size(); i++) {
            node_f a = root.edges.get(i);
            if (a.color == 0) {
                dfs2(a);
            }
        }
        root.color = 2;
        // cnt++;

    }
}

class node_f {
    int ind;
    int color = 0;
    int belong = 0;
    ArrayList<node_f> edges = new ArrayList<>();
}

class re_f {
    int ind;
    int color = 0;
    ArrayList<re_f> edges = new ArrayList<>();
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
