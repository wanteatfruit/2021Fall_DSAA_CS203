import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int T = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        for (int i = 0; i < T; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(med(a, b, l, r));
        }

    }

    public static int med(int[] a, int[] b, int l, int r) {
        int k = r - l + 1;
        int left = l - 1;
        int right = r - 1;
        int countA = 0;
        int countB = 0;
        int mid = 0;

        while (k > 1) {
            mid = k / 2 - 1;
            int temp = mid + 1;
            if (a[left + mid + countA] >= b[left + mid + countB]) {
                countB = countB + mid + 1;
            } else {
                countA = countA + mid + 1;
            }
            k = k - temp;
        }
        if (a[left + mid + countA] >= b[left + mid + countB]) {
            return b[left + mid + countB];
        } else
            return a[left + mid + countA];

    }

}