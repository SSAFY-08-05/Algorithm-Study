//package week6.bj_14442;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 14442 벽 부수고 이동하기
public class Main {
	static int N,M,K;
	static boolean map[][], visited[][][];
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	static class Info{
		int x,y,cnt,path;

		public Info(int x, int y, int cnt, int path) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.path = path;
		}

		@Override
		public String toString() {
			return "Info [x=" + x + ", y=" + y + ", cnt=" + cnt + ", path=" + path + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		map = new boolean[N][M];
		visited = new boolean[N][M][K+1];
		
		for (int i = 0; i < N; i++) {
			String buffer = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				if(buffer.charAt(j) == '1') map[i][j] = true;
			}
		}
		
		System.out.println(bfs());
		
		br.close();
	}
	
	static int bfs() {
		Queue<Info> queue = new LinkedList<>();
		visited[0][0][0] = true;
		queue.offer(new Info(0,0,0,1)); // x,y,cnt,path
		while(!queue.isEmpty()) {
			Info cur = queue.poll();
			if(cur.x == N-1 && cur.y == M-1) {
				return cur.path;
			}
			
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				if(ii<0 || ii >= N || jj <0 || jj >= M) continue;				
				
				if(map[ii][jj]) {
					if( cur.cnt < K && !visited[ii][jj][cur.cnt+1]) {
						queue.offer(new Info(ii,jj,cur.cnt+1,cur.path+1));
						visited[ii][jj][cur.cnt+1] = true;
					}
				}
				else {
					if (!visited[ii][jj][cur.cnt]) {
						queue.offer(new Info(ii,jj,cur.cnt,cur.path+1));
						visited[ii][jj][cur.cnt] = true;
					}
				}
			}
		}
		return -1;
	}
}
