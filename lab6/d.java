package lab6;

import java.io.*;
import java.util.*;

public class d {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        String s= in.next();
        String s2=in.next();
        Stack<String> s1stack=new Stack<>();
        Deque<Character> s1q=new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
            s1q.addLast(s.charAt(i));
        }

        for(int i=0;i<s2.length();i++){
            if(s2.charAt(i)==s1q.getFirst()){
                s1q.removeFirst();
            }
        }
        for(int i=0;i<s2.length();i++){
            if(s1q.size()==0) break;
            if(s2.charAt(i)==s1q.getFirst()){
                s1q.removeFirst();
            }
        }
        if(s1q.size()>0){
            out.print("No");
        }
        else {
            out.print("Yes");
        }
        out.close();
    }
}


