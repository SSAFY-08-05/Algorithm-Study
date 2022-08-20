//package week4.bj_2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2606 바이러스
public class Main {
	// 전역변수 선언
	static int N,E,infected;
	static boolean adjustMap[][], isVisited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 데이터 입력
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		// 인접 행렬 및 방문배열 생성
		adjustMap = new boolean[N+1][N+1];
		isVisited = new boolean[N+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjustMap[a][b] = adjustMap[b][a] = true;
		}
		// dfs알고리즘 실행
		dfs(1);
		// 1번을 제외한 갯수 출력
		System.out.println(infected-1);
	}
	static void dfs(int cur) {
		// 감염처리 및 방문 처리
		infected++;
		isVisited[cur] = true;
		// 이어진 간선이 있고 아직 방문하지 않았다면 dfs 재귀 호출
		for (int i = 1; i <= N; i++) {
			if(adjustMap[cur][i] && !isVisited[i]) {
				dfs(i);
			}
		}
	}
}
