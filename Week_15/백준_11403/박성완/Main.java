package week15.bj_11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 13940	시간 128
// 백준 11403 경로 찾기
public class Main {
	static int N, D[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		D = new int[N][N];
		
		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(st.nextToken().equals("1")) 
					D[i][j] = 1;
				else
					D[i][j] = 100_000_000;
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(D[i][j] > D[i][k] + D[k][j])
						D[i][j] = D[i][k] + D[k][j];
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(D[i][j] >= 100_000_000) sb.append("0 ");
				else sb.append("1 ");
			}
			sb.append("\n");
		}
		sb.append("\n");
		
		System.out.println(sb);
		br.close();
	}

}
