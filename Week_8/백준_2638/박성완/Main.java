//package week8.bj_2638;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 메모리 14636	시간 132
// 백준 2638 치즈
public class Main {
	// 전역변수 선언
	static int N,M,bcnt,time;
	static Block[][] map;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	// 단계별로 치즈를 뺄 큐 
	static Queue<Point> cqueue;
	// 데이터를 저장할 클래스 요소
	static class Block{
		short near;
		boolean type;

		public Block(boolean type) {
			super();
			this.near = 0;
			this.type = type;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// 전역변수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Block[N][M];
		// 입력 및 초기 데이터 생성
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				switch(st.nextToken()) {
				case "1":
					map[i][j] = new Block(true);
					bcnt++;
					break;
				default:
					map[i][j] = new Block(false);
					break;
				}
			}
		}
		// 테두리는 항상 치즈가 없으므로 바깥 아무 군데에서 초기화한다.
		bfs(0,0);
		cqueue = new LinkedList<>();
		// 치즈가 다 없어질 때 까지 단계별로 진행
		while(bcnt > 0) {
			// 단계별로 맞닿은 면이 2이상이면 큐에 추가
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != null && map[i][j].near>=2) {
						cqueue.offer(new Point(i,j));
					}
				}
			}
			// 큐에 있는 위치를 빼면서 상태 변경
			while(!cqueue.isEmpty()) {
				cheese(cqueue.poll());
				bcnt--;
			}
			// 단계마다 시간 증가
			time++;
		}
		
		System.out.println(time);		
	}
	// 새로 열리는 공간이 생이면 공간을 bfs로 탐색하면서 맞닿은 면적을 증가시켜준다.
	static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x,y));
		map[x][y] = null;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			// 4방탐색을 하면서 빈공간이면 null로 만들고, 치즈라면 near요소 증가
			for (int k = 0; k < 4; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				
				if(!isRange(ii,jj) || map[ii][jj] == null) continue;
				
				if(map[ii][jj].type) {//치즈
					map[ii][jj].near++;
				}else {//빈칸
					map[ii][jj] = null;
					queue.offer(new Point(ii,jj));
				}
			}
		}
		
	}
	// 치즈가 사라질 때 상태 변경
	static void cheese(Point p) {
		map[p.x][p.y] = null;
		// 4방 탐색을 하면서 
		for (int k = 0; k < 4; k++) {
			int ii = p.x + dx[k];
			int jj = p.y + dy[k];
			
			if(!isRange(ii,jj) || map[ii][jj] == null) continue;
			
			if(map[ii][jj].type) {
				map[ii][jj].near++;
			}else {
				//뚫린 공간이 생겼다면 bfs를 호출하여 공간들을 null로 바꾸고 상태 변경
				map[ii][jj] = null;
				bfs(ii,jj);
			}
		}
	}
	static boolean isRange(int x, int y) {
		if(x<0||x>=N||y<0||y>=M) return false;
		return true;
	}
}
