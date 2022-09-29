import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> cheese;
	static int count;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cheese = new ArrayList<>(); //접촉면을 확인하기 위해 각 치즈의 위치 저장
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if(map[i][j] == 1) { //치즈 위치와 치즈 개수 저장
					cheese.add(new Point(i,j));
					count += 1;
				}
			}
		}
		
		int time = 0; //경과시간을 위한 변수
		
		while(count != 0) {
			time += 1; 
			visited = new boolean[N][M];
			dfs(0,0); //외부공기 탐색
			melt(); //2이상 접촉된 치즈 녹임
		}
		
		System.out.println(time);

	}

	private static void melt() {
		// TODO Auto-generated method stub
		for(int i = 0; i < cheese.size(); i++) {
			int x = cheese.get(i).x;
			int y = cheese.get(i).y;
			
			int cnt = 0; //접촉면 확인을 위한 변수
			
			for(int j = 0; j<4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				
				if(map[nx][ny] == 2) { //외부 공기접촉되면 카운트
					cnt+=1;
				}
			}
			
			if(cnt >= 2) { // 접촉면적 2 이상이면 치즈 녹음
				map[x][y] = 0;
				count -= 1;
				cheese.remove(i);
				i--;
			}
		}
		
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		map[x][y] = 2; // 외부 공기라는 의미로 2로 바꿔줌 

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			if (visited[nx][ny] || map[nx][ny] == 1) continue; //치즈이거나 방문했던 곳은 넘어감

			dfs(nx, ny); // 외부공기인 경우만 dfs() 수행 
		}
	}
	

}
