import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        s=Integer.parseInt(br.readLine());
        int []dp=new int[1001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[1]=1;
        for(int i=2; i<=s; i++) {
            dp[i]=Math.min(i, dp[i]);

            for(int j=2; j<=500; j++) { //곱할 수
                if(i*j>1000) break;
                dp[i*j]=Math.min(dp[i*j], dp[i]+j);
                dp[i*j-1]=Math.min(dp[i*j-1], dp[i*j]+1); //하나 삭제할 때..
            }
        }

        System.out.println(dp[s]);
    }
}
