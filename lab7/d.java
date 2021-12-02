package lab7;

import java.io.*;
import java.util.StringTokenizer;

public class d {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n=in.nextInt();
        for(int l=0;l<n;l++){
            String a=in.next();
            String b=in.next();
            char[]ac=a.toCharArray();
            char[]bc=b.toCharArray();
            int pow=0;
            for(int i=0;i<a.length()-1;i++){
                for(int j=b.length()-1;j>=1;j--){
                    if(bc[j]==ac[i] && bc[j-1]==ac[i+1]){
                        pow++;
                    }
                }
            }
            long res= (long) Math.pow(2,pow);
            out.println(res);

        }
        out.close();
    }
}


