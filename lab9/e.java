import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // directed
    static Dijknode[] dijknodes = new Dijknode[400000];
    static long[] distance = new long[400000];

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();// nodes
        int m = in.nextInt();// edges
        for (int i = 0; i < dijknodes.length; i++) {
            dijknodes[i] = new Dijknode();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt(); // start
            int v = in.nextInt(); // end
            int w = in.nextInt(); // weight
            if (w == 2) { // turn 2 into 2 1s
                Dijknode tmp = new Dijknode();
                dijknodes[u].edges.add(tmp);
                tmp.edges.add(dijknodes[v]);
            } else {
                dijknodes[u].edges.add(dijknodes[v]);
            }

            // dijknodes[u].weights.add()
        }
        dijknodes[1].distance = 0;
        bfs(dijknodes[1]);
        if (dijknodes[n].distance == 1000000000000L) {
            out.println(-1);
        } else {
            out.println(dijknodes[n].distance);
        }
        out.close();
    }

    static void bfs(Dijknode cur) {
        Queue<Dijknode> queue = new LinkedList<>();
        queue.add(cur);
        // cur.distance=0;
        while (!queue.isEmpty()) {
            Dijknode now = queue.poll();
            now.isvisited = true;
            for (int i = 0; i < now.edges.size(); i++) {
                if (!now.edges.get(i).isvisited) {
                    queue.add(now.edges.get(i));
                    long curDis = now.edges.get(i).distance;
                    now.edges.get(i).distance = Math.min(curDis, now.distance + 1);
                }
            }
        }
    }
}

class Dijknode {
    int index;
    ArrayList<Dijknode> edges = new ArrayList<>();
    ArrayList<Integer> weights = new ArrayList<>();
    long distance = 1000000000000L;
    boolean isvisited = false;

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
