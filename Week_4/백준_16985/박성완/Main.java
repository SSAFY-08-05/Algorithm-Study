//package week4.bj_16985;
// 메모리293644	1112
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 16985 Maaaaaaaaaze
public class Main {
	// 전역변수 선언
	static final int SIZE = 5;
	static int least,current[];
	static boolean maze[][][], visited[][][];
	static int dx[] = {1,0,0,-1,0,0};
	static int dy[] = {0,1,0,0,-1,0};
	static int dz[] = {0,0,1,0,0,-1};
	static int order[] = {0,1,2,3,4};
	static boolean panVisited[];
	// 3차원 위치와 경로를 저장하는 객체
	static class Point3D{
		int x,y,z,path;

		public Point3D(int x, int y, int z, int path) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.path = path;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 미로 정보 입력
		maze = new boolean[SIZE][SIZE][SIZE];
		least = Integer.MAX_VALUE;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < SIZE; k++) {
					// 0 은 들어갈 수 없는 칸, 1은 들어갈 수 있는 칸
					// 따라서 1이면 들어갈 수 있게  true 로 설정한다.
					if(st.nextToken().equals("1")) maze[i][j][k] = true;
				}
			}
		}
		// 우선 각 판을 돌리는 경우의 수를 모두 탐색한다.
		everyCase();

		if(least == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(least);
	}
	
	static void everyCase() {
		// 각 판에 대해서 모두 돌려보고
		for (int i = 0; i < 4; i++) {
			maze[0] = roll(maze[0]);
			for (int j = 0; j < 4; j++) {
				maze[1] = roll(maze[1]);
				for (int m = 0; m < 4; m++) {
					maze[2] = roll(maze[2]);
					for (int k = 0; k < 4; k++) {
						maze[3] = roll(maze[3]);
						for (int n = 0; n < 4; n++) {
							maze[4] = roll(maze[4]);
							// 다 돌려봤으면 판을 놓는 경우의 수를 순열방식으로 접근한다.
							current = new int[5];
							panVisited = new boolean[5];
							perm(0);							

						}
					}
				}
			}
		}
	}
	// 판 하나를 시계방행으로 돌리는 함수
	static boolean[][] roll(boolean[][] arr) {
		boolean[][] temp = new boolean[SIZE][SIZE];
		for (int i = 0; i < SIZE ; i++) {
			for(int j = 0; j < SIZE ; j++) {
				temp[i][j] = arr[SIZE-1-j][i];
			}
		}
		return temp;
	}
	// 범위 안인지 판별하는 함수
	static boolean isRange(int x, int y, int z) {
		if( x < 0 || x >= SIZE || y < 0 || y >= SIZE || z < 0 || z >= SIZE ) return false;
		return true;
	}
	// 판을 놓는 위치를 정하는 순열 함수
	static void perm(int cnt) {
		// 판 순서를 정햇으면 그대로 쌓고  bfs알고리즘으로 탐색 시작
		if(cnt==5) {
			boolean temp[][][] = new boolean[SIZE][SIZE][SIZE];
			temp = maze.clone();
			for (int i = 0; i < 5; i++) {
				temp[i] = maze[current[i]].clone();
			}
			// 만약 출발점이나 도착점이 갈 수 없는 곳이면 못가므로 계산할 필요가 없다.
			if(!temp[0][0][0] || !temp[4][4][4]) return;
			bfs(temp);
			return;
		}
		for (int i = 0; i < 5; i++) {
			if(panVisited[i]) continue;
			panVisited[i] = true;
			current[cnt] = i;
			perm(cnt+1);
			panVisited[i] = false;
		}
		
	}
	// 경로탐색 : bfs
	static void bfs(boolean[][][] arr) {
		// 방문 배열 초기화
		visited = new boolean[SIZE][SIZE][SIZE];
		// 큐 초기화
		Queue<Point3D> queue = new LinkedList<>();
		// 첫 위치를 놓고
		queue.offer(new Point3D(0,0,0,0));
		while(!queue.isEmpty()) {
			// 하나씩 빼서
			Point3D cur = queue.poll();
			// 도착점이라면 경로값을 반환
			if(cur.x == 4 && cur.y == 4 && cur.z == 4) {
				least = Math.min(least, cur.path);
				return;
			}
			// 방문처리하고
			visited[cur.x][cur.y][cur.z] = true;
			// 갈 수 있는 위치를 넣는다.
			for (int k = 0; k < 6; k++) {
				int ii = cur.x + dx[k];
				int jj = cur.y + dy[k];
				int kk = cur.z + dz[k];
				
				if(isRange(ii,jj,kk) && !visited[ii][jj][kk] && arr[ii][jj][kk]) {
					queue.offer(new Point3D(ii,jj,kk,cur.path+1));
					// 넣을 때 방문처리를 해야 간 곳을 돌아오지 않는다.
					visited[ii][jj][kk] = true;
				}
			}
		}
		return;
	}
}
