import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
 
public class Main {
    public static void main(String[] args) {
 
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int []A=new int[n];
        int countA=0;
        int countB=0;
        while (countA<n){
            A[countA]=sc.nextInt();
            countA++;
        }
 
        int t=sc.nextInt();
        int[] B=new int[t];
 
        while (countB<t){
            B[countB]=sc.nextInt();
            countB++;
        }
 
 
        for (int j : B) {
            int count = 0;
            for (int k : A) {
                if (j == k) {
                    System.out.println("yes");
                    count++;
                    break;
                }
 
            }
            if (count == 0) System.out.println("no");
 
        }
    }
 }
 
/**************************************************************
    Problem: 1415
    User: 12012109
    Language: Java
    Result: 正确
    Time:2140 ms
    Memory:31512 kb
****************************************************************/
