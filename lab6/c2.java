package lab6;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class c2 {
    public static void main(String[] args) {
        //Scanner sc=new Scanner(System.in);
        QReader sc=new QReader();
        QWriter out=new QWriter();
        String s=sc.nextLine();
        int[] next=new int[s.length()+1];
        int m=s.length();
        next[0]=0;
        int k=0;
        int j=1;

        while(j<m){
            if(s.charAt(k)==s.charAt(j)){
                k++;
                next[j]=k;
                j++;
            }
            else if(k==0){
                next[j]=0;
                j++;
            }
            else{
                k=next[k-1];
            }
        }


        for(int i=0;i<next.length-1;i++){
            out.println(next[i]);
        }
        out.close();
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

