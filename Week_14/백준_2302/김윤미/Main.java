import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());

        int dp[]=new int[n+1];

        dp[0]=1;
        dp[1]=1;

        for(int i=2; i<=n; i++) {
            dp[i]=dp[i-2]+dp[i-1];
        }

        long ans=1;
        int start=0;
        for(int i=0; i<m; i++) {
            int num=Integer.parseInt(br.readLine());
            ans*=dp[num-start-1];
            start=num;
        }
        ans*=dp[n-start];
        System.out.println(ans);
    }
}
