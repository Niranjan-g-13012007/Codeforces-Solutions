import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- > 0) {
            int n=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=sc.nextInt();
            }
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    if(arr[j]>arr[i]){
                        arr[j]=arr[i];
                        break;
                    }
                }
            }
            long sum=0;
            for(int i=0;i<n;i++){
                sum+=arr[i];
            }
            System.out.println(sum);
        }
        sc.close();
    }
}