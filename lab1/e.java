import java.util.Scanner;
import java.util.Stack;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long l=0;
        long r=0;
        String[] strings=new String[10];
        int count=0;
 
         
        while (sc.hasNext()) {
            l = sc.nextLong();
            r = sc.nextLong();
            strings[count]=findLeft(l);
            count++;
            strings[count]=findRight(r);
            count++;
        }
        /*while (sc.hasNext()){
        l=sc.nextLong();
        r=sc.nextLong();
        System.out.println(findLeft(l)+" "+findRight(r));
        //System.out.println(findRight(r));
        }*/
 
        for(int i=0;i<strings.length;i=i+2){
            if(strings[i]!=null){
                System.out.println(strings[i]+" "+strings[i+1]);
            }
        }
    }
 
    public static String findLeft(long l){
        long origin=l;
 
        String s=String.valueOf(l);
        int weishu=0; //input weishu, before add
 
        if(l==0){
            return "0";
        }
        else if(l==1){
            return "1";
        }
        else if(l>1 && l<=8){
            return "8";
        }
        else if(l == 9){
            return "11";
        }
        else if(l>9 && l<=11){
            return "11";
        }
        else {
            while (l > 0) {
                l = l / 10;
                weishu++;
            }
        }
        String half="";
        if(weishu%2==0){
 
            half=s.substring(0,s.length()/2);
        }
        else half=s.substring(0,s.length()/2+1);
        //if(half.equals(""))
 
        int firstHalf=Integer.parseInt(half);
        String min="0";
 
 
        if(weishu%2==0 && evenLegal(half)){ //if 自带回文
            min=revertEven(half);
            if(firstHalf==Math.pow(10,weishu/2)-1){
                firstHalf++;
                weishu++;
            }
            else firstHalf++;
        }
        if(weishu%2==1 && oddLegal(half)){
            min=revertOdd(half);
            if(firstHalf==Math.pow(10,(weishu/2)+1)-1){
                firstHalf++;
                firstHalf=firstHalf/10;
                weishu++;
            }
            else             firstHalf++;
 
        }
        long temp=Long.parseLong(min);
 
 
 
        while (true){
            boolean isLegal;
 
            half=String.valueOf(firstHalf);
 
            //int count=0;
            if(weishu%2==0){//even num
 
                isLegal=evenLegal(half);
 
            }
            else { // odd
                isLegal=oddLegal(half);
            }
 
            if(isLegal) {
                break;
            }
 
            else { //jinwei
                if(firstHalf==Math.pow(10,weishu/2)-1 && weishu%2==0){ // even jinwei 4>5
                    firstHalf++;
                    weishu++;
                }
                if(firstHalf==Math.pow(10,(weishu/2)+1)-1&& weishu%2==1){ //odd jinwei
                    firstHalf++;
                    firstHalf=firstHalf/10;
                    weishu++;
                }
                else {
                    firstHalf++;
                }
 
            }
        }
 
        String legal=String.valueOf(firstHalf);
        if(weishu%2==0 && temp>=origin){
            return min;
        }
        if(weishu%2==0){
            return revertEven(legal);
        }
        if(weishu%2==1 && temp>=origin){
            return min;
        }
        if(weishu%2==1 ){
            return revertOdd(legal);
        }
        /*if(weishu%2==0 && Long.parseLong(revertEven(legal))<temp){
            return min;
        }
        if(weishu%2==0 && Long.parseLong(revertEven(legal))>=temp){
            return revertEven(legal);
        }
        if(weishu%2==1 && Long.parseLong(revertOdd(legal))<temp){
            return min;
        }
        if(weishu%2==1 && Long.parseLong(revertOdd(legal))>=temp){
            return revertOdd(legal);
        }*/
 
 
        return null;
 
    }
 
    public static String findRight(long r){
 
        long origin=r;
 
        String s=String.valueOf(r);
        int weishu=0; //input weishu, before add
        if(r==0) {
            return "0";
        }
        else if(r==1){
            return "1";
        }
        else if(r<11 && r>=8){
            return "8";
        }
        else if(r<8 && r>=1){
            return "1";
        }
        else if(r>=11 && r<69){
            return "11";
        }
        else {
            while (r > 0) {
                r = r / 10;
                weishu++;
            }
        }
        String half="";
        if(weishu%2==0){
 
            half=s.substring(0,s.length()/2);
        }
        else half=s.substring(0,s.length()/2+1);
 
        int firstHalf=Integer.parseInt(half);
 
 
        String min="1000000000000";
 
        if(weishu%2==0 && evenLegal(half)){ //if 自带回文
            min=revertEven(half);
            if(firstHalf==Math.pow(10,weishu/2-1)){
                firstHalf=firstHalf*10;
                firstHalf--;
                weishu--;
            }
            else firstHalf--;
        }
        else if(weishu%2==1 && oddLegal(half)){
            min=revertOdd(half);
            if(firstHalf==Math.pow(10,weishu/2)){
                firstHalf--;
                weishu--;
            }
            else      firstHalf--;
 
        }
        long temp=Long.parseLong(min);
 
 
 
 
        while (true){
            boolean isLegal;
            half=String.valueOf(firstHalf);
 
 
            //int count=0;
            if(weishu%2==0){//even num
 
                isLegal=evenLegal(half);
 
            }
            else { // odd
                isLegal=oddLegal(half);
            }
 
            if(isLegal) {
                break;
            }
 
            else { //tuiwei
                if(weishu%2==0 && firstHalf==Math.pow(10,weishu/2)){ //even tuiwei 10>99
 
                    firstHalf=firstHalf*10;
                    firstHalf--;
                    weishu--;
 
                }
                if(weishu%2==1 && firstHalf==Math.pow(10,weishu/2)){
                    firstHalf--;
                    weishu--;
                }
                else {
                    firstHalf--;
                }
 
            }
        }
 
        String legal=String.valueOf(firstHalf);
        if(weishu%2==0 && temp<=origin){
            return min;
        }
        if(weishu % 2 == 0){
            return revertEven(legal);
        }
        if(weishu%2==1 && temp<=origin){
            return min;
        }
        if(weishu % 2 == 1){
            return revertOdd(legal);
        }
        /*if(weishu%2==0 && Long.parseLong(revertEven(legal))>temp){
            return min;
        }
        if(weishu%2==0 && Long.parseLong(revertEven(legal))<=temp){
            return revertEven(legal);
        }
        if(weishu%2==1 && Long.parseLong(revertOdd(legal))>temp){
            return min;
        }
        if(weishu%2==1 && Long.parseLong(revertOdd(legal))<=temp){
            return revertOdd(legal);
        }*/
        return null;
 
    }
 
 
    public static String revertEven(String legal){
        Stack<String> stack=new Stack<>();
        String reverted="";
 
        for(int i=0;i<legal.length();i++){ //put into stack
            if(legal.charAt(i)=='6'){
                stack.add("9");
            }
            else if(legal.charAt(i)=='9'){
                stack.add("6");
            }
            else {
                stack.add(String.valueOf(legal.charAt(i)));
            }
        }
 
        while (!stack.empty()){
            reverted=reverted+stack.pop();
        }
 
        return legal+reverted;
 
    }
 
    public static String revertOdd(String legal){
 
        Stack<String> stack=new Stack<>();
        String reverted="";
 
        for(int i=0;i<legal.length()-1;i++){
            if(legal.charAt(i)=='6'){
                stack.add("9");
            }
            else if(legal.charAt(i)=='9'){
                stack.add("6");
            }
            else {
                stack.add(String.valueOf(legal.charAt(i)));
            }
        }
 
        while (!stack.empty()){
            reverted=reverted+stack.pop();
        }
        return legal+reverted;
 
 
    }
 
 
    public static boolean oddLegal(String half){
        boolean isLegal=true;
        char firstChar=half.charAt(0);
        if(firstChar!='1' && firstChar!= '6' && firstChar!='9' && firstChar!='8'){
            isLegal=false;
        }
        for(int i=1;i<half.length()-1;i++){
            if(half.charAt(i)!='1' && half.charAt(i)!='6' && half.charAt(i)!='0' && half.charAt(i)!='9' && half.charAt(i)!='8'){
                isLegal=false;
                break;
            }
        }
        if(half.charAt(half.length()-1)!='1' && half.charAt(half.length()-1)!='0' && half.charAt(half.length()-1)!='8'){
            isLegal=false;
        }
 
        return isLegal;
 
    }
 
    public static boolean evenLegal(String half){
        char firstChar=half.charAt(0);
        boolean isLegal=true;
        for(int i=1;i<half.length();i++){
            if(half.charAt(i)!='1' && half.charAt(i)!='6' && half.charAt(i)!='0' && half.charAt(i)!='9' && half.charAt(i)!='8'){
                isLegal=false;
                break;
            }
        }
        if(firstChar!='1' && firstChar!= '6' && firstChar!='9' && firstChar!='8'){
            isLegal=false;
        }
        return isLegal;
 
    }
 
 
}
 
