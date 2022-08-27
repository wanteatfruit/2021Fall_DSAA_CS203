import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QReader read = new QReader();
        QWriter write = new QWriter();
        int n = read.nextInt(); // terms of first
        int m = read.nextInt(); // terms of second
        ListNode head1 = new ListNode(2000000000, 2000000000);
        ListNode current1 = head1;
        for (int i = 0; i < n; i++) {
            int coefficient = read.nextInt();
            int exp = read.nextInt();

            current1.next = new ListNode(coefficient, exp);
            current1 = current1.next;
        }
        current1.next = new ListNode(-2000000000, -2000000000);
        current1 = head1;

        for (int i = 0; i < m; i++) {
            int coefficient = read.nextInt();
            int exp = read.nextInt();
            while (current1.next.exp >= exp) {
                current1 = current1.next;
            }
            if (current1.exp == exp) {
                current1.coe += coefficient;
            } else {
                ListNode node = new ListNode(coefficient, exp);
                node.next = current1.next;
                current1.next = node;
                current1 = current1.next;

            }

        }

        int count = 0;

        current1 = head1.next;
        while (current1.next != null) {
            int coe = -2000000000;
            if (current1.coe == 0) {
                current1 = current1.next;
                continue;
            }
            count++;
            current1 = current1.next;
        }
        write.println(count);

        current1 = head1.next;
        while (current1.next != null) {
            int coe = -2000000000;
            if (current1.coe == 0) {
                current1 = current1.next;
                continue;
            }
            count++;
            write.print(current1.coe + " " + current1.exp);
            write.println("");
            current1 = current1.next;
        }

        write.close();
    }

}

class ListNode {
    int coe;
    int exp;
    ListNode next;

    ListNode() {
    }

    ListNode(int coe, int exp) {
        this.coe = coe;
        this.exp = exp;
    }

    ListNode(int coe, int exp, ListNode next) {

        this.next = next;
        this.coe = coe;
        this.exp = exp;

    }

    static void remove(ListNode A, int index) {
        A.next = A.next.next;
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
