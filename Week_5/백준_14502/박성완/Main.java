//package week5.bj_14502;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
//백준 14502 연구소
// 메모리 123932	 시간404
public class Main {
	// 전역변수
	static int N,M,result[],maxx,blankN;
	static boolean rawvisited[][];
	static List<Point> blanks;
	static List<Point> virus;
	static Point[] list;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 기초 데이터 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maxx = 0;
		blanks = new ArrayList<Point>(); // 빈칸 리스트
		virus = new ArrayList<Point>(); // 바이러스 리스트
		rawvisited = new boolean[N][M]; // 초기 방문배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				switch(st.nextToken().charAt(0)) {
				case '0': // 빈칸 리스트에 넣음
					blanks.add(new Point(i,j));
					blankN++;
					break;
				case '1': // 벽은 못가는 처리
					rawvisited[i][j] = true;
					break;
				default: // 바이러스 처리
					virus.add(new Point(i,j));
					break;
				}
			}
		}
		list = new Point[3]; // 골라질 위치
		comb(0,0);
				
		System.out.println(maxx);
		br.close();
	}
	// 조합 선택
	static void comb(int cnt, int start) {
		// 다 골랐으면 시뮬레이션 시작
		if(cnt == 3) {
			simulation();
			return;
		}
		// 빈칸중에 3개를고른다.
		for (int i = start; i < blankN; i++) {
			list[cnt] = blanks.get(i);
			comb(cnt+1, i+1);
		}
	}
	// 범위 판별
	static boolean isRange(int x, int y) {
		if(x<0||x>=N||y<0||y>=M)return false;
		return true;
	}
	// 배열객체 복사 함수
	static boolean[][] copyof(boolean[][] arr){
		boolean[][] temp = arr.clone();
		for (int i = 0; i < N; i++) {
			temp[i] = arr[i].clone();
		}
		return temp;
	}
	// bfs 시뮬레이션 메소드
	static void simulation() {
		// 3개는 방문처리되므로 -3된채로 시작
		int count = blankN-3;
		// 방문배열 생성
		boolean visited[][] = copyof(rawvisited);
		// 큐를 만들고
		Queue<Point> queue = new LinkedList<>();
		// 바이러스는 큐에 넣어주면서 방문처리
		for(Point p : virus) {
			queue.offer(p);
			visited[p.x][p.y] = true;
		}
		for(Point p : list) {
			// 고른 벽 위치도 방문처리
			visited[p.x][p.y] = true;
		}
		
		while(!queue.isEmpty()) {
			// 하나 빼서
			Point p = queue.poll();
			// 퍼질 수 있는 곳이면 큐에 넣고 방문처리
			for (int i = 0; i < 4; i++) {
				int ii = p.x + dx[i];
				int jj = p.y + dy[i];
				
				if(!isRange(ii,jj) || visited[ii][jj]) continue;
				
				queue.offer(new Point(ii,jj));
				visited[ii][jj] = true;
				// 안전지대가 줄어들고, 최댓값보다 작아진다면 return 
				if(count-- < maxx) return;
			}
			
		}
		// return 되지 않고 끝났다면 최댓값 갱신
		maxx = Math.max(maxx, count);
	}
}
