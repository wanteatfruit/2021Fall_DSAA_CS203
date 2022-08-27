import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }
        System.out.println(findMedian(a));

    }

    public static long findMedian(long[] a) {

        long[] inOrder = mergeSort(a);
        if (inOrder.length % 2 == 0) {
            return ((inOrder[a.length / 2] + inOrder[a.length / 2 - 1]));
        } else
            return 2 * inOrder[a.length / 2];
    }

    public static long[] mergeSort(long[] a) {
        if (a.length < 2) {
            return a;
        } else {
            int mid = a.length / 2;
            long[] left = Arrays.copyOfRange(a, 0, mid);
            long[] right = Arrays.copyOfRange(a, mid, a.length);
            return merge(mergeSort(left), mergeSort(right));
        }
    }

    public static long[] merge(long[] left, long[] right) {
        long[] result = new long[left.length + right.length];

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
            } else {
                result[pointer3] = right[pointer2];
                pointer2++;
            }
            pointer3++;
        }

        return result;

    }
}