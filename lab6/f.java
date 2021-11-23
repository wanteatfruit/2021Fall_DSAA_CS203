package lab6;
//bedeaaa should output 6, because aaa should match
//consider stack or queue
import java.io.*;
import java.util.StringTokenizer;

public class f {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        char[] table=new char[26];
        for(int i=0;i<26;i++){
            table[i]=in.next().charAt(0);
        }
        String s=in.next();
        int scstart=0;
        int codestart=0;
        for(int i=s.length()/2;i<s.length();i++){
            int ind=s.charAt(i)-'a';
            if(s.charAt(codestart)==table[ind]){
                scstart=i;
                while (i<s.length() ){
                    ind=s.charAt(i)-'a';
                    if(s.charAt(codestart)==table[ind]){
                        i++;
                        codestart++;
                    }
                    else {
                        scstart=i+1;
                        codestart=0;

                        i--;
                        break;
                    }

                }

            }
            else{
                scstart=i+1;

            }
        }

        if(scstart==0){
            scstart=s.length();
        }
        out.print(scstart);
        out.close();
    }
}



