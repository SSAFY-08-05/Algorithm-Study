package week8.bj_1563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 백준 1563 개근상
// 1 O 2 L 3 A
// 메모리 11552	시간 80
public class Main {
	static int N, count;
	static long dp[][]; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new long[N][7];

		// 지각 없는 경우 (0~2)
		dp[0][0] = 1; dp[0][1] = 0; dp[0][2] = 1;
		// ~a			~aa			~o				
		// 지각 하나인 경우 (3~6)으로 나눈다.
		 dp[0][3] = 0; dp[0][4] = 0; dp[0][5] = 0; dp[0][6] = 1;
		//~a			~aa				~o			~l
		fillDP();
		
		System.out.println(sumResult()%1000000);
		
	}
	static void fillDP() {
		for (int i = 1; i < N; i++) {
			dp[i][0] = (dp[i-1][2])%1000000;
			dp[i][1] = (dp[i-1][0])%1000000;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%1000000;
			dp[i][3] = (dp[i-1][5] + dp[i-1][6])%1000000;
			dp[i][4] = (dp[i-1][3])%1000000;
			dp[i][5] = (dp[i-1][3] + dp[i-1][4] + dp[i-1][5] + dp[i-1][6])%1000000;
			dp[i][6] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%1000000;
		}
	}
	static long sumResult() {
		return dp[N-1][0] + dp[N-1][1] + dp[N-1][2] + dp[N-1][3] + dp[N-1][4] + dp[N-1][5] + dp[N-1][6] ;
	}
}
