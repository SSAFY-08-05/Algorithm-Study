package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1717_집합의표현 { // union-find문제

	static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집합원소개수

		parent = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}

		int M = Integer.parseInt(st.nextToken()); // 연산 개수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken()); // 0 or 1
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			switch(cal) {
			case 0:
				union(find(a),find(b));
				break;
			case 1:
				if(find(a)==find(b)) //시작되는 부모가 같다면
					System.out.println("YES");
				else
					System.out.println("NO");
				break;
			}
			

		}
	}
	
	public static void union(int a, int b) {
		parent[a] = b;
	}
	
	public static int find(int a) {
		if(parent[a]==a) //자기 자신이 부모라면
			return a;
		else
			return parent[a] = find(parent[a]); //부모가 아니면 그 위로 찾아가기
	}

}
