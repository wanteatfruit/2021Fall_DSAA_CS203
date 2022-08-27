import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();

            DoubleLink head = new DoubleLink(0);

            DoubleLink cur = head;
            DoubleLink tail = new DoubleLink(10000000);
            int[] ins = new int[n];
            for (int j = 0; j < n; j++) {
                ins[j] = in.nextInt();
            }
            for (int j = 0; j < n; j++) {
                DoubleLink temp = cur;
                cur.next = new DoubleLink(ins[j]);
                cur = cur.next;
                cur.pre = temp;
            }

            DoubleLink temp = cur;
            cur.next = tail;
            cur = cur.next;
            cur.pre = temp;

            cur = head.next;

            ArrayList<DoubleLink> doubleLinks = new ArrayList<>();

            // first iteration
            boolean skip = false;
            int count = 0;
            while (cur.next != null && cur.pre != null) {
                if (cur.next.val >= cur.val && cur.pre.val <= cur.val) {
                    count = 0;
                }
                if (cur.val > cur.next.val) {
                    if (count == 0) {
                        doubleLinks.add(cur.pre);
                        count++;
                    }
                }
                cur = cur.next;
            }

            cur = head.next;

            while (cur.next != null && cur.pre != null) {
                if (skip) {
                    skip = false;
                    if (cur.val > cur.next.val) {
                        cur.pre.next = cur.next;
                    }
                    cur = cur.next;
                    continue;
                }
                if (cur.val < cur.pre.val) {
                    cur.pre = cur.pre.pre;
                    int skipped = cur.val;
                    if (cur.val <= cur.next.val) {
                        cur.pre.next = cur.next;
                        cur.next.pre = cur.pre;
                        cur = cur.next;
                        skip = true;
                        continue;
                    }
                }
                if (cur.val > cur.next.val) {

                    cur.pre.next = cur.next;
                }
                cur = cur.next;
            }
            skip = false;
            count = 0;
            doubleLinks.add(tail);

            while (doubleLinks.size() > 1) {

                ArrayList<DoubleLink> doubleLinks1 = new ArrayList<>();
                int count1 = 0;
                for (int j = 0; j < doubleLinks.size() - 1; j++) {

                    cur = doubleLinks.get(j);
                    if (cur.val > cur.next.val) {
                        if (doubleLinks1.size() == 0) {
                            doubleLinks1.add(cur.pre);
                            count1++;
                        } else if (doubleLinks1.get(count1 - 1) != cur.pre) {
                            doubleLinks1.add(cur.pre);
                            count1++;
                        }
                        cur.pre.next = cur.next;
                        cur = cur.next;
                        cur.pre = cur.pre.pre;
                        skip = true;
                        if (doubleLinks.get(j).next == doubleLinks.get(j + 1)) {
                            if (doubleLinks.size() % 2 == 0) {
                                if (j % 2 == 0)
                                    continue;
                                else if (doubleLinks.get(j + 1).val > doubleLinks.get(j + 1).next.val) {
                                    continue;
                                } else {
                                    cur.pre.next = cur.next;
                                    cur = cur.next;
                                    cur.pre = cur.pre.pre;
                                    j++;
                                    skip = false;
                                }
                            }

                            if (doubleLinks.size() % 2 == 1) {
                                if (j % 2 == 1)
                                    continue;
                                else if (doubleLinks.get(j + 1).val > doubleLinks.get(j + 1).next.val) {
                                    continue;
                                } else {
                                    cur.pre.next = cur.next;
                                    cur = cur.next;
                                    cur.pre = cur.pre.pre;
                                    j++;
                                    skip = false;
                                }
                            }
                        }
                    }
                    if (skip) {// detele next
                        skip = false;
                        cur.pre.next = cur.next;
                        cur = cur.next;
                        cur.pre = cur.pre.pre;
                    }
                }

                // cur=doubleLinks.get(doubleLinks.size()-1);
                // if(cur==head || cur==tail){
                // break;
                // }
                // if(doubleLinks1.size()==0){
                // doubleLinks1.add(cur.pre);
                // count1++;
                // }
                //
                // if(cur.val>cur.next.val){
                // if(doubleLinks1.size()>=1 && doubleLinks1.get(count1-1)!=cur.pre){
                // doubleLinks1.add(cur.pre);
                // }
                // cur.pre.next=cur.next;
                // cur=cur.next;
                // cur.pre = cur.pre.pre;
                // skip=true;
                //
                // }
                // if(skip){//detele next
                // skip=false;
                // cur.pre.next=cur.next;
                // cur=cur.next;
                // cur.pre=cur.pre.pre;
                // }
                doubleLinks1.add(tail);
                doubleLinks = doubleLinks1;

            }

            // delete(cur,tail,head);

            cur = head.next;
            while (cur.next != null) {
                out.print(cur.val + " ");
                cur = cur.next;
            }
            out.println("");

        }
        out.close();

    }

    static DoubleLink delete(DoubleLink cur, DoubleLink tail, DoubleLink head) {
        if (cur == tail) {
            return cur;
        }
        // cur=cur.next;
        int count = 0;// need find the first deleted
        DoubleLink res = tail;
        boolean skip = false;
        while (cur.next != null && cur.pre != null) {
            if (skip) {
                skip = false;
                if (cur.val > cur.next.val) {
                    cur.pre.next = cur.next;
                }
                cur = cur.next;
                continue;
            }
            if (cur.val < cur.pre.val) {
                cur.pre = cur.pre.pre;
                int skipped = cur.val;
                if (cur.val <= cur.next.val) {
                    cur.pre.next = cur.next;
                    cur.next.pre = cur.pre;
                    skip = true; // if 9 9 9 1 1 1, will skip second 1 ,if not skip will false delete second 1
                    cur = cur.next;
                    continue;
                }
            }
            if (cur.val > cur.next.val) {
                if (count == 0) {
                    res = cur.pre;
                } // if this is true, the previous one also true(when go to next node)
                cur.pre.next = cur.next;
                // if(res.val<cur.pre.val){
                // res=cur.pre;
                // }

                count++;
            }

            cur = cur.next;
        }
        return delete(res, tail, head);
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
    int val;
    DoubleLink next;
    DoubleLink pre;

    public DoubleLink(int val) {
        this.val = val;
    }
}