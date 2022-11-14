package week16.bj_1938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
// 메모리 12136	시간 92
// 백준 1939 통나무 옮기기
public class Main {
	// 전역변수 생성
	static int N,SX,SY,EX,EY,init_d;
	// 통나무의 현재 상태 클래스
	static class Bar{
		// x,y,방향,시간
		int x, y, d, t; // d // 0 가로 1 세로

		public Bar(int x, int y, int d, int t) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.t = t;
		}
		
	}
	static boolean map[][], visited[][][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new boolean[N][N];
		visited = new boolean[N][N][2];
		
		// 맵 입력
		boolean swit = false;
		init_d = -1;
		for (int i = 0; i < N; i++) {
			String buffer = br.readLine().trim();
			for (int j = 0; j < N; j++) {
				switch(buffer.charAt(j)) {
				case '1':
					map[i][j] = true;
					break;
				// 시작 위치와 방향을 알아내기 위해 간단한 로직 사용
				case 'B':
					SX += i;
					SY += j;
					
					if(swit) break;
					if(init_d == -1) {
						init_d = i;
					}else {
						if (init_d == i) init_d = 0;
						else init_d = 1;
						swit = true;
					}
					break;
				case 'E':
					EX += i;
					EY += j;
					break;
				case '0':
					break;
				default:
					System.out.println("error");
					break;
				}
			}
		}
		// 각 세 점의 좌표를 더해놓고 나누면 된다.
		SX /= 3; SY /=3; EX /=3; EY /=3;
		
		
		System.out.println(bfs());
	}
	static int bfs() {
		Queue<Bar> queue = new LinkedList<>();
		queue.offer(new Bar(SX,SY,init_d,0));
		visited[SX][SY][init_d] = true;
		while(!queue.isEmpty()) {
			Bar cur = queue.poll();
			
			if(cur.x == EX && cur.y == EY) return cur.t;
			
			if(cur.d == 0) { // 가로 상태면
				//상
				if(multipleValid(cur.x-1,cur.y-1,cur.x-1,cur.y+1) && !visited[cur.x-1][cur.y][cur.d]) {
					queue.offer(new Bar(cur.x-1,cur.y,cur.d,cur.t+1));
					visited[cur.x-1][cur.y][cur.d] = true;
				}
				
				//하
				if(multipleValid(cur.x+1,cur.y-1,cur.x+1,cur.y+1) && !visited[cur.x+1][cur.y][cur.d]) {
					queue.offer(new Bar(cur.x+1,cur.y,cur.d,cur.t+1));
					visited[cur.x+1][cur.y][cur.d] = true;
				}
				
				//좌
				if(cur.y-2 >= 0 && !map[cur.x][cur.y-2] && !visited[cur.x][cur.y-1][cur.d]) {
					queue.offer(new Bar(cur.x,cur.y-1,cur.d,cur.t+1));
					visited[cur.x][cur.y-1][cur.d] = true;
				}
				
				//우
				if(cur.y+2 < N && !map[cur.x][cur.y+2] && !visited[cur.x][cur.y+1][cur.d]) {
					queue.offer(new Bar(cur.x,cur.y+1,cur.d,cur.t+1));
					visited[cur.x][cur.y+1][cur.d] = true;
				}
				
			}else { // 세로 상태면
				//상
				if(cur.x-2 >= 0 && !map[cur.x-2][cur.y] && !visited[cur.x-1][cur.y][cur.d]) {
					queue.offer(new Bar(cur.x-1,cur.y,cur.d,cur.t+1));
					visited[cur.x-1][cur.y][cur.d] = true;
				}
				
				//하
				if(cur.x+2 < N && !map[cur.x+2][cur.y] && !visited[cur.x+1][cur.y][cur.d]) {
					queue.offer(new Bar(cur.x+1,cur.y,cur.d,cur.t+1));
					visited[cur.x+1][cur.y][cur.d] = true;
				}
				
				//좌
				if(multipleValid(cur.x-1,cur.y-1,cur.x+1,cur.y-1) && !visited[cur.x][cur.y-1][cur.d]) {
					queue.offer(new Bar(cur.x,cur.y-1,cur.d,cur.t+1));
					visited[cur.x][cur.y-1][cur.d] = true;
				}
				
				//우
				if(multipleValid(cur.x-1,cur.y+1,cur.x+1,cur.y+1) && !visited[cur.x][cur.y+1][cur.d]) {
					queue.offer(new Bar(cur.x,cur.y+1,cur.d,cur.t+1));
					visited[cur.x][cur.y+1][cur.d] = true;
				}
				
			}
			//회전
			if(multipleValid(cur.x-1,cur.y-1,cur.x+1,cur.y+1) && !visited[cur.x][cur.y][1-cur.d]) {
				queue.offer(new Bar(cur.x,cur.y,1-cur.d,cur.t+1));
				visited[cur.x][cur.y][1-cur.d] = true;
			}
			
		}
		return 0;
	}
	// 여러 위치를 확인하기 위한 함수
	static boolean multipleValid(int x1, int y1, int x2, int y2) {
		if( !isRange(x1,y1) || !isRange(x2,y2)) return false;
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				if(map[i][j]) return false;
			}
		}
		return true;
	}
	// 범위 내인지 확인하는 함수
	static boolean isRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
}
