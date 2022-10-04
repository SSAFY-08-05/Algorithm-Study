//package week10.sw_4008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//31,636 kb 223 ms
// SWEA 4008 숫자 만들기
public class Solution {
	// 전역변수 선언
	static int TC, N, raw[], maxim, least, numbers[], operators[];
	static StringBuilder sb;
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		raw = new int[4];
		
		for (int t = 1; t <= TC; t++) {
			// 초기화
			N = Integer.parseInt(br.readLine());
			numbers = new int[N];
			operators = new int[N-1];
			maxim = Integer.MIN_VALUE;
			least = Integer.MAX_VALUE;
			visited = new boolean[N-1];

			// 입력을 받으면서 연산자 배열을 단계적으로 채움
			int count = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				raw[i] = Integer.parseInt(st.nextToken());
				Arrays.fill(operators, count, count+raw[i], i);
				count += raw[i];
			}
			// 숫자 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			// nextPermutation 이용, 중복 순열을 겹치는 구간 없이 탐색하여 완탐
			do {
				int res = numbers[0];
				for (int i = 0; i < N-1; i++) {
					res = calc(res,numbers[i+1],operators[i]);
				}
				maxim = Math.max(maxim, res);
				least = Math.min(least, res);
			}while(np(operators));
			
			sb.append("#" + t + " " + (maxim - least) + "\n");
			
		}
		System.out.println(sb);
		br.close();
	}
	//nextPermutation
	static boolean np(int[] arr) {
		int NN = N-1;
		int i = NN-1;
		while(i>0 && arr[i-1] >= arr[i]) --i;
		
		if(i==0) return false;
		
		int j = NN-1;
		while(arr[i-1] >= arr[j]) --j;
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		Arrays.sort(arr,i,NN);
		
		return true;
	}
	// 실제 계산
	static int calc(int a, int b, int comm) {
		switch(comm) {
		case 0:
			return a+b;
		case 1:
			return a-b;
		case 2:
			return a*b;
		case 3:
			return a/b;
		default:
			System.out.println("error");
			break;
		}
		return 0;
	}
}
