import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
 
public class Main{
    public static void main(String[] args) {
 
 
        Scanner sc=new Scanner(System.in);
 
        int n=sc.nextInt();
        String []strings=new String[n];
 
 
        for(int i=0;i<n;i++){
            int next=sc.nextInt();
            int numCount=1;
            int correctCount=0;
            int remain=0;
            int remain6=0;
            String s = "";
            Stack<String> stack=new Stack<>();
            if(next==6) {
                stack.add("6");
                stack.add("2");
            }
 
            else if(next%3==0) {
                //next = next - 3;
                while (next>3){
                    remain=next%3;
                    switch (remain){
                        case 0:
                            next=next-3;
                            stack.add("6");
                            break;
                        case 1:
                            stack.add("2");
                            break;
                        case 2:
                            stack.add("3");
                            break;
                    }
                    next=next/3;
                }
                if(next==3) stack.add("6");
                if(next==2) stack.add("3");
                if(next==1) stack.add("2");
                if(next==0) stack.add("6");
 
 
            }
            else {
 
                while (next > 3) {
                    remain = next % 3;
                    switch (remain) {
                        case 0:
                            stack.add("6");
                            next=next-3;
                            break;
                        case 1:
                            stack.add("2");
                            break;
                        case 2:
                            stack.add("3");
                            break;
                    }
                    next = next / 3;
                }
                if(next==3) stack.add("6");
                if (next == 2) stack.add("3");
                if (next == 1) stack.add("2");
                //if(next==0) stack.add("6");
            }
 
 
            while (!stack.isEmpty()){
                s=s+stack.pop();
            }
            strings[i]=s;

        }
        for(int j=0;j<n;j++){
            System.out.println(strings[j]);
        }
 
    }

}
 
