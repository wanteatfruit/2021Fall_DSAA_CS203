import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static long[] temp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }
        temp = new long[n];
        long cost = 0;

        mergeSort(a);

        for (long l : temp) {
            cost = cost + l;
        }
        System.out.print(cost);

    }

    public static long findPairs(long[] a, long[] b) {

        int pointer1 = 0;
        int pointer2 = 0;
        long temp1;
        long temp2 = 0;
        long count = 0;
        while (pointer1 < a.length) {
            if (pointer2 < b.length && a[pointer1] > b[pointer2]) { // legal
                pointer2++;
                temp2 = pointer2;

            } else {
                count = count + temp2;
                pointer1++;
            }
        }

        return count;
    }

    public static long[] mergeSort(long[] a) {
        long cost = 0;
        if (a.length < 2) {
            return a;
        } else {

            int mid = a.length / 2;
            long[] left = Arrays.copyOfRange(a, 0, mid);
            long[] right = Arrays.copyOfRange(a, mid, a.length);
            // return cost+mergeCost(mergeSort(left),mergeSort(right));
            return mergeCost(mergeSort(left), mergeSort(right));
        }
    }

    public static long totalCost(long[] a) {
        long cost = 0;
        if (a.length < 2) {
            return 0;
        } else {

            int mid = a.length / 2;
            long[] left = Arrays.copyOfRange(a, 0, mid);
            long[] right = Arrays.copyOfRange(a, mid, a.length);
            return cost;
        }
    }

    public static long[] mergeCost(long[] left, long[] right) {
        long[] result = new long[left.length + right.length];

        long cost = 0;
        long differ = 0;

        int pointer1 = 0;
        int pointer2 = 0;
        int pointer3 = 0;

        while (pointer3 < result.length) {
            if (pointer2 == right.length) {
                result[pointer3] = left[pointer1];
                pointer1++;
            } else if (pointer1 < left.length && left[pointer1] <= right[pointer2]) {
                result[pointer3] = left[pointer1];
                pointer1++;
            } else { // left>right
                differ = left.length - pointer1;
                cost = cost + (differ * right[pointer2]);

                result[pointer3] = right[pointer2];
                pointer2++;
            }
            pointer3++;
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                temp[i] = cost;
                break;
            }
        }

        return result;

    }

    public static long[] merge(long[] left, long[] right) {
        long[] result = new long[left.length + right.length];

        int pointer1 = 0;
        int pointer2 = 0;
        int pointer3 = 0;

        while (pointer3 < result.length - 1) {
            if (pointer2 == right.length) {
                result[pointer3] = left[pointer1];
                pointer1++;
            } else if (pointer1 < left.length && left[pointer1] <= right[pointer2]) {
                result[pointer3] = left[pointer1];
                pointer1++;
            } else {
                result[pointer3] = right[pointer2];
                pointer2++;
            }
            pointer3++;
        }

        return result;

    }

}