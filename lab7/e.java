package lab7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class e {
    static int leaveNum=0;
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n=in.nextInt();
        Node[] nodes=new Node[100000];
        int[] res=new int[50000];
        Node root=new Node(1);
        nodes[1]=root;
        for(int i=0;i<n-1;i++){
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
        int[] order=new int[50000];

        ArrayList<String> first=new ArrayList<>();
        String path="";
        dfs(root,order[0],path,first);

        for (int i = 0; i <leaveNum; i++) {
            order[i]=in.nextInt();
        }

        String[] pathOrder=new String[order.length];

        for(int i=0;i<first.size();i++){
            String cur=first.get(i);
            for(int j=0;j< leaveNum;j++){
                int ind=cur.length()-1;
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.insert(0,cur.charAt(ind));
                while (cur.charAt(ind-1)!=' '){
                    stringBuilder.insert(0,cur.charAt(ind-1));

                    ind--;
                }
                String tmp=stringBuilder.toString();
                if(Integer.parseInt(tmp)==order[j]){
                    pathOrder[j]=cur;
                }
            }
        }
        int k=0;

        for(int i=0;i< leaveNum;i++){
            if(i==0){
                String[] s=pathOrder[i].split(" ");
                for (int j = 1; j < s.length; j++) {
                    res[k]= Integer.parseInt(s[j]);
                    k++;
                }
            }
            else{
                int index=findCommonPre(pathOrder[i],pathOrder[i-1]);
                String[] s=pathOrder[i].split(" ");
                String[]s0=pathOrder[i-1].split(" ");
                for(int j0=s0.length-2;j0>index;j0--){
                    res[k]=Integer.parseInt(s0[j0]);
                    k++;
                }
                for(int j=index;j<s.length;j++){
                    res[k]=Integer.parseInt(s[j]);
                    k++;
                }
            }
        }
        String[] s0=pathOrder[leaveNum-1].split(" ");
        for(int j0=s0.length-2;j0>=1;j0--){
            res[k]=Integer.parseInt(s0[j0]);
            k++;
        }

        int count=0;

        for(int j=0;j<res.length;j++){
            if(res[j]==0){
                break;
            }
            count++;
        }
        if(count!=(2*n-1)){
            out.print(-1);
        }
        else{
            for (int i = 0; i < res.length; i++) {
                if (res[i] != 0) {
                    out.print(res[i] + " ");
                }
                else{
                    break;
                }
            }

        }
        out.close();


    }
    static int findCommonPre(String a,String b){
        int left=1;
        int right=1;
        int len=0;
        String[] a1=a.split(" ");
        String[] b1=b.split(" ");
        while (left<a1.length && right<b1.length){
            if(a1[left].equals(b1[right])){
                left++;
                right++;
                len++;
            }
            else{
                break;
            }
        }
        return len;
    }

    static void dfs(Node node, int target, String path, ArrayList<String> paths){
        if(node==null){
            return;
        }
        else {
            path=path+" "+String.valueOf(node.index);
            node.visited=true;
            if(node.index!=1 && node.children.size()==1){
                leaveNum++;
                paths.add(path);
                return;
            }
            else {
                for (int i = 0; i < node.children.size(); i++) {
                    if(!node.children.get(i).visited) {
                        dfs(node.children.get(i), target, path, paths);
                    }
                }
            }
        }
    }
}


