import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = sc.nextInt();
            }

            int[] copy = Arrays.copyOf(a, n);

            long count1 = insertionCount(a);
            long count2 = selectionCount(copy);

            int[] result = selectionSort(a);
            for (int l = 0; l < result.length; l++) {
                System.out.print(result[l] + " ");
            }
            System.out.println();
            if (count1 < count2) {
                System.out.println("Insertion Sort wins!");
            } else
                System.out.println("Selection Sort wins!");

        }
    }

    public static int[] selectionSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {// 遍历未剩余未排序元素中继续寻找最小元素
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }

        return array;
    }

    public static long insertionCount(int[] a) {
        long count = 0;

        for (int i = 0; i < a.length - 1; i++) {
            int current = a[i + 1];
            int index = i;
            while (index >= 0) {
                if (current >= a[index]) {
                    count++;
                    break;
                } else {
                    a[index + 1] = a[index];
                    index--;
                    count += 2;
                }
            }
            if (a[index + 1] != current) {
                a[index + 1] = current;
                // count++;
            }

        }

        return count;
    }

    public static long selectionCount(int[] a) {
        long count = 0;

        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {// 遍历未剩余未排序元素中继续寻找最小元素
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
                count++;
            }

            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
            count++;

        }

        return count;

    }
}