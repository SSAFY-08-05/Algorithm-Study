import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		
		arr = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());			
			for(int j =1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[][][] dp = new int[N+1][N+1][3]; 
		
		dp[1][2][0] = 1;
		
		for(int i = 1; i <=N; i++ ) {
			for(int j = 2; j <= N; j++) {
				if(i==1 && j==2)continue;
				
				if(arr[i][j]==0) {
					dp[i][j][0]= dp[i][j-1][0] + dp[i][j-1][2];
				
					dp[i][j][1]= dp[i-1][j][1] + dp[i-1][j][2];
					
					if(arr[i-1][j] == 0 && arr[i][j-1]==0) {
						dp[i][j][2]= dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2];
					}
				}
								
			}
		}
		
		int sum = dp[N][N][0]+dp[N][N][1]+dp[N][N][2];		
		System.out.println(sum);
	}

}
