import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static node_e[][] nodes = new node_e[500001][11];

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(); // nodes
        int m = in.nextInt(); // edges
        int p = in.nextInt(); // portals
        int k = in.nextInt(); // portal at most
        for (int i = 0; i < n + 1; i++) {
            nodes[i][0] = new node_e();
            nodes[i][0].ind = i;
        }
        for (int i = 1; i < m + 1; i++) {// directed
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            nodes[u][0].edges.add(nodes[v][0]);
            nodes[u][0].weights.add((long) w);
        }
        for (int i = 1; i < p + 1; i++) {// portal
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u][0].edges.add(nodes[v][0]);
            nodes[u][0].weights.add(0L);
        }
        int s = in.nextInt();// origin
        int t = in.nextInt();// destination
        node_e sta = nodes[s][0];
        sta.distance = 0;
        // node_e end=nodes[t];
        Comparator<node_e> sort = Comparator.comparingLong(o -> o.distance);

        PriorityQueue<node_e> heap = new PriorityQueue<>(sort);
        heap.add(sta);
        node_e res;
        while (!heap.isEmpty()) {
            node_e cur = heap.poll();

            cur.isvisited = true;
            for (int i = 0; i < cur.edges.size(); i++) {
                node_e a = cur.edges.get(i);
                long weight = cur.weights.get(i);
                if (!a.isvisited) {
                    if (weight != 0) {// not a portal, normal dij

                        if (weight + cur.distance < a.distance) {
                            node_e tmp;
                            if (nodes[a.ind][cur.times] == null) {
                                tmp = new node_e();
                                tmp.times = cur.times;
                                tmp.ind = a.ind;
                                tmp.edges = a.edges;
                                tmp.weights = a.weights;
                                nodes[a.ind][cur.times] = tmp;
                            } else {

                                tmp = nodes[a.ind][cur.times];
                                if (tmp.isvisited)
                                    continue;
                            }
                            tmp.distance = Math.min(tmp.distance, weight + cur.distance);
                            heap.remove(tmp);
                            heap.add(tmp);
                        }
                    } else {// is portal
                        int nxt_times = cur.times + 1;
                        if (nxt_times <= k) {
                            if (weight + cur.distance < a.distance) { // need update
                                node_e tmp;
                                if (nodes[a.ind][nxt_times] == null) {
                                    tmp = new node_e();
                                    tmp.times = nxt_times;
                                    tmp.ind = a.ind;
                                    tmp.edges = a.edges;
                                    tmp.weights = a.weights;
                                    nodes[a.ind][nxt_times] = tmp;
                                } else {
                                    tmp = nodes[a.ind][nxt_times];
                                    if (tmp.isvisited)
                                        continue;
                                }
                                tmp.distance = Math.min(tmp.distance, weight + cur.distance);
                                heap.remove(tmp);
                                heap.add(tmp);
                            }

                        }

                    }
                }
            }

        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            min = Math.min(min, nodes[t][i].distance);
        }
        out.print(min);
        out.close();
    }
}

class node_e {
    int ind;
    int times = 0;
    long distance = Long.MAX_VALUE;
    boolean isvisited = false;
    ArrayList<node_e> edges = new ArrayList<>();
    ArrayList<Long> weights = new ArrayList<>();
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
