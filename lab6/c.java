package lab6;

import java.io.*;
import java.util.StringTokenizer;

public class c {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        String s=in.next();


        int len=s.length();

        int[] next=new int[len+1];

        char[] chars=s.toCharArray();
        int k=-1;

        next[0]=-1;

        int i=0;

        while (i<len) {

             if (k==-1 || s.charAt(k) == s.charAt(i)) {
                 k++;

                 i++;
                 next[i]=k;

            } else {
                k = next[k];
            }
        }




        for(int i1=1;i1<len+1;i1++){
            out.println(next[i1]);
        }
        out.close();
    }
}


