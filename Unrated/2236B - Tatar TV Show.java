import java.util.*;
public class Main{
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- >0){
            int n=sc.nextInt();
            int k=sc.nextInt();
            String s=sc.next();
            int f=1;
            for(int i=0;i<k;i++){
                int one=0;
                for(int j=i;j<n;j+=k){
                    if(s.charAt(j)=='1'){
                        one++;
                    }
                }
                if(one%2==1){
                    f=0;
                    break;
                }
            }
            if(f==1){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
        sc.close();
    }
}