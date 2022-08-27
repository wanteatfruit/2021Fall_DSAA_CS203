import java.awt.*;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [] arr=new int[n];
        for(int i=0;i<arr.length;i++){
            arr[i]=sc.nextInt();
        }
        int T=sc.nextInt();
        for(int i=0;i<T;i++){
            int test=sc.nextInt();
            System.out.println(search(test,n,arr));
        }
    }
 
    public static String search(int n,int length,int[]arr){
        int left=0;
        int right=length-1;
 
        //int mid=(left+right)/2;
        while(left<=right){
            int mid=(left+right)/2;
            if(arr[mid]==n){
                return "YES";
            }
            else if(arr[mid]>n){
                right=mid-1;
            }
            else {
                left=mid+1;
            }
            //if(left>right) return "NO";
        }
        return "NO";
    }
}
