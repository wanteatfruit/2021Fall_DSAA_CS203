import java.io.*;
import java.util.*;

public class Main {
    static NodeC[] nodeCS = new NodeC[200000];
    static boolean hasCycle = false;
    static int last = 0;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(); // nodes
        int m = in.nextInt(); // edges
        for (int i = 0; i < n + 1; i++) {
            nodeCS[i] = new NodeC(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            nodeCS[u].edges.add(nodeCS[v]);
            nodeCS[u].degree++;
            nodeCS[v].degree++;
            nodeCS[v].edges.add(nodeCS[u]);
        }
        int cnt = 0;
        Queue<NodeC> top = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (nodeCS[i].degree <= 1) {
                top.add(nodeCS[i]);
            }
        }
        while (!top.isEmpty()) {
            NodeC cur = top.poll();
            cnt++;
            for (int i = 0; i < cur.edges.size(); i++) {
                cur.edges.get(i).degree = cur.edges.get(i).degree - 1;
                if (cur.edges.get(i).degree == 1) {
                    top.add(cur.edges.get(i));
                }
            }
        }
        if (cnt == n) {
            out.println("Good");
        } else
            out.println("Bad");
        out.close();
    }

    static void dfs(NodeC nodeC) {
        nodeC.color = 1;
        for (int i = 0; i < nodeC.edges.size(); i++) {
            if (nodeC.edges.get(i).color == 1 && nodeC.edges.get(i).idx != last) {
                hasCycle = true;
                break;
            } else if (nodeC.edges.get(i).color == 0) {
                // father[last]=0;
                last = nodeC.idx;
                dfs(nodeC.edges.get(i));
            }
        }
        nodeC.color = 2;
        // last= nodeC.idx;
    }

}

class NodeC {
    int color = 0;
    int idx;
    int degree = 0;
    ArrayList<NodeC> edges = new ArrayList<>();

    // NodeC last;
    public NodeC(int idx) {
        this.idx = idx;
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