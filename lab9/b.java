import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] steps = new int[65][65];
    static int res = 0;
    static boolean flag = false;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String start = in.next();
            String end = in.next();
            int row = 0;
            int col = 0;
            int endRow = 0;
            int endCol = 0;
            Node[][] nodes = new Node[65][65];
            for (int j = 0; j < steps.length; j++) {
                for (int k = 0; k < steps.length; k++) {
                    steps[j][k] = 0;
                }
            }
            for (int j = 0; j < nodes.length; j++) {
                for (int N = 0; N < nodes.length; N++) {
                    nodes[j][N] = new Node();
                    nodes[j][N].row = j;
                    nodes[j][N].col = N;
                }
            }
            res = 0;
            flag = false;
            switch (start.charAt(0)) {
                case 'a':
                    col = 11;
                    break;
                case 'b':
                    col = 12;
                    break;
                case 'c':
                    col = 13;
                    break;
                case 'd':
                    col = 14;
                    break;
                case 'e':
                    col = 15;
                    break;
                case 'f':
                    col = 16;
                    break;
                case 'g':
                    col = 17;
                    break;
                case 'h':
                    col = 18;
                    break;
            }
            switch (start.charAt(1)) {
                case '8':
                    row = 11;
                    break;
                case '7':
                    row = 12;
                    break;
                case '6':
                    row = 13;
                    break;
                case '5':
                    row = 14;
                    break;
                case '4':
                    row = 15;
                    break;
                case '3':
                    row = 16;
                    break;
                case '2':
                    row = 17;
                    break;
                case '1':
                    row = 18;
                    break;
            }
            switch (end.charAt(1)) {
                case '8':
                    endRow = 11;
                    break;
                case '7':
                    endRow = 12;
                    break;
                case '6':
                    endRow = 13;
                    break;
                case '5':
                    endRow = 14;
                    break;
                case '4':
                    endRow = 15;
                    break;
                case '3':
                    endRow = 16;
                    break;
                case '2':
                    endRow = 17;
                    break;
                case '1':
                    endRow = 18;
                    break;
            }
            switch (end.charAt(0)) {
                case 'a':
                    endCol = 11;
                    break;
                case 'b':
                    endCol = 12;
                    break;
                case 'c':
                    endCol = 13;
                    break;
                case 'd':
                    endCol = 14;
                    break;
                case 'e':
                    endCol = 15;
                    break;
                case 'f':
                    endCol = 16;
                    break;
                case 'g':
                    endCol = 17;
                    break;
                case 'h':
                    endCol = 18;
                    break;
            }
            bfs(nodes, row, col, endRow, endCol);
            out.println(steps[endRow][endCol]);
        }
        out.close();
    }

    static void bfs(Node[][] board, int row, int col, int endRow, int endCol) { // row-1,row+1,col+2,col-2,row+2,row-2
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(board[row][col]);
        board[row][col].isVisited = true;
        while (!nodes.isEmpty()) {
            Node cur = nodes.poll();
            row = cur.row;
            col = cur.col;
            if (row < 11 || row > 19 || col < 11 || col > 19) {
                continue;
            }
            Node nxt1 = board[row - 1][col + 2];
            Node nxt2 = board[row - 1][col - 2];
            Node nxt3 = board[row + 1][col + 2];
            Node nxt4 = board[row + 1][col - 2];
            Node nxt5 = board[row - 2][col - 1];
            Node nxt6 = board[row - 2][col + 1];
            Node nxt7 = board[row + 2][col - 1];
            Node nxt8 = board[row + 2][col + 1];
            if (!nxt1.isVisited && nxt1.row >= 11 && nxt1.col <= 18) {
                nodes.add(nxt1);
                nxt1.isVisited = true;
                steps[nxt1.row][nxt1.col] = steps[row][col] + 1;
            }
            // if (nxt1.col == endCol && nxt1.row == endRow) {
            // break;
            // }
            if (!nxt2.isVisited && nxt2.row >= 11 && nxt2.col >= 11) {
                nodes.add(nxt2);
                nxt2.isVisited = true;
                steps[nxt2.row][nxt2.col] = steps[row][col] + 1;
            }
            // if (nxt2.col == endCol && nxt2.row == endRow) {
            // break;
            // }
            if (!nxt3.isVisited && nxt3.row <= 18 && nxt3.col <= 18) {
                nodes.add(nxt3);
                nxt3.isVisited = true;
                steps[nxt3.row][nxt3.col] = steps[row][col] + 1;
            }
            // if (nxt3.col == endCol && nxt3.row == endRow) {
            // break;
            // }
            if (!nxt4.isVisited && nxt4.row <= 18 && nxt4.col >= 11) {
                nodes.add(nxt4);
                nxt4.isVisited = true;
                steps[nxt4.row][nxt4.col] = steps[row][col] + 1;
            }
            // if (nxt4.col == endCol && nxt4.row == endRow) {
            // break;
            // }
            if (!nxt5.isVisited && nxt5.row >= 11 && nxt5.col >= 11) {
                nodes.add(nxt5);
                nxt5.isVisited = true;
                steps[nxt5.row][nxt5.col] = steps[row][col] + 1;
            }
            // if (nxt5.col == endCol && nxt5.row == endRow) {
            // break;
            // }
            if (!nxt6.isVisited && nxt6.row >= 11 && nxt6.col <= 18) {
                nodes.add(nxt6);
                nxt6.isVisited = true;
                steps[nxt6.row][nxt6.col] = steps[row][col] + 1;
            }
            // if (nxt6.col == endCol && nxt6.row == endRow) {
            // break;
            // }
            if (!nxt7.isVisited && nxt7.row <= 18 && nxt7.col >= 11) {
                nodes.add(nxt7);
                nxt7.isVisited = true;
                steps[nxt7.row][nxt7.col] = steps[row][col] + 1;
            }
            // if (nxt7.col == endCol && nxt7.row == endRow) {
            // break;
            // }
            if (!nxt8.isVisited && nxt8.row <= 18 && nxt8.col <= 18) {
                nodes.add(nxt8);
                nxt8.isVisited = true;
                steps[nxt8.row][nxt8.col] = steps[row][col] + 1;
            }
            // if (nxt8.col == endCol && nxt8.row == endRow) {
            // break;
            // }

        }
        // if (row < 11 || row > 18 || col < 11 || col > 18) {
        // return;
        // }
        // if (row == endRow && col == endCol) {
        // res = steps[row][col];
        // flag = true;
        // return;
        // }

        // if(!flag && !board[row+1][col-2].isVisited){
        // steps[row+1][col-2]=steps[row][col]+1;
        // bfs(board,row+1,col-2,endRow,endCol);
        // }
        // if(!flag&& !board[row+1][col+2].isVisited) {
        // steps[row + 1][col + 2] = steps[row][col] + 1;
        // bfs(board, row + 1, col + 2, endRow, endCol);
        // }
        // if(!flag&& !board[row+2][col-1].isVisited) {
        // steps[row + 2][col - 1] = steps[row][col] + 1;
        // bfs(board, row + 2, col - 1, endRow, endCol);
        // }
        // if(!flag&& !board[row+2][col+1].isVisited) {
        // steps[row + 2][col + 1] = steps[row][col] + 1;
        // bfs(board, row + 2, col + 1, endRow, endCol);
        // }
        // if(!flag&& !board[row-1][col+2].isVisited) {
        // steps[row - 1][col + 2] = steps[row][col] + 1;
        // bfs(board, row - 1, col - 2, endRow, endCol);
        // }
        // if(!flag&& !board[row-1][col+2].isVisited) {
        // steps[row - 1][col + 2] = steps[row][col] + 1;
        // bfs(board, row - 1, col + 2, endRow, endCol);
        // }
        // if(!flag&& !board[row-2][col-1].isVisited) {
        // steps[row - 2][col - 1] = steps[row][col] + 1;
        // bfs(board, row - 2, col - 1, endRow, endCol);
        // }
        // if(!flag&& !board[row-2][col+1].isVisited) {
        // steps[row - 2][col + 1] = steps[row][col] + 1;
        // bfs(board, row - 2, col + 1, endRow, endCol);
        // }
    }
}

class Node {
    boolean isVisited = false;
    int row;
    int col;
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