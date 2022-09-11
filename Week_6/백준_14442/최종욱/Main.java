import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Info{
		int x;
		int y;
		int dist;
		int wall;
		
		Info(int x, int y, int dist, int wall){
			this.x = x; //좌표
			this.y = y;
			this.dist = dist; //지나온 거리
			this.wall = wall; //부순 벽
		}
	}
	static int N,M,K;
	static int dist = 0;
	static int[] dx = {-1,0,1,0};//4방향 이동 배열
	static int[] dy = {0,1,0,-1};
	static int[][] arr;
	static boolean[][][] visited; //방문 여부와 벽 확인
	
	
	static int track() {
		Queue<Info> queue = new LinkedList<>();
		queue.add(new Info(0,0,1,0));//시작 위치

		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Info location = queue.poll();
			
			if(location.x == N-1 && location.y == M-1) {
				return location.dist;
			}			
			for(int i = 0; i < 4; i++) {//4방
				int nx = location.x + dx[i];
				int ny = location.y + dy[i];
				int wall = location.wall;
					
				if(nx >= 0 && nx < N && ny >= 0 && ny < M ) { //이동할 위치가 범위 내에 있는 지 확인
					if(arr[nx][ny] == 0 && !visited[nx][ny][wall]) { // 벽이 없을 때
						visited[nx][ny][wall] = true;
						queue.add(new Info(nx, ny, location.dist+1, wall));													
					}
					else if(arr[nx][ny] == 1 && wall < K && !visited[nx][ny][wall+1] ) { //벽이 있을 때
						visited[nx][ny][wall+1] = true;
						queue.add(new Info(nx, ny, location.dist+1, wall+1));						
					}
				}
			}			
		}	
		return -1;
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M][K+1];
		
		for(int i = 0; i < N; i++) {
			String s = bf.readLine();
			for(int j = 0; j < M; j++ ) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
				
		
		int result = track();
		System.out.println(result);
		
		
		

	}

}

/**
서영탁
저 처음엔 boolean[][][] visited를 3차원으로 했다가 int[][] visited 2차원으로 변경하였습니다.
이미 1번의 벽을 깨고 들어온 곳에 2번의 벽을 깨고 들어오는 경우는 볼 필요가 없다고 생각해서 제외했습니다.
**/
