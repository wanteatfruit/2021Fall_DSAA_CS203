import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            double test = sc.nextDouble();
            // System.out.println(calculate(test));
            System.out.printf("%.10f\n", calculate(test));
        }
    }

    public static double calculate(double b) {
        double min = 0; // f(min)<0
        double max = 260; // f(max)>0
        double mid = (min + max / 2);

        while (!accurate(mid, b)) {
            if (function(mid, b) <= 0) {
                min = mid;
            } else {
                max = mid;
            }
            mid = (min + max) / 2;
            // mid=newton(mid,b);
        }

        return mid;

    }

    public static double function(double x, double test) {
        return x * Math.pow(Math.E, x / 20) - test;
    }

    public static double newton(double x0, double b) {
        double fx = x0 * Math.pow(Math.E, x0 / 20) - b;
        double log = Math.log(fx);
        double deri = Math.pow(Math.E, x0 / 20) + fx / 20;
        return x0 - (log / deri);
    }

    public static boolean accurate(double ans, double test) {
        double val = ans * Math.pow(Math.E, ans / 20) - test;
        return Math.abs(val) < 0.01;
    }
}