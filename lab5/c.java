package lab5;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class c {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int n=in.nextInt();
            String s=in.nextLine();
            out.println(isValid(s,n));
        }
        out.close();
    }

    public static String isValid(String s,int n) {
        StackL stackL=new StackL(n);
        char[] chars = s.toCharArray();
        if(chars.length%2==1) return "NO";
        int count=0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '(' || chars[i] == '[') {
                stackL.push(chars[i]);
            }
            if ((chars[i] == '}' || chars[i] == ')' || chars[i] == ']')  && !stackL.isEmpty() ) {
                char popped = stackL.pop();
                switch (chars[i]) {
                    case ']':
                        if (popped != '[')
                            return "NO";
                        break;
                    case '}':
                        if (popped != '{')
                            return "NO";
                        break;
                    case ')':
                        if (popped != '(')
                            return "NO";
                        break;
                }
                count++;
            }

        }
        if( count == chars.length / 2){
            return "YES";
        }
        else return "NO";


    }

}
class StackL{
    char[] array;
    int size=0;
    int capacity;
    int top=-1;
    public StackL(int capacity){
        //this.size=size;
        array =new char[capacity];
    }

    public int size(){
        return size;
    }

    public void push(char item){
        if(array.length==size){
            return;
        }
        array[++top]=item;
        size++;
    }

    public char pop(){
        if(isEmpty()){
            return 'E';
        }
        size--;
        return array[top--];
    }

    public char top(){
        if(isEmpty()){
            return 'E';
        }
        return array[top];
    }

    public boolean isEmpty(){
        return this.top==-1;
    }


}


