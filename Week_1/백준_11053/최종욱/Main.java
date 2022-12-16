
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //수열의 크기
		
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) { //수열을 이루는 값 저장
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N]; //가장 긴 중가 수열을 확인할 배열
		
		for(int i = 0; i < N; i++) { //수열 수에 본인이 포함되니 일단 1을 기본으로 설정
			dp[i] = 1;
		}
		
		int line = 0; //최장 수열의 길이
		
		for(int i = 0; i < N; i++) {
			int max = 0;
			for(int j = i; j >= 0; j--) { //A[i] 전의 값들을 확인애서 본인 보다 작은 값들의  수열 길이(dp)확인
				if(A[i] > A[j]) {
					max = Math.max(max, dp[j]); //그 중 가장 큰 값을 찾음
				}
			}
			dp[i] += max; //가장 큰 dp값을 더 해줌
			line = Math.max(line, dp[i]); //최장 길이를 업데이트
			
		}
		
		System.out.println(line);
		

	}

}
