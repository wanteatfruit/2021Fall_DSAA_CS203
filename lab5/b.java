package lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n=in.nextInt(); //num of group
        int p=in.nextInt(); //q1 size
        int q=in.nextInt(); //q2 size
        long [] res=new long[n+1];
        int [] dequed=new int[n+1];
        QueueI q1=new QueueI(p);
        QueueI q2=new QueueI(q);
        for(int i=0;i<p;i++){
            q1.enQueue(in.nextInt());
        }
        for(int i=0;i<q;i++){
            q2.enQueue(in.nextInt());
        }

        int time=1;
        while (q1.size()>0 && q2.size()>0){
            if(q1.front().equals(q2.front())){ //same group
                res[q1.front()]=time;
            }
            else {
                while ((q1.size()>0 && dequed[q1.front()]==1) || (q2.size()>0 && dequed[q2.front()]==1)){
                    if(q1.size()>0 && dequed[q1.front()]==1){
                        q1.deQueue();
                    }
                    if(q2.size()>0 && dequed[q2.front()]==1){
                        q2.deQueue();
                    }
                }
                if(q2.size()>0){
                    res[q2.front()]=time;
                    dequed[q2.front()]=1;
                }
                if(q1.size()>0){
                    res[q1.front()]=time;
                    dequed[q1.front()]=1;

                }
                //deque another member

            }
            if(q1.size()>0){
                q1.deQueue();
            }
            if(q2.size()>0){
                q2.deQueue();
            }
            time++;
        }

        while (q1.size()>0){
            while (q1.size()>0 && dequed[q1.front()]==1){
                q1.deQueue();
            }
            if(q1.size()>0){
                res[q1.front()]=time;
                q1.deQueue();
                time++;
            }
        }
        while (q2.size()>0){
            while (q2.size()>0 && dequed[q2.front()]==1){
                q2.deQueue();
            }
            if(q2.size()>0){
                res[q2.front()]=time;
                q2.deQueue();
                time++;
            }
        }

        for(int i=1;i<res.length;i++){
            out.print(res[i]+" ");
        }

        out.close();



    }
}

class QueueI{
    ArrayList<Integer> array;

    public QueueI(int capacity){
        array=new ArrayList<>(capacity);
    }

    public QueueI(){
        array=new ArrayList<>();
    }

    public int size(){
        return array.size();
    }

    public void enQueue(Integer item){
        array.add(item);
    }

    public Integer deQueue(){
        return array.remove(0);
    }

    public Integer front(){
        return array.get(0);
    }

}


