package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2667_단지번호붙이기 {
	static int N;
	static int map[][];
	static boolean visited[][];
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]);
//			}System.out.println();
//		}

		num = 1; // 단지

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) { // 이어진 1이 끝나면 dfs가 한 번씩 끝나기 때문에 단지+1해서 한 번 더 실행해줘야함
					
					dfs(i, j);
					num++;
				}
			}
		}
		

		System.out.println(num-1); // 단지 수 총 몇갠지

		int house[] = new int[num];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 1) {
					house[map[i][j] - 1]++; // 단지 index에 하나씩 추가
				}
			}
		}
		//System.out.println();

		Arrays.sort(house);
		for (int i = 1; i < house.length; i++) {
			System.out.println(house[i]);
		}

	}

	static void dfs(int x, int y) {
		map[x][y] = num; // 단지 수로 바꾸고 
		visited[x][y] = true; // 방문했다고 표시
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i]; // 상하좌우 로 이동하면서

			if (nx >= 0 && nx < N && ny >= 0 && ny < N) { // 범위인지 확인한 후
				if (map[nx][ny] == 1 && !visited[nx][ny]) { // 집이면서 아직 방문하지 않았으면
					dfs(nx, ny); // 그 자리에서 dfs 다시 실행
				}
			}
		}

	}

}
