import java.io.*;
import java.util.*;

public class Main {
    static Queue<Long> queue = new PriorityQueue<>(Collections.reverseOrder());
    static ArrayList<Long> arrayList = new ArrayList<>();
    static long max = 0;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int l = 0; l < t; l++) {
            max = 0;
            int n = in.nextInt(); // rows
            int m = in.nextInt(); // cols
            long[][] matrix = new long[10][10];
            // TreeSet<Long> sums=new TreeSet<>();
            for (int j = 1; j < n + 1; j++) {
                for (int k = 1; k < m + 1; k++) {
                    matrix[j][k] = in.nextLong();
                }
            }
            // long max = 0;
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    queue = new PriorityQueue<>(Collections.reverseOrder());
                    int[][] select = new int[10][10];// 2 is not select, 1 is select, -1 is can't select, 0 is out of
                                                     // bound
                    for (int k = 1; k < n + 1; k++) {
                        for (int o = 1; o < m + 1; o++) {
                            select[k][o] = 2;
                        }
                    }
                    queue.add(matrix[i][j]);
                    findSum(matrix[i][j], select, i, j, matrix, n, m);

                    max = Math.max(max, queue.peek());
                }
            }
            out.println(max);

        }
        out.close();
    }

    static void findSum(long cursum,
            int[][] selection, int selRow, int selCol, long[][] matrix, int rowNum, int colNum) {// [0][selcol]
        // queue.add(cursum);
        boolean mark0 = false;
        boolean mark1 = false;
        boolean mark2 = false;
        boolean mark3 = false;
        boolean mark4 = false;
        boolean mark5 = false;
        boolean mark6 = false;
        boolean mark7 = false;
        boolean mark8 = false;
        if (selection[selRow][selCol] == 2) {
            mark0 = true;
        }
        if (selection[selRow + 1][selCol + 1] == 2) {
            mark1 = true;
        }
        if (selection[selRow + 1][selCol - 1] == 2) {
            mark2 = true;
        }
        if (selection[selRow - 1][selCol - 1] == 2) {
            mark3 = true;
        }
        if (selection[selRow - 1][selCol + 1] == 2) {
            mark4 = true;
        }
        if (selection[selRow][selCol + 1] == 2) {
            mark5 = true;
        }
        if (selection[selRow][selCol - 1] == 2) {
            mark6 = true;
        }
        if (selection[selRow - 1][selCol] == 2) {
            mark7 = true;
        }
        if (selection[selRow + 1][selCol] == 2) {
            mark8 = true;
        }
        selection[selRow][selCol] = -1;
        // long sum=matrix[selRow][selCol];
        selection[selRow + 1][selCol + 1] = -1;
        selection[selRow + 1][selCol - 1] = -1;
        selection[selRow - 1][selCol - 1] = -1;
        selection[selRow - 1][selCol + 1] = -1;
        selection[selRow][selCol + 1] = -1;
        selection[selRow][selCol - 1] = -1;
        selection[selRow - 1][selCol] = -1;
        selection[selRow + 1][selCol] = -1;
        long tmp = cursum;
        Queue<Long> firstThree = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 1; i < rowNum + 1; i++) {
            for (int j = 1; j < colNum + 1; j++) {
                if (selection[i][j] == 2) {
                    firstThree.add(matrix[i][j]);

                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < rowNum + 1; j++) {
                for (int k = 1; k < colNum + 1; k++) {
                    cursum = tmp;
                    if (selection[j][k] == 2 && firstThree.size() != 0 && firstThree.peek() == matrix[j][k]) {

                        cursum = cursum + matrix[j][k];
                        max = Math.max(max, cursum);
                        queue.add(cursum);
                        findSum(cursum, selection, j, k, matrix, rowNum, colNum);
                    }
                }
            }
            firstThree.poll();
        }
        if (mark0)
            selection[selRow][selCol] = 2;
        if (mark1)
            selection[selRow + 1][selCol + 1] = 2;
        if (mark2)
            selection[selRow + 1][selCol - 1] = 2;
        if (mark3)
            selection[selRow - 1][selCol - 1] = 2;
        if (mark4)
            selection[selRow - 1][selCol + 1] = 2;
        if (mark5)
            selection[selRow][selCol + 1] = 2;
        if (mark6)
            selection[selRow][selCol - 1] = 2;
        if (mark7)
            selection[selRow - 1][selCol] = 2;
        if (mark8)
            selection[selRow + 1][selCol] = 2;

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
