package lab6;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s = in.next();
        int len = s.length();
        int[][] trans = new int[len][26];
        char[] alphabet = new char[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) ('a' + i);
        }

        int[][] res = transition(s, trans, alphabet);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < 26; j++) {
                out.print(res[i][j] + " ");
            }
            out.println("");
        }

        out.close();
    }

    public static int suffix(String s, String p) { // p is pattern, s is entered
        int suflen = 0;
        Deque<Character> deque = new ArrayDeque<>();
        Stack<Character> cur = new Stack<>();
        for (int i = 0; i < p.length(); i++) {

        }

        return suflen;
    }

    static int[][] transition(String p, int[][] trans, char[] alp) {
        int m = p.length();
        int x = 0;
        char[] letters = new char[p.length() + 1];
        for (int i = 1; i < letters.length; i++) {
            letters[i] = p.charAt(i - 1);
        }
        for (int i = 0; i < 26; i++) {
            if (p.charAt(0) == alp[i]) {
                trans[0][i] = 1;
            } else
                trans[0][i] = 0;
        }
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < 26; i++) {
                if (letters[j + 1] == alp[i]) {
                    trans[j][i] = j + 1;
                } else {
                    trans[j][i] = trans[x][i];
                }
            }
            x = trans[x][letters[j + 1] - 'a'];
        }
        return trans;
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