package lab7;


import java.io.*;

import java.util.*;

public class f {
    static int max=0;
    static int ans=0;
    static int[] distance1=new int[2000000];
    static int[] distance2=new int[2000000];
    static int first=0;
    static int second=0;
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int t=in.nextInt();
        for (int i = 0; i < t; i++) {
            Node[] nodes=new Node[2000000];
            max=0;
            ans=0;
            Arrays.fill(distance1,0);
            Arrays.fill(distance2,0);
            first=0;
            second=0;
            int n=in.nextInt();
            int k=in.nextInt();
            Node root=new Node(1);
            nodes[1]=root;

            for (int j = 0; j < n-1; j++) {
                int n1=in.nextInt();
                int n2=in.nextInt();
                if(nodes[n1]==null){
                    Node a=new Node(n1);
                    nodes[n1]=a;
                }
                if(nodes[n2]==null){
                    Node b=new Node(n2);
                    nodes[n2]=b;
                }
                nodes[n1].children.add(nodes[n2]);
                nodes[n2].children.add(nodes[n1]);
            }

            int k1=in.nextInt();
            nodes[k1].hasPerson=true;
            first=k1;
            for (int j = 1; j < k; j++) {
                int n3=in.nextInt();
                nodes[n3].hasPerson=true;
            }

            if(k==1){
                out.println(0);
                continue;
            }

            distance1[first]=0;

            dfs1(nodes[first]);

            max=0;
            distance2[second]=0;

            dfs2(nodes[second]);

            ans=(max+1)/2;
            out.println(ans);
        }
        out.close();
    }

    static void dfs1(Node node){
        if(node==null){
            return;
        }
        else {
            node.visited=true;
            if(node.hasPerson && distance1[node.index]>max){
                max=distance1[node.index];
                second= node.index;
            }
                for (int i = 0; i < node.children.size(); i++) {
                    if(!node.children.get(i).visited) {
                        distance1[node.children.get(i).index]=distance1[node.index]+1;
                        dfs1(node.children.get(i));
                    }
                }
        }
    }

    static void dfs2(Node node){
        if(node==null){
            return;
        }
        else {
            node.visited=false;
            if(node.hasPerson && distance2[node.index]>max){
                max=distance2[node.index];
                //second= node.index;
            }
            for (int i = 0; i < node.children.size(); i++) {
                if(node.children.get(i).visited) {
                    distance2[node.children.get(i).index]=distance2[node.index]+1;
                    dfs2(node.children.get(i));
                }
            }

        }
    }

    static void bfs(Node root){
        Queue<Node> nodes=new LinkedList<>();
        nodes.add(root);
        root.visited=false;
        while (!nodes.isEmpty()){
            Node cur=nodes.poll();
            if(cur.children.size()==1){
                continue;
            }
            for(int i=0;i<cur.children.size();i++){
                if(cur.children.get(i).visited){
                    nodes.add(cur.children.get(i));
                    cur.children.get(i).visited=false;
                }
            }
        }
    }
}

class Node{
    List<Node> children=new ArrayList<>();
    int index;
    boolean visited=false;
    boolean hasPerson=false;

    public Node(int index){
        this.index=index;
        children=new ArrayList<>();
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
