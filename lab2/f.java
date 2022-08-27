import java.util.Scanner;
//A[i][j]  is a number that equals i2+12345×i+j2−12345×j+i×j.
//mth smallest num
//64.648
//竖排是由小到大的
//横排是由大到小
//min in mat[0][n-1]
//max in mat[n][0]

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextInt();
            long m = sc.nextLong();
            System.out.println(mthSmallest(n, m));

        }
    }

    public static long findNumsSmaller(long num, long n, long m) {
        long ans = 0;
        for (int j = 1; j <= n; j++) { // search rows, rows from big to small

            if (function(n, j) <= num) {
                ans = ans + n;
                continue;
            }
            long greaterThan = 0;
            long left = 1;
            long right = n;
            long temp = 0;
            while (left <= right) {
                long mid = (right - left) / 2 + left;
                if (function(mid, j) == num) {
                    greaterThan = mid;
                    break;
                } else if (function(mid, j) > num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                greaterThan = left - 1;
            }
            ans = ans + greaterThan;
        }
        return ans;

    }

    public static long mthSmallest(long n, long m) {

        long min = 0;
        if (n < 6172) {
            min = function(1, n);
        } else
            min = -38081328;
        long max = Math.max(function(n, 1), function(n, n));

        while (min <= max) {
            long mid = (max - min) / 2 + min;
            long smallerThanMid = findNumsSmaller(mid, n, m);
            if (smallerThanMid < m) { // mid too small
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return min;

    }

    public static long function(long i, long j) {
        return (long) (Math.pow(i + j, 2) - i * j + 12345 * (i - j));
    }

}
