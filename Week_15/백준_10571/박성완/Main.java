package week15.bj_10571;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 13416	시간 104
// 백준 10571 다이아몬드
public class Main {
	static int TC, N , dp[], best;
	static Diamond data[];
	static StringBuilder sb;
	static class Diamond{
		float w, c;

		public Diamond(float w, float c) {
			super();
			this.w = w;
			this.c = c;
		}
	}
	
	static boolean isBetter(Diamond a, Diamond b) {
		if( a.w < b.w && a.c > b.c) return true;
		return false;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < TC; t++) {
			
			N = Integer.parseInt(br.readLine());
			data = new Diamond[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				data[i] = new Diamond(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
			}
			
			
			dp = new int[N];
			dp[0] = 1;
			best = 1;
			
			for (int i = 1; i < N; i++) {
				int tmp = 0;
				for (int j = 0; j < i; j++) {
					if ( isBetter(data[j],data[i]))
						tmp = Math.max(tmp, dp[j]);
				}
				dp[i] = tmp + 1;
				
				best = Math.max(best, dp[i]);
			}
			sb.append(best).append("\n");
//			for (int i : dp) {
//				System.out.print(i + " ");
//			}
//			System.out.println();
		}
		System.out.println(sb);
		br.close();
	}

}
