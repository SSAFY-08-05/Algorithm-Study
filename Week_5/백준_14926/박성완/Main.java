//package week5.bj_14926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 메모리 23348	시간 232
// 백준 14926 not equal
public class Main {
	// 전역변수 선언
	static int N;
	static boolean table[][];
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		// 오일러 탐색 실행
		euler(N);
		// 결과 출력
		System.out.println(sb);
		br.close();
	}
	static void euler(int n) {
		// 방문배열을 생성하고
		table = new boolean[n+1][n+1];
		// 본인은 탐색할 일이 없으므로 true
		for (int i = 1; i <= n; i++) table[i][i] = true;
		// 왼쪽최하단, 오른쪽 최상단은 마지막에 탐색해야 하므로 방문처리
		table[n][1] = table[1][n] = true;
		// 1번부터 탐색
		int cur = 1;
		sb.append("a1 ");
		// 총 횟수는 1~n-1을 더한 값이다. 하지만 1은 앞뒤로 처리해주므로 1~N-1까지 더한 값에서 1을 뺀만큼 수행한다.
		for (int i = 0,amount = n*(n-1)/2-1; i < amount; i++) {
			int next = -1;
			// 각 행의 처음으로 방문하지 않은 곳을 다음으로 지정
			for (int j = 1; j <= n; j++) {
				if(!table[cur][j]) {
					next = j;
					break;
				}
			}
			// 방문처리하고
			table[cur][next] = table[next][cur] = true;
			// 결과를 더한다.
			sb.append("a" + next + " ");
			cur = next;
		}
		
		sb.append("a1\n");
	}
}
