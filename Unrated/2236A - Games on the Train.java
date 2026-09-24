import java.util.*;
public class Main{
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- >0){
            int n=sc.nextInt();
            int min=Integer.MAX_VALUE;
            int max=Integer.MIN_VALUE;
            for(int i=0;i<n;i++){
                int a=sc.nextInt();
                min=Math.min(min,a);
                max=Math.max(max,a);
            }
            int res=(max-min)+1;
            System.out.println(res);
        }
        sc.close();
    }
}