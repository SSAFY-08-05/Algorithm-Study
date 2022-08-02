//package week1.bj_11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 백준 11053 가장긴 증가하는 부분 수열
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] data = new String[N];
		data = br.readLine().split(" ");
		
		int logic[][] = new int[2][N];
		
		logic[0][0] = Integer.parseInt(data[0]);
		logic[1][0] = 1;
		int maxV = -1;
		int ans = 1;
		
		for (int i = 1; i < N; i++) {
			logic[0][i] = Integer.parseInt(data[i]);
			logic[1][i] = 1;
			maxV = -1;
			for (int j = 0; j < i; j++) {
				if (logic[0][j] < logic[0][i]) {
					maxV = Math.max(maxV, logic[1][j]);
				}
			}
			
			if (maxV==-1) logic[1][i] = 1;
			else logic[1][i] = maxV + 1;
			
			ans = Math.max(ans, logic[1][i]);
		}
		

		System.out.println(ans);
	}
}
