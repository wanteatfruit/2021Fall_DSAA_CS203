package lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class a {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int t=in.nextInt();
        StackS food=new StackS(10000);
        QueueS student=new QueueS();

        for(int i=0;i<t;i++){
            String event=in.next();
            String name = "";
            if(event.equals("TakeFood")){
                
            }
            else {
                name = in.next();
            }
            switch (event){
                case "NewComer":
                    student.enQueue(name); //name of fav food
                    break;
                case "NewFood":
                    food.push(name);  //new food
                    break;
                case "TakeFood":
                    String fav= student.front();
                    if(food.isEmpty()){
                        break;
                    }
                    else {
                        if(food.top().equals(fav)){
                            food.pop();
                            student.deQueue();
                        }
                        else {
                            student.deQueue();
                            student.enQueue(fav);
                        }
                    }
                    break;
            }

        }

        while (!food.isEmpty()&&student.size()>0&&check(food,student)){
            String fav=student.front();
            if(food.isEmpty()){
                break;
            }
            else {
                if(food.top().equals(fav)){
                    food.pop();
                    student.deQueue();
                }
                else {
                    student.deQueue();
                    student.enQueue(fav);
                }
            }
        }

        if(student.size()==0){
            out.println("Qi Fei!");
        }
        else {
            out.println(student.size());
        }
        out.close();
    }

    static boolean check(StackS s,QueueS fav){
        for(int i=0;i< fav.size();i++){
            if(fav.array.get(i).equals(s.top())){
                return true;
            }
        }
        return false;
    }
}
class StackS{
    String[] array;
    int size=0;
    int capacity;
    int top=-1;
    public StackS(int capacity){
        //this.size=size;
        array =new String[capacity];
    }

    public int size(){
        return size;
    }

    public void push(String item){
        if(array.length==size){
            return;
        }
        array[++top]=item;
        size++;
    }

    public String pop(){
        if(isEmpty()){
            return "empty";
        }
        size--;
        return array[top--];
    }

    public String top(){
        if(isEmpty()){
            return "empty";
        }
        return array[top];
    }

    public boolean isEmpty(){
        return this.top==-1;
    }


}

class QueueS{
    ArrayList<String> array;

    public QueueS(int capacity){
        array=new ArrayList<>(capacity);
    }

    public QueueS(){
        array=new ArrayList<>();
    }

    public int size(){
        return array.size();
    }

    public void enQueue(String item){
        array.add(item);
    }

    public String deQueue(){
        return array.remove(0);
    }

    public String front(){
        return array.get(0);
    }

}



