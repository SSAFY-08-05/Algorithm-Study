import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        int dp[][]=new int[3][1001]; //마지막 날이 출/지/결일 때 n일까지 개근상 받을 수 있는 경우의 수
        int mod=1000000;

        dp[0][0]=1;
        dp[1][0]=1;
        dp[2][0]=1;

        dp[0][1]=1;
        dp[1][1]=1;
        dp[2][1]=1;

        dp[0][2]=3;
        dp[1][2]=2;
        dp[2][2]=3;

        for(int i=3; i<=n; i++) {
            dp[0][i]=(dp[0][i-1]+dp[1][i-1]+dp[2][i-1])%mod;
            dp[1][i]=(dp[1][i-3]+dp[1][i-2]+dp[1][i-1])%mod;
            dp[2][i]=(dp[0][i-2]+dp[1][i-2]+dp[0][i-1]+dp[1][i-1])%mod;
        }

        System.out.println((dp[0][n]+dp[1][n]+dp[2][n])%mod);

    }
}
