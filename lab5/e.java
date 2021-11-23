package lab5;
import java.io.*;
import java.util.StringTokenizer;

public class e {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        while (in.hasNext()){
            int n=in.nextInt(); //num of deque
            int q=in.nextInt(); //num of operation
            DequeE [] dequeLs=new DequeE[n+1];
            for(int i=0;i<n+1;i++){
                dequeLs[i]=new DequeE(500);
            }


            for(int i=0;i<q;i++){
                int type=in.nextInt();
                switch (type){
                    case 1:
                        int u=in.nextInt();
                        int w=in.nextInt();
                        int val=in.nextInt();
                        if(w==0){
                            dequeLs[u].insertfront(val);
                        }
                        else{
                            dequeLs[u].insertrear(val);
                        }
                        break;
                    case 2:
                        int u2=in.nextInt();
                        int w2=in.nextInt();
                        if(dequeLs[u2].isEmpty()){
                            out.println(-1);
                        }
                        else if(w2==0){
                            out.println(dequeLs[u2].popFront());
                        }
                        else{
                            out.println(dequeLs[u2].popRear());
                        }
                        break;
                    case 3:
                        int u3=in.nextInt();
                        int v3=in.nextInt();
                        int w3=in.nextInt();
                        if(w3==0){ //connect v to rear of u
                            if(dequeLs[v3].size==0){
                                continue;
                            }
                            else {
                                while (!dequeLs[v3].isEmpty()){
                                    dequeLs[u3].insertrear(dequeLs[v3].popFront());
                                }
                            }
                        }
                        if(w3==1){
                            if(dequeLs[v3].size==0){
                                continue;

                            }
                            else {
                                while (!dequeLs[v3].isEmpty()){
                                    dequeLs[u3].insertrear(dequeLs[v3].popRear());
                                }
                            }
                        }

                        break;
                }
            }
        }
        out.close();
    }

}



class DequeE {
    int max = 1002;
    int [] arr;
    int front;
    int rear;
    int size;

    public DequeE(int size) {
        arr = new int[max];
        front = -1;
        rear = 0;
        this.size = size;
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
