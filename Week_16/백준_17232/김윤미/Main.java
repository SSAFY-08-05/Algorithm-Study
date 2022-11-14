
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, t;
	static int k, a, b;
	static char[][] arr;
	static int[][] life; // 현재 칸 주위의 생명의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		arr = new char[n][m];
		life = new int[n][m];

		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		while (t-- > 0) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					life[i][j] = 0;
					if (arr[i][j] == '*') {
						life[i][j] = 1;
					}
					if (isRange(i - 1, j))
						life[i][j] += life[i - 1][j];
					if (isRange(i, j - 1))
						life[i][j] += life[i][j - 1];
					if (isRange(i - 1, j - 1))
						life[i][j] -= life[i - 1][j - 1];
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {

					int startX = Math.max(0, i - k);
					int startY = Math.max(0, j - k);
					int endX = Math.min(n - 1, i + k);
					int endY = Math.min(m - 1, j + k); // 범위 안 넘는 선에서 k범위 안으로..

					int cnt = life[endX][endY];
					if (isRange(startX - 1, startY - 1))
						cnt += life[startX - 1][startY - 1];
					if (isRange(startX - 1, endY))
						cnt -= life[startX - 1][endY];
					if (isRange(endX, startY - 1))
						cnt -= life[endX][startY - 1];
					if (arr[i][j] == '*')
						cnt--;

					if (arr[i][j] == '*') { // 현재 칸에 생명이 있을 때

						if (cnt >= a && cnt <= b)
							continue; // 생존
						else { // 고독 or 과밀
							arr[i][j] = '.';
						}
					} else {
						if (cnt > a && cnt <= b) {
							arr[i][j] = '*';
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(arr[i]).append("\n");
		}

		System.out.println(sb.toString());
	}

	static boolean isRange(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= m)
			return false;
		else
			return true;
	}
}
