package lab5;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class d {
    public static void main(String[] args) {

        QReader in=new QReader();
        QWriter out=new QWriter();
        String s=in.nextLine();
        StackI stackL=new StackI(1000002);
        stackL.push(new BigInteger("0"));

        char[] chars=s.toCharArray();

        for(int i=0;i<chars.length;i++){
           if(chars[i]=='('){
               stackL.push(new BigInteger("0"));
           }
           if(chars[i]==')'){
               if(!stackL.top().equals(new BigInteger("0"))){
                   BigInteger pop1= stackL.pop();
                   BigInteger pop= stackL.pop();
                   BigInteger push=pop1.multiply(new BigInteger("2")).add(pop);
                   stackL.push(push);
               }
              else  {
                   BigInteger pop1= stackL.pop();
                   BigInteger pop= stackL.pop();
                   stackL.push(pop .add(new BigInteger("1")) );
               }

           }

        }


//        while (!stackL.isEmpty()){
//            ans+=stackL.pop();
//        }
        BigInteger out1=stackL.top().mod(new BigInteger("514329"));
        out.print(out1);
        out.close();

    }
}

class StackI{
    BigInteger[] array;
    int size=0;
    int capacity;
    int top=-1;
    public StackI(int capacity){
        //this.size=size;
        array =new BigInteger[capacity];
    }

    public long size(){
        return size;
    }

    public void push(BigInteger item){
        if(array.length==size){
            return;
        }
        array[++top]=item;
        size++;
    }

    public BigInteger pop(){
        if(isEmpty()){
            return new BigInteger(String.valueOf(0));
        }
        size--;
        return array[top--];
    }

    public BigInteger top(){
        if(isEmpty()){
            return new BigInteger(String.valueOf(0));
        }
        return array[top];
    }

    public boolean isEmpty(){
        return this.top==-1;
    }


}



