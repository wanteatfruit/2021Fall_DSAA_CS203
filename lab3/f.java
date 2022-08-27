import java.math.BigInteger;
import java.util.*;

public class Main {

    private long hp;
    private long dmg;
    private long hpMinusdmg;

    public Main(long hp, long dmg, long hpMinusdmg) {
        this.dmg = dmg;
        this.hp = hp;
        this.hpMinusdmg = hpMinusdmg;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // num of plant
        int p = sc.nextInt(); // double health
        int q = sc.nextInt(); // power=health

        Main[] plants = new Main[n];

        for (int i = 0; i < n; i++) {
            int hp = sc.nextInt();
            int dmg = sc.nextInt();
            plants[i] = new Main(hp, dmg, hp - dmg);
        }

        if (q == 0) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum = plants[i].dmg + sum;
            }
            System.out.println(sum);
            System.exit(0);
        } else {
            Main[] sorted = mergeSort(plants);
            long res = 0;
            for (int i = 0; i < sorted.length; i++) {
                if (i < q) {
                    if (sorted[i].hpMinusdmg >= 0) {
                        res = res + sorted[i].hp;
                    } else
                        res = res + sorted[i].dmg;
                } else {
                    res = res + sorted[i].dmg;
                }
            }
            long pow = (long) Math.pow(2, p);
            long temp = res;
            for (int i = 0; i < n; i++) {
                long newres = temp;
                if (i < q) {
                    if (sorted[i].hpMinusdmg >= 0) {
                        newres = newres - sorted[i].hp;
                    } else {
                        newres = newres - sorted[i].dmg;
                    }
                    // long tempHp=sorted[i].hp;
                    // sorted[i].hp=pow*sorted[i].hp;
                    newres = newres + sorted[i].hp * pow;
                    res = Math.max(res, newres);
                    // sorted[i].hp=tempHp;

                } else {

                    if (sorted[q - 1].hpMinusdmg >= 0) {
                        newres = newres - sorted[q - 1].hp;
                    } else {
                        newres = newres - sorted[q - 1].dmg;
                    }

                    newres = newres + sorted[q - 1].dmg;

                    newres = newres - sorted[i].dmg;
                    sorted[i].hp = pow * sorted[i].hp;
                    newres = newres + sorted[i].hp;
                    res = Math.max(res, newres);

                }

            }
            System.out.println(res);

        }

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

    public static Main[] mergeSort(Main[] a) {
        if (a.length < 2) {
            return a;
        } else {
            int mid = a.length / 2;
            Main[] left = Arrays.copyOfRange(a, 0, mid);
            Main[] right = Arrays.copyOfRange(a, mid, a.length);
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

    public static Main[] merge(Main[] left, Main[] right) {
        Main[] result = new Main[left.length + right.length];

        int pointer1 = 0;
        int pointer2 = 0;
        int pointer3 = 0;

        while (pointer3 < result.length) {
            if (pointer2 == right.length) {
                result[pointer3] = left[pointer1];
                pointer1++;
            } else if (pointer1 < left.length && left[pointer1].hpMinusdmg > right[pointer2].hpMinusdmg) {
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