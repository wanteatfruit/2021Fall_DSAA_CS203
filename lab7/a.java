package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

import java.util.*;

public class Main {
    static QWriter out = new QWriter();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QReader in = new QReader();
        int n = in.nextInt();
        int target = in.nextInt();
        Node[] nodes = new Node[500001];
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int weight = in.nextInt();
            if (nodes[u] == null) {
                Node unode = new Node(u);
                nodes[u] = unode;
            }
            if (nodes[v] == null) {
                Node vnode = new Node(v);
                nodes[v] = vnode;
            }
            nodes[u].children.add(nodes[v]);
            nodes[u].weights.add(weight);
            nodes[v].children.add(nodes[u]);
            nodes[v].weights.add(weight);
        }

        int count = 0;
        Node root = nodes[1];
        Queue<Node> Nodeq = new LinkedList<>();
        Queue<Integer> sumq = new LinkedList<>();
        Nodeq.add(root);
        sumq.add(0);
        root.visited = true;
        while (!Nodeq.isEmpty()) {
            Node cur = Nodeq.poll();
            int tmp = sumq.poll();
            if (cur.children.size() == 1) {
                if (tmp == target) {
                    count++;
                }
            }
            for (int i = 0; i < cur.children.size(); i++) {
                if (!cur.children.get(i).visited) {
                    Nodeq.add(cur.children.get(i));
                    cur.children.get(i).visited = true;
                    sumq.add(tmp + cur.weights.get(i));
                }
            }
        }

        out.print(count);
        out.close();

    }

    static long log(long n) {
        return (long) (Math.log(n) / Math.log(2));
    }

    static void print(Node root) {
        if (root.children.size() == 0) {
            out.print(root.index + " ");
            // return;
        } else {
            for (int i = 0; i < root.children.size(); i++) {
                print(root.children.get(i));
            }

        }
    }
}

class Node {
    List<Node> children = new ArrayList<>();
    int index;
    List<Integer> weights = new ArrayList<>();
    boolean visited = false;

    public Node(int index) {
        this.index = index;
        children = new ArrayList<>();
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