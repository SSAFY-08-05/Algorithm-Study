//package week3.bj_2437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 백준 2437 저울
public class Main {
	// 전역변수 선언
	static int[] numbers;
	static int[] sums;
	static int N, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// N 입력받고 배열 생성 및 입력
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());
		// 정렬
		Arrays.sort(numbers);
		// 첫요소가 1이 아니라면 1이 답
		if(numbers[0] != 1) System.out.println(1);
		else {
			// 갯수가 1이라면 1 하나이므로 2가 답
			if(N==1) {
				System.out.println(2);
				System.exit(0);
			}
			// 둘 다 아니라면 누적합 알고리즘을 이용한다.
			// 현재 위치까지 누적합을 구하고, 다음 요소가 연재 누적합 +1보다 높다면 불가하다.
			// 다음요소가 누적합 +1이라면 다음요소 하나만 가지고 만들 수 있다.
			// 불가한 위치를 찾으면 그 값 +1을 저장하고 나온다.
			ans = -1;
			sums = new int[N];
			
			sums[0] = numbers[0];
			
			for (int i = 1; i < N-1; i++) {
				sums[i] = sums[i-1] + numbers[i];
				if(sums[i] + 1 < numbers[i+1]) {
					ans = sums[i]+1;
					break;
				}
			}
			
			if(ans == -1) {
				// 값이 바뀌지 않았다면 맨 마지막 누적합 +1을 출력한다.
				System.out.println(sums[N-2] + numbers[N-1]+1);
			}else System.out.println(ans);
			
		}
		br.close();
	}
}
