//package week5.bj_17070;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 메모리11784	시간 84
// 백준 17070 파이프 옮기기
public class Main {
	// 전역변수 선언
	static int N;
	static Block[][] map;
	static boolean visited[][];
	// 블록 클래스 선언
	static class Block{
		int hori, vert, slide; // 가로, 세로, 대각선
		boolean wall; // 벽인지 여부
		public Block(int hori, int vert, int slide, boolean wall) {
			super();
			this.hori = hori;
			this.vert = vert;
			this.slide = slide;
			this.wall = wall;
		}
		// 합을 구하는 메소드
		public int getSum() {
			return this.hori + this.vert + this.slide;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		// 맵 입력받고 초기화
		map = new Block[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				boolean b = st.nextToken().equals("1") ? true : false;
				map[i][j] = new Block(0,0,0,b);
			}
		}
		// 시작 위치 세팅. 파이프의 가장 오른쪽 하단을 기준으로 둔다.
		map[0][1].hori = 1;
		bfs();
		// 최하단 위치 출력
		System.out.println(map[N-1][N-1].getSum());
		
		br.close();
	}
	// bfs 알고리즘
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0,1));
		visited = new boolean[N][N];
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if(isGoal(cur.x, cur.y)) return;
			if(visited[cur.x][cur.y]) continue;
						
			searchWays(cur.x,cur.y);
			
			visited[cur.x][cur.y] = true;
			if(cur.y + 1 < N && !map[cur.x][cur.y+1].wall) {
				queue.offer(new Point(cur.x,cur.y+1));
			}
				
			if(cur.x + 1 < N && !map[cur.x+1][cur.y].wall) {
				queue.offer(new Point(cur.x+1,cur.y));
			}
							
		}
	}
	// 마지막 위치인지 판별
	static boolean isGoal(int x, int y) {
		if(x==N-1 && y==N-1) return true;
		else return false;
	}
	// 해당 위치가 갈 수 있는 곳인지
	static boolean canGo(int x, int y ) {
		if(x >= N || y >= N || map[x][y].wall) return false;
		return true;
	}
	// 3방향으로 갈 수 있는 곳인지
	static void searchWays(int x, int y) {
		Block cur = map[x][y];
		//가로 찾기
		// 오른쪽이 갈 수 있고, 오른쪽이 골이거나 오른쪽의 오른쪽도 갈 수 있는 곳일 때
		if(canGo(x,y+1) && (isGoal(x,y+1) || canGo(x,y+2))) {
			map[x][y+1].hori += cur.hori + cur.slide;
		}
		//세로 찾기
		// 아래가 갈 수 있고, 아래가 골이거나 아래의 아래도 갈 수 있는 곳이면
		if(canGo(x+1,y) && (isGoal(x+1,y) || canGo(x+2,y))) {
			map[x+1][y].vert += cur.vert + cur.slide;
		}
		
		//대각선 찾기
		// 오른쪽, 아래, 대각선 모두 갈 수 있고,
		if((canGo(x+1,y) && canGo(x,y+1) && canGo(x+1,y+1)) 
				// 대각선이 골이거나 대각선의 오른쪽/대각선의 아래 둘 중 한 곳을 갈 수 있을 때
				&& (isGoal(x+1,y+1) || (canGo(x+2,y+1) || canGo(x+1,y+2)))) {
			map[x+1][y+1].slide += cur.getSum();
		}
	}
}
