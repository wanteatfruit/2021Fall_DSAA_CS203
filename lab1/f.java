import java.util.Scanner;
 
//c发散,c/x收敛,csinx收敛,ccosx收敛,c/sinx发散,c/cosx发散,c^x：当c大于1发散,cx发散
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String[] result = new String[t];
 
        for (int i = 0; i < t; i++) {
            String function = sc.next();
            if (function.equals("0")) {
                result[i] = "yes";
            } else {
                String[] strings = function.split("[+]");
                int count=0;
                for (int j = 0; j < strings.length; j++) {
 
 
                    if(strings[j].charAt(0)=='0') {
                        count++;
                    }
                }
                if(count==strings.length){
                    result[i]="yes";
                }
                else result[i]="no";
            }
 
        }
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
 
    }
}
