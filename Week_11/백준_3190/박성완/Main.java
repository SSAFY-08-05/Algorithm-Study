package week11.bj_3190;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 메모리 12312	시간 88
// 백준 3190 뱀
public class Main {
	// 전역변수 선언
	static int N,K,L,gx,gy,gtime,gdirect;
	static Block[][] map;
	static Queue<Point> snake;
	static Command[] commands;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	// 맵에 사용할 블록 클래스
	static class Block{
		boolean isSnake;
		boolean isApple;
		public Block(boolean isSnake, boolean isApple) {
			super();
			this.isSnake = isSnake;
			this.isApple = isApple;
		}
		
	}
	// 명령을 저장할 클래스
	static class Command{
		int time;
		char rotate;
		public Command(int time, char rotate) {
			super();
			this.time = time;
			this.rotate = rotate;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		// 맵 생성
		map = new Block[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Block(false,false);
			}
		}
		
		// 사과 입력
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y].isApple = true;
		}
		
		L = Integer.parseInt(br.readLine());
		commands = new Command[L];
		
		// 명령 입력
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char rotate = st.nextToken().charAt(0);
			commands[i] = new Command(time,rotate);
		}
		
		// 게임 초기 설정
		snake = new LinkedList<>();
		gx = gy = gtime = gdirect = 0;
		map[gx][gy].isSnake = true;
		snake.offer(new Point(gx,gy));
		
//		System.out.println("위치 : " + gx + " " + gy);
//		System.out.println("시간 : " + gtime + " 방향  : " + gdirect);
		
		int cCnt = 0;
		while(true) {
			// 이동
			gx += dx[gdirect];
			gy += dy[gdirect];
			gtime++;
			// 충돌 계산
			// 벽 충돌이거나 몸이랑 충돌이면 게임이 끝남
			if(!isRange(gx,gy) || map[gx][gy].isSnake) {
				//System.out.println("범위 밖이거나 뱀몸과 부딪힘");
				break;
			}
			
			// 아니라면 이동 마무리
			snake.offer(new Point(gx,gy));
			map[gx][gy].isSnake = true;
			
			// 충돌이 아니라면 사과인지 계산
			if(map[gx][gy].isApple) {
				//System.out.println("사과 먹었다");
				map[gx][gy].isApple = false;
			} else { // 사과가 아니라면 꼬리가 이동되니까 한칸 비운다.
				//System.out.println("이동했다");
				Point p = snake.poll();
				map[p.x][p.y].isSnake = false;
			}
			
			// 회전 명령
			if(cCnt < L && commands[cCnt].time == gtime) {
				//System.out.println("방향명령 실행");
				switch(commands[cCnt].rotate) {
				case 'L' :
					if(gdirect == 0) gdirect = 3;
					else gdirect--;
					break;
				case 'D' :
					if(gdirect == 3) gdirect = 0;
					else gdirect++;
					break;
				default :
					System.out.println("error");
					break;
				}
				cCnt++;
			}
//			System.out.println("위치 : " + gx + " " + gy);
//			System.out.println("시간 : " + gtime + " 방향  : " + gdirect);
//			print();
		}
		
		System.out.println(gtime);
		
	}
	static boolean isRange(int x, int y) {
		return( x >= 0 && x < N && y >= 0 && y < N);
	}
	// 디버깅용 맵 출력 함수
	static void print() {
		for(Block[] arr : map) {
			for(Block a : arr) {
				if(a.isSnake) System.out.print("#");
				else if(a.isApple) System.out.print("O");
				else System.out.print(".");
			}
			System.out.println();
		}
		System.out.println();
	}
}
