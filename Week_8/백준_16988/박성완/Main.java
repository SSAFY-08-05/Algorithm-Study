package week8.bj_16988;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 백준 16988 Baaaaaaaaaduk2 (Easy)
// 메모리12000	시간 84
public class Main {
	// 전역변수 선언
	static int N,M,ans;
	static int[][] map;
	static boolean[][] visited;
	static int [] dx = {0,0,1,-1};
	static int [] dy = {1,-1,0,0};
	static Answer answer;
	// 1회차 bfs에 대한 결과지
	static class Answer{
		int two_top, one_top, one_sec;

		public Answer() {
			super();
			this.two_top = 0;
			this.one_top = 0;
			this.one_sec = 0;
		}
		// 주위의 0이 1개
		public void addOne(int num) {
			if(one_sec < num) {
				one_sec = num;
				if(one_top < one_sec) {
					int tmp = one_sec;
					one_sec = one_top;
					one_top = tmp;
				}
			}
		}
		// 주위의 0이 2개
		public void addTwo(int num) {
			this.two_top = Math.max(this.two_top, num);
		}

	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// 답지 생성
		answer = new Answer();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1차 bfs 탐색 : 2묶음 단위별로 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==2) {
					bfs1(i,j);
				}
			}
		}
		//2차  bfs 탐색 : 0을 통해서 이어진 2묶음이 있다면 같이 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==3) {
					bfs2(i,j);
				}
			}
		}
		// 최댓값 출력
		System.out.println(Math.max(answer.two_top, Math.max(ans, answer.one_top + answer.one_sec)));
		br.close();
	}
	// 검증 메소드
	static void print() {
		for(int arr[] : map) {
			for(int a : arr) System.out.print(a + " ");
			System.out.println();
		}
	}
	// 1차 bfs 탐색
	static void bfs1(int sx, int sy) {
		visited = new boolean[N][M];
		int cnt = 0;
		int zeroCnt = 0;
		Queue<Point> zero = new LinkedList<>();
		Queue<Point> queue = new LinkedList<>();
		map[sx][sy] = 3;
		visited[sx][sy] = true;
		queue.offer(new Point(sx,sy));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			visited[cur.x][cur.y] = true;
			cnt ++;
			
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				if(!isRange(ii,jj) || visited[ii][jj] || map[ii][jj]==1) continue;
				
				visited[ii][jj]=true;
				
				if(map[ii][jj]==2) {
					map[ii][jj] = 3;
					queue.offer(new Point(ii,jj));
				}else {
					zero.offer(new Point(ii,jj));
					zeroCnt++;
				}
				
			}
			
		}
		
		if(zeroCnt > 2 ) {
			while(!zero.isEmpty()) {
				Point z = zero.poll();
				visited[z.x][z.y] = false;
			}
			return;
		}
		while(!zero.isEmpty()) {
			Point z = zero.poll();
			visited[z.x][z.y] = false;
		}
		if(zeroCnt==1) {
			answer.addOne(cnt);
		}else { //zeroCnt==2
			answer.addTwo(cnt);
		}
		//System.out.println(cnt + " " + zeroCnt);
	}
	// 2차 bfs 탐색
	static void bfs2(int sx, int sy) {
		visited = new boolean[N][M];
		int cnt = 0;
		int zeroCnt = 0;
		Queue<Point> zero = new LinkedList<>();
		Queue<Point> queue = new LinkedList<>();
		map[sx][sy] = 4;
		visited[sx][sy] = true;
		queue.offer(new Point(sx,sy));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			visited[cur.x][cur.y] = true;
			if(map[cur.x][cur.y]==4) cnt ++;
			if(map[cur.x][cur.y]==0 && !look(cur.x,cur.y))continue;
			
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				if(!isRange(ii,jj) || visited[ii][jj] || map[ii][jj]==1) continue;
				
				visited[ii][jj]=true;
				
				if(map[ii][jj]==3) {
					map[ii][jj] = 4;
					queue.offer(new Point(ii,jj));
				}else if(map[ii][jj]==0) {
					zeroCnt++;
					zero.offer(new Point(ii,jj));
					queue.offer(new Point(ii,jj));
				}
				
			}
			
		}
		while(!zero.isEmpty()) {
			Point z = zero.poll();
			visited[z.x][z.y] = false;
		}
		
		if(zeroCnt <= 2 ) {
			ans = Math.max(ans, cnt);
		}
		//System.out.println(cnt + " " + zeroCnt);
	}
	// 2차 bfs 중 0에 대한 처리 .먼저 4방을 바라보고 주위에 2가 하나라도 있으면 true 반환
	static boolean look(int x, int y) {
		for (int k = 0; k < 4; k++) {
			int ii = x + dx[k];
			int jj = y + dy[k];
			if(isRange(ii,jj) && map[ii][jj]==3) return true;			
		}
		return false;
	}
	static boolean isRange(int x, int y) {
		if(x<0||x>=N||y<0||y>=M) return false;
		return true;
	}
}
