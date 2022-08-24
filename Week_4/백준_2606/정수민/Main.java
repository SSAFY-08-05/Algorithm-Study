package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_2606_바이러스 {
	static int N, M, ans;
	static boolean visited[];
	static ArrayList<Integer> graph[];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //컴퓨터 수
		M = Integer.parseInt(br.readLine()); //연결 수
		visited = new boolean[N];
		graph = new ArrayList[N]; // 컴퓨터 수만큼 ArrayList 배열 생성
		
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>(); // 컴퓨터 수만큼 배열 생성
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b); // 연결을 넣어줌
			graph[b].add(a);
		}
		ans = 0;
		
		dfs(0,0);
		
		for (int i = 0; i < N; i++) {
			if(visited[i])
				ans++;
		}
		System.out.println(ans-1); //1번 컴퓨터는 제외

	}
	static void dfs(int count, int start) {

		int size = graph[start].size(); // 현재 컴퓨터와 연결된 개수

		for (int i = 0; i < size; i++) {
			int cur = graph[start].get(i); // 연결된 컴퓨터들을 순서대로 탐색

			if (visited[cur]) {// 이미 다른 곳에서 연결돼서 탐색했을 경우 visited 체크됨
				continue;
			} else {
				visited[cur] = true;
				dfs(count + 1, cur); // 이동한 노드에서 재귀 호출
			}
		}

	}

}
