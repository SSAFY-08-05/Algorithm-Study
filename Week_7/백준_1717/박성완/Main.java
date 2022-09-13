//package week7.bj_1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 52532	시간 404
// 백준 1717 집합의 표현
public class Main {
	// 전역변수
	static int N,M,p[];
	static StringBuilder sb;
	// 부모를 찾는 함수
	static int find(int node) {
		if(p[node] == node) return node;
		else return p[node] = find(p[node]);
	}
	// 합집합 연산
	static void union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot != broot) p[aroot] = broot;
	}
	// 같은 부모인지 판별하는 
	static boolean isUnion(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		
		if(aroot == broot) return false;
		else return true;
	}
	public static void main(String[] args) throws IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 부모 배열 선언 및 초기화
		p = new int[N+1];
		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}
		
		// 조건에 따라 메소드 실행
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String comm = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(comm.equals("0")) union(a,b);
			else {
				if(isUnion(a,b)) sb.append("NO\n");
				else sb.append("YES\n");
			}
			
		}
		System.out.println(sb);
		br.close();
	}

}
