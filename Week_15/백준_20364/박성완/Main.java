package week15.bj_20364;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 44528	시간 448
// 백준 20364  부동산 다툼
public class Main {
	static int N, Q, needs[], goal, cur, which;
	static boolean visited[], swit;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		//needs = new int[Q];
		visited = new boolean[N+1];
		
		for (int i = 0; i < Q; i++) {
			goal = Integer.parseInt(br.readLine().trim());
			cur = goal;
			swit = false;
			which= -1;
			
			while(cur > 0) {
				if(visited[cur]) {
					swit = true;
					which = cur;
				}
				cur/=2;				
			}
			
			if(!swit) {
				sb.append(0).append("\n");
				visited[goal] = true;
			}
			else sb.append(which).append("\n");
			
		}
		
		System.out.println(sb);
		br.close();
	}

}
