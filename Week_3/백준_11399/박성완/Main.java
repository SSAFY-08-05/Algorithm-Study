//package week3.bj_11399;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11399 ATM
public class Main {
	// 전역변수 선언
	static int N,sum;
	static int[] input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 데이터 선언 및 입력
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
		
		// 가장 적은 경우의 수는 걸리는 시간이 적은 순으로 순번을 매기는 것이다.
		// 배열을 정렬하고, 각 요소를 N-i번 곱해서 더하면 된다.
		Arrays.sort(input);
		for (int i = 0; i < N; i++) sum += (N-i)*input[i];
		System.out.println(sum);
	}
}
