import java.io.*;
import java.util.*;

public class Main {
    static anode[] nodes = new anode[200000];
    static long nil = 1000000000000L;

    public static void main(String[] args) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new anode();
            nodes[i].index = i;
        }
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();// nodes
        int m = in.nextInt();// edges
        Comparator<anode> sort = (o1, o2) -> {
            int r = Long.compare(o1.distance, o2.distance);
            return r;
        };
        // TreeMap<Integer,Long> bst=new TreeMap<>(); //key: index val: distance
        PriorityQueue<anode> heap = new PriorityQueue<>(sort);

        for (int i = 1; i < m + 1; i++) {
            int u = in.nextInt(); // directed u to v
            int v = in.nextInt();
            int w = in.nextInt(); // weight
            nodes[u].edges.add(nodes[v]);
            nodes[u].weights.add(w);
        }
        nodes[1].distance = 0;
        for (int i = 1; i < n + 1; i++) {
            heap.add(nodes[i]);
        }
        while (!heap.isEmpty()) {
            anode cur = heap.poll();
            cur.isvisited = true;
            for (int i = 0; i < cur.edges.size(); i++) {
                anode a = cur.edges.get(i);
                if (!a.isvisited) {
                    if (a.distance == nil) {
                        a.distance = cur.weights.get(i);
                        a.distance = cur.distance + cur.weights.get(i);
                    } else {
                        a.distance = Math.min(cur.distance + cur.weights.get(i), a.distance);
                    }
                    heap.remove(a);
                    heap.add(a);
                }
            }
            // heap.remove(cur);
        }
        if (nodes[n].distance == 1000000000000L)
            out.println(-1);
        else
            out.println(nodes[n].distance);

        out.close();

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

class anode {
    int index;
    ArrayList<anode> edges = new ArrayList<>();
    ArrayList<Integer> weights = new ArrayList<>();
    long distance = 1000000000000L;
    boolean isvisited = false;

}