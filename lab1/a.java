import java.util.Scanner;
 
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
 
        for(int i=0;i<B.length;i++){
            int count=0;
            for(int j=0;j<A.length;j++){
                if(B[i]==A[j]){
                    System.out.println("yes");
                    count++;}
 
            }
            if(count==0) System.out.println("no");
 
        }
    }
}
