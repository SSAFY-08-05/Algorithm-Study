package week03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1051 {

	static int size = 1; //2*2크기부터 탐색 예정이라 그 이하 크기이면 1을 출력

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/week03/input_1051"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int arr[][] = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0'; 
			} 
		}
		
		square(N,M,arr);
		System.out.println(size);
	}

	public static void square(int N, int M, int arr[][]) {
		for (int i = 0; i < N; i++) { //전체 배열 탐색
			for (int j = 0; j < M; j++) {
				for(int k=1;i+k<N&&j+k<M;k++) { //k=1부터 더해주며 탐색시작, 정사각형이므로 j와 k에 동일하게 더해줌, 조건 : 배열 범위 내일때
					if(arr[i][j]==arr[i+k][j]&&arr[i][j]==arr[i+k][j+k]&&arr[i][j]==arr[i][j+k]) { //1꼭짓점 기준 나머지 세개가 같을 때
						size = Math.max(size, (k+1)*(k+1)); //정사각형 크기는 k+1의 제곱(처음 i,j좌표가 0이므로 k=1일때 크기가 2*2임)
					}
				}

			}

		}
	}

}
