import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static bnode[] nodes = new bnode[200000];
    static reverseb[] reversebs = new reverseb[200000];
    static ArrayList<Integer> firstdfs = new ArrayList<>();
    static ArrayList<Integer> L = new ArrayList<>(); // reversed firstdfs
    static int cnt = 0;
    static int min = 10000000;
    static boolean flag = false;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] cur_edges = new int[100001][2];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new bnode();
            nodes[i].idx = i;
            reversebs[i] = new reverseb();
            reversebs[i].idx = i;
        }
        for (int i = 1; i < m + 1; i++) {
            int u = in.nextInt(); // directed
            int v = in.nextInt();
            cur_edges[i][0] = u;
            cur_edges[i][1] = v;
        }

        int left = 1;
        int right = m;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            cnt = 0;
            L = new ArrayList<>();
            firstdfs = new ArrayList<>();
            for (int i = 1; i < m + 1; i++) {
                nodes[cur_edges[i][0]].edges = new ArrayList<>();
                nodes[cur_edges[i][0]].color = 0;
                reversebs[cur_edges[i][1]].edges = new ArrayList<>();
                reversebs[cur_edges[i][1]].color = 0;

            }
            for (int i = 1; i < mid + 1; i++) {
                nodes[cur_edges[i][0]].edges.add(nodes[cur_edges[i][1]]);
                reversebs[cur_edges[i][1]].edges.add(reversebs[cur_edges[i][0]]);
            }
            for (int i = 1; i < mid + 1; i++) {
                if (reversebs[cur_edges[i][1]].color == 0) {
                    dfs1(reversebs[cur_edges[i][1]]);
                }
            }
            dfs2(nodes[firstdfs.get(firstdfs.size() - 1)]);
            if (cnt > n) {
                right = mid - 1;
            } else if (cnt == n) {
                right = mid - 1;
                flag = true;
                min = Math.min(mid, min);
            } else {
                left = mid + 1;
            }

        }
        if (!flag) {
            out.print(-1);
        } else
            out.println(min);
        out.close();

    }

    static void dfs1(reverseb root) {
        root.color = 1;
        for (int i = 0; i < root.edges.size(); i++) {
            reverseb a = root.edges.get(i);
            if (a.color == 0) {
                dfs1(a);
            }
        }
        root.color = 2;
        firstdfs.add(root.idx);
    }

    static void dfs2(bnode root) {
        root.color = 1;
        for (int i = 0; i < root.edges.size(); i++) {
            bnode a = root.edges.get(i);
            if (a.color == 0) {
                dfs2(a);
            }
        }
        root.color = 2;
        cnt++;

    }

}

class bnode {
    int idx;
    int color = 0;
    ArrayList<bnode> edges = new ArrayList<>();
}

class reverseb {
    int idx;
    int color = 0;
    ArrayList<reverseb> edges = new ArrayList<>();
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