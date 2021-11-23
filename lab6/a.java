package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class a {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String string=s.next();
        int[] res=new int[5110];
        ArrayList<String> arrayList=new ArrayList<>();
        int len=1;
        int ind=0;
        int start = 0,end = 0;
        for(len=1;len<=string.length();len++){
            start=0; end=start+len;

            while(end<string.length()){
                end=start+len;
                if(!arrayList.contains(string.substring(start,end))){
                    arrayList.add(string.substring(start,end));
                }
                start++;
            }
        }

        System.out.println(arrayList.size()+1);
    }
}
