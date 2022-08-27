import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int i=0;i<T;i++){
            long test=sc.nextInt();
            System.out.println(calculate(test));
 
        }
    }
 
    public static Long calculate(long n){
        return n*(n+1)*(n+2)/6;
    }
}