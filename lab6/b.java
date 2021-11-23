package lab6;

public class b {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        String p=in.next();

        int[][] trans=new int[p.length()][26];

        for(int i=0;i< trans.length;i++){

        }



        for(int i=0;i< trans.length;i++){
            for(int j=0;j<trans[i].length;j++){
                out.print(trans[i][j]+" ");
            }
            out.println("");
        }

        out.close();
    }

}
