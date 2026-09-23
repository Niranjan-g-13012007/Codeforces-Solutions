import java.util.*;
public class Main {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      sc.nextLine();
      for(int i=0;i<n;i++){
        String str=sc.nextLine();
        int len=str.length();
        if(len<=10){
          System.out.println(str);
        }
        else{
          String first=String.valueOf(str.charAt(0));
          String last=String.valueOf(str.charAt(len-1));
          String mid=String.valueOf(len-2);
          System.out.println(first+mid+last);
        }
      }
    }
}