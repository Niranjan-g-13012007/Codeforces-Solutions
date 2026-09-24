import java.util.*;
public class Main {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int min=Integer.MAX_VALUE;
      HashSet<Integer>hs=new HashSet<>();
      for(int i=0;i<n;i++){
        int x=sc.nextInt();
        hs.add(x);
        min=Math.min(min,x);
      }
      if(hs.size()==1){
        System.out.println("NO");
        return;
      }
      int second=Integer.MAX_VALUE;
      for(int i:hs){
        if(i>min){
          second=Math.min(second,i);
        }
      }
      System.out.println(second);
    }
}