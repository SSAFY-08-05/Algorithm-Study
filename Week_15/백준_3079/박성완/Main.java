package week15.bj_3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 3079 입국심사
public class Main {
	static int N,M,data[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		data = new int[N];
		int maxx = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(br.readLine());
			maxx = Math.max(maxx, data[i]);
		}
		
		long bottom = 0;
		long top = (long)maxx*M;
		long mid = 0;
		long sum = 0;
		
		while(bottom <= top) {
			
			mid = (bottom + top)/2;
			
			sum = 0;
			for (int i = 0; i < N; i++) {
				sum += mid/data[i];
			}
			
			if(sum >= M) {
				top = mid - 1;
			}else {
				bottom = mid + 1;
			}
		}

		System.out.println(bottom);
	}

}
