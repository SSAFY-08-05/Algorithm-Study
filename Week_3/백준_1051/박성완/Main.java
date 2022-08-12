//package week3.bj_1051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1051 숫자 정사각형
public class Main {
	// 전역변수 선언
	static int N,M,counter;
	static String[] data;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//  N,M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 데이터 입력
		data = new String[N];
		for (int i = 0; i < N; i++) data[i] = br.readLine().trim(); 
		// 넓이가 큰 정사각형부터 탐색. 행 열중 크키가 작은 수부터 정사각형 탐색
		counter = N > M ? M : N;
		// 넓이가 큰 정사각형부터 서서히 넓이를 줄여가며 탐색한다.
		while(counter>1) {
			// 찾았으면 탈출하기 위한 변수
			boolean find = false;
			// 정사각형 크기에 따라 범위를 정하고 탐색한다.
			for (int i = 0; i <= N-counter; i++) {
				for (int j = 0; j <= M-counter; j++) {
					char c = data[i].charAt(j);
					// 만약 조건에 만족하다면 바로 탈출한다.
					if((c == data[i].charAt(j+counter-1)) && (c == data[i+counter-1].charAt(j)) && (c == data[i+counter-1].charAt(j+counter-1))) { 
						find = true;
						break;
					}
				}
				if(find) break;
			}
			if(find) break;
			// 아직 못찾았다면 정사각형의 크기를 줄여본다.
			counter--;
		}
		// 카운터의 제곱을 출력한다.
		System.out.println((int)(Math.pow(counter, 2)));
	}

}
