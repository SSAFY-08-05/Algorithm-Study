import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][2][3];  // day, 지각 일수, 연속 결석 일수

        for(int i = 0; i <= n; i++){
            for(int j = 0; j < 2; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    public static int dfs(int day, int late, int absent){
        if(late > 1 || absent > 2) return 0;
        if(day > n-1) return 1;

        if(dp[day][late][absent] != -1) return dp[day][late][absent];

        dp[day][late][absent] = dfs(day+1, late, 0)  // 출석
                + dfs(day+1, late+1, 0) // 지각
                + dfs(day+1, late, absent+1);  // 결석

        return dp[day][late][absent] % 1000000;
    }

}
