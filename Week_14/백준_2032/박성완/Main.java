package week14.bj_2032;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 메모리 11484	시간 72
// 백준 2302 극장 좌석
public class Main {
	static int N,M,fibo[],result, prev, cur, range;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		result = 1;
		fibo = new int[41];
		fibo[0] = fibo[1] = 1;
		
		doFibo();
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < M; i++) {
			cur = Integer.parseInt(br.readLine().trim());
			range = cur-prev-1;
			result *= fibo[range];
			prev = cur;
		}
		range = N-prev;
		result *= fibo[range];
		
		System.out.println(result);
		br.close();
		
	}
	private static void doFibo() {
		for (int i = 2; i < 41; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
	}
}
