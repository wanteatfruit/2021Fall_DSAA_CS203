import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int maxPower = log(2, a[n - 1] + a[n - 2]);

        System.out.println(PairNum(a, maxPower));

    }

    public static int log(int basement, int n) {
        return (int) (Math.log(n) / Math.log(basement));
    }

    public static int PairNum(int[] a, int maxPower) {
        int count = 0;
        for (int i = 0; i < a.length - 1; i++) {
            int temp = maxPower;
            int differ = 0;
            int right = a.length - 1;
            while (temp >= 1) {
                differ = (int) (Math.pow(2, temp) - a[i]);
                int left = i + 1;
                int biggest = findthebiggestnum(a, differ, left, right);
                if (biggest != -1) {
                    count++;

                    while (biggest > left && a[biggest - 1] == differ) {
                        count++;
                        biggest = biggest - 1;
                    }
                }
                temp--;
            }
        }
        return count;
    }

    public static boolean find(int[] a, int differ, int left, int right) { // differ is the target
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == differ) {
                return true;
            } else if (a[mid] > differ) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;

    }

    public static int findthebiggestnum(int[] a, int differ, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == differ) {
                while (mid < a.length - 1 && a[mid + 1] == differ) {
                    mid++;
                }
                return mid;
            } else if (a[mid] > differ) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;

    }
}
