package week13.bj_16954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
// 메모리 11612	시간 80
// 백준 16954 움직이는 미로 탈출
public class Main {
	// 전역변수 선언
	static final int SIZE = 8;
	static boolean map[][], visited[][][];
	static int blocknum;
	// 9방...
	static int dx[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 0, 1, 1, 1, 0, -1, -1, -1 };
	static Deque<Point> blocks;
	static class Point{
		int x,y,time;
		
		public Point(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	public static void main(String[] args) throws IOException {
		//입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[SIZE][SIZE];
		// 방문은 시간을 고려하여 3차원으로
		visited = new boolean[SIZE][SIZE][65];
		blocks = new LinkedList<>();
		for (int i = 0; i < SIZE; i++) {
			String buffer = br.readLine().trim();
			for (int j = 0; j < SIZE; j++) {
				if (buffer.charAt(j) == '#') {
					map[i][j] = true;
					blocks.offerFirst(new Point(i,j,0));
				}
			}
		}
		blocknum = blocks.size();
		// 시작 위치가 불가능이면 시작도 못한다.
		if(map[7][0]) System.out.println(0);
		else System.out.println(bfs()); // 결과 바로 탐색해서 출력

	}
	// bfs 탐색 알고리즘
	static int bfs() {
		int size = 0;
		// 부모 큐와 자식 큐
		Queue<Point> queue = new LinkedList<>();
		Queue<Point> nexts = new LinkedList<>();
		// 시작위치 마킹
		queue.offer(new Point(7,0,0));
		visited[7][0][0] = true;
		// 전체 큐가 빌때까지
		while(!queue.isEmpty()) {
			// 9방 이동
			while(!queue.isEmpty()) {
				Point cur = queue.poll();
				// 64는 그냥 64칸이라서..
				if(map[cur.x][cur.y] || cur.time >= 64) continue;
				// 도착하면 바로 1 반환
				if(cur.x == 0 && cur.y == 7) return 1;
				
				for (int k = 0; k < 9; k++) {
					int ii = cur.x + dx[k];
					int jj = cur.y + dy[k];
					
					if(!isRange(ii,jj) || map[ii][jj] || visited[ii][jj][cur.time+1]) continue;
					visited[ii][jj][cur.time+1] = true;
					nexts.offer(new Point(ii,jj,cur.time+1));
				}
			}
			// 블럭 이동
			size = blocknum;
			while(size-->0) {
				Point cur = blocks.poll();
				map[cur.x][cur.y] = false;
				if(++cur.x >= 8) blocknum--;
				else {
					map[cur.x][cur.y] = true;
					blocks.offer(cur);
					
				}
			}
			// 디버깅
			//print();
			
			// 자식들 추가
			size = nexts.size();
			while(size-->0) {
				queue.offer(nexts.poll());
			}
			
		}		
		return 0;
	}
	// 범위 판별
	static boolean isRange(int x, int y) {
		return (x >= 0 && x < SIZE && y >= 0 && y < SIZE);
	}
	// 디버깅
	static void print() {
		for(boolean[] arr : map) {
			for(boolean a : arr) {
				if(a)System.out.print('#');
				else System.out.print('.');
			}
			System.out.println();
		}
		System.out.println();
	}
}
