package lab5;

import java.io.*;

import java.util.StringTokenizer;

public class f {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int k=in.nextInt();
        int n=in.nextInt();

        long[] inputs=new long[n];
        for(int i=0;i<n;i++){
            inputs[i]=in.nextLong();
        }
        DequeL min=new DequeL(3000001);
        DequeL max=new DequeL(3000001);
        long len= 0;

        int left=0; int right=0;
        for(int i=0;i<n;i++){//front is max/min
            while (!min.isEmpty() && inputs[i]<min.getRear()){
                min.popRear();
            }
            while (!max.isEmpty() && inputs[i]>max.getRear()){
                max.popRear();
            }
            min.insertrear((int) inputs[i]);
            max.insertrear((int) inputs[i]);
            right++;

            while(left<n && Math.abs(max.getFront()-min.getFront())>k){
                if(inputs[left]==max.getFront()){
                    max.popFront();
                }
                if(inputs[left]== min.getFront()){
                    min.popFront();
                }
                left++;
            }


            len=Math.max(right-left,len);
        }
        out.print(len);
        out.close();
    }
}



class DequeL {
    int max = 3000003;
    int [] arr;
    int front;
    int rear;
    int size;

    public DequeL(int size) {
        arr = new int[max];
        front = -1;
        rear = 0;
        this.size = size;
    }

    int getFront(){
        return arr[front];
    }

    int getRear(){
        return arr[rear];
    }

    boolean isFull() {
        return ((front == 0 && rear == size - 1) ||
                front == rear + 1);
    }

    boolean isEmpty() {
        return (front == -1);
    }


    void insertfront(int key) {

        if (isFull()) {
            return;
        }

        if (front == -1) {
            front = 0;
            rear = 0;
        }
        else if (front == 0) {
            front = size - 1;
        }
        else {
            front = front - 1;
        }
        arr[front] = key;
        //size++;
    }

    void insertrear(int key) {
        if (isFull()) {
            return;
        }

        if (front == -1) {
            front = 0;
            rear = 0;
        }

        else if (rear == size - 1) {
            rear = 0;
        }
        else {
            rear = rear + 1;
        }
        arr[rear] = key;
        //size++;
    }

    public int popFront() {
        if (isEmpty()) {
            return -1;
        }
        int res=0;

        if (front == rear) {
            res=arr[front];
            front = -1;
            rear = -1;
        } else {

            if (front == size - 1) {
                res = arr[front];
                front = 0;
            }

            else {
                res=arr[front];
                front +=1;
            }
        }
        //size--;
        return res;
    }

    public int popRear() {
        if (isEmpty()) {
            return -1;
        }
        int res=0;

        if (front == rear) {
            res=arr[rear];
            front = -1;
            rear = -1;
        } else {

            if (rear==0) {
                res = arr[rear];
                rear=size-1;
            }

            else {
                res=arr[rear];
                rear-=1;}
        }
        //size--;
        return res;
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
