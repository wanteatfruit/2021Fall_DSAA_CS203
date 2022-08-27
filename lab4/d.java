import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        DoubleLink headD = new DoubleLink(-2000000001, -1);
        DoubleLink tailD = new DoubleLink(-2000000001, n);
        DoubleLink cur = headD;
        HashMap<Integer, DoubleLink> doubleLinkHashMap = new HashMap<>();
        DoubleLink[] doubleLinks = new DoubleLink[n];

        // Node cur = head;
        long[] array = new long[n];
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            array[i] = m;
            // cur.next = new Node(m);
            // cur = cur.next;
        }

        long[] sorted = mergeSort(array);
        for (int i = 0; i < n; i++) {
            DoubleLink tempNew = new DoubleLink(sorted[i], i);
            doubleLinkHashMap.put(i, tempNew);
            // doubleLinks[i]=tempNew;
            cur.next = tempNew;
            DoubleLink temp = cur;
            cur = cur.next;
            cur.pre = temp;
            // sort.next = new Node(sorted[i]);
            // sort = sort.next;

        }
        DoubleLink tempPre = cur;
        cur.next = tailD;
        cur = cur.next;
        cur.pre = tempPre;

        cur = headD;
        // sort.next = tail;
        // sort = head;

        int count = 0;
        int index = 0;

        while (index < array.length - 1) {

            long val = array[index];

            if (index == array.length - 2) {
                res[count] = Math.abs(array[index + 1] - array[index]);
                break;
            }

            int pos = find(val, sorted);
            // cur=doubleLinks[pos];

            // if(doubleLinkHashMap.get(pos)==null)
            while (doubleLinkHashMap.get(pos) == null) {
                pos++;
            }

            cur = doubleLinkHashMap.get(pos);

            // cur=doubleLinks[val];

            // if(pos==0){
            // res[count]=doubleLinkHashMap.get(1).val-doubleLinkHashMap.get(0).val;
            // }
            // else if(pos==n-1){
            // res[count]=doubleLinkHashMap.get(n-1).val-doubleLinkHashMap.get(n-2).val;
            // }
            // else {
            res[count] = Math.min(Math.abs(cur.val - cur.pre.val), Math.abs(cur.next.val - cur.val));
            // }

            cur = cur.pre;
            DoubleLink temp = cur;
            cur.next = cur.next.next;
            cur = cur.next;
            cur.pre = temp;

            doubleLinkHashMap.remove(pos);

            index++;
            count++;

        }

        for (int i = 0; i < n - 1; i++) {
            out.print(res[i] + " ");
        }
        out.close();
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

    public static int find(long target, long[] a) {
        int left = 0;
        int right = a.length - 1;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == target) {
                result = mid;
                break;
            } else if (a[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int back = result;
        while (back > 0 && a[back - 1] == a[result]) {
            back--;
        }
        return back;
    }

    public static Node find(int target, Node a) {
        Node temp = a;
        while (temp.next != null && temp.next.next != null) {
            if (temp.val == target) {
                break;
            }
            if (temp.next.val == target) {
                break;
            }
            temp = temp.next;

        }
        return temp;
    }

}

class Node {
    int val;
    Node next;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node next) {

        this.next = next;
        this.val = val;

    }

    boolean hasNext(Node A) {
        return A != null;
    }

    static int length(Node a) {
        int l = 0;
        while (a != null) {
            l++;
            a = a.next;
        }
        return l;
    }

    static int remove(Node a, int index) {
        int ind = 1;
        int res = -1;
        while (ind + 1 < index) {
            a = a.next;
            ind++;
        }
        if (a.next.next != null) {
            a.next = a.next.next;
            res = a.next.next.val;
        } else {
            res = a.next.val;
            a.next = null;
        }
        return res;
    }

    static int get(Node a, int index) {
        int ind = 1;
        Node temp = a;
        while (ind < index) {
            temp = temp.next;
            ind++;
        }
        return temp.val;
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

class DoubleLink {
    long val;
    DoubleLink next;
    DoubleLink pre;
    int index;

    public DoubleLink(long val, int index) {
        this.val = val;
        this.index = index;
    }
}
