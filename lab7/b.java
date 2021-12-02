package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

import java.util.*;

public class b {
    static int[] leaves=new int[2000000];
    static int leave=0;


    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n=in.nextInt();
        Node[] ind=new Node[2000000];
        Node root=new Node(1);
        root.index=1;
        ind[1]=root;
        if(n==1){
            out.print(1);
            out.close();
            System.exit(0);
        }
        for(int i=0;i<n-1;i++){
            int start=in.nextInt();
            int end=in.nextInt();
            if(ind[start]==null){
                Node s=new Node(start);
                ind[start]=s;
            }
            if(ind[end]==null){
                Node e=new Node(end);
                ind[end]=e;
            }
            ind[start].children.add(ind[end]);
            ind[end].children.add(ind[start]);
        }


        Queue<Node> nodes=new LinkedList<>();
        nodes.add(root);
        root.visited=true;
        while (!nodes.isEmpty()){
            Node cur=nodes.poll();
            if(cur.children.size()==1){
               leaves[cur.index]=cur.index;
                continue;
            }
            for(int i=0;i<cur.children.size();i++){
                if(!cur.children.get(i).visited){
                    nodes.add(cur.children.get(i));
                    cur.children.get(i).visited=true;
                }
            }
        }

        for(int i=0;i<leaves.length;i++){
            if(leaves[i]!=0){
                out.print(leaves[i]+" ");
            }
        }
        out.close();

    }
}


