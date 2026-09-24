import java.util.*;
public class Main {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      sc.nextLine();
      int x=0;
      for(int i=0;i<n;i++){
        String str=sc.nextLine();
        if(str.charAt(0)=='X' && str.charAt(2)=='+'){
          x++;
        }
        else if(str.charAt(0)=='X' && str.charAt(2)=='-'){
          x--;
        }
        else if(str.charAt(0)=='+' && str.charAt(2)=='X'){
          x++;
        }
        else if(str.charAt(0)=='-' && str.charAt(2)=='X'){
          x--;
        }
      }
      System.out.println(x);
    }
}