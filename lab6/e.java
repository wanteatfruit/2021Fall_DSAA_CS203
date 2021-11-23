package lab6;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class e {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        String s= in.next();

        if(s.length()==1){
            out.print(1);
            out.close();
            System.exit(0);
        }
        else if(s.length()==2){
            if(s.charAt(0)==s.charAt(1)){
                out.print(2);
            }
            else out.print(0);
            out.close();
            System.exit(0);
        }
        else {

            int left = 0;
            int right = 0;
            int longest = 1;

            for(int i=0;i<s.length();i++){

                left=i;
                right=i;

                if(right<s.length()-1 && s.charAt(right)==s.charAt(right+1)){
                    int same=1;
                    while (right<s.length()-1 && s.charAt(right)==s.charAt(right+1)){
                        right++;
                        same++;
                    }
                    longest=Math.max(longest,same);
                    right=right+1;
                    left--;
                    i=right;
                }

                while(right<s.length() && left>=0 && s.charAt(left)==s.charAt(right)){
                    longest=Math.max(longest,right-left+1);
                    right++;
                    left--;
                }
            }

            out.print(longest);


            out.close();
        }

    }

    static boolean isPal(int left,int right,String s){
        Stack<Character> judge=new Stack<>();
        String sub=s.substring(left,right+1);
        if(sub.length()%2==0){

            for(int i=0;i<sub.length()/2;i++){
                judge.add(sub.charAt(i));
            }
            for(int i=sub.length()/2;i<sub.length();i++){
                if(judge.pop()!=sub.charAt(i)) return false;
            }
        }
        else {
            for(int i=0;i<=sub.length()/2-1;i++){
                judge.add(sub.charAt(i));
            }
            for(int i=sub.length()/2+1;i<sub.length();i++){
                if(judge.pop()!=sub.charAt(i)) return false;
            }
        }
        return true;
    }
}





