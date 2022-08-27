package lab7;

import java.util.Scanner;

public class c {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextLong();
            System.out.println(log(n) + 1);

        }
    }
    static long log(long n) {
        return (long) (Math.log(n) / Math.log(2));
    } 
}
