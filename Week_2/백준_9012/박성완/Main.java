//package week2.bj_9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9012 괄호
public class Main {
	// 전역변수 선언
	static int N;
	static char[] PS;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < N; t++) {
			// 괄호 갯수
			int cnt = 0;
			// 데이터 읽음
			PS = br.readLine().toCharArray();
			// 틀렸는지 판별하는 변수
			boolean wrong = false;
			
			// 문자를 읽어 (면 cnt 중가
			// )면 cnt==0일때 틀렸다고 판별하고 반복문 탈출
			// 아니라면 cnt 감소
			for(char c :PS) {
				if(c=='(') cnt++;
				else if (cnt == 0) {
					wrong = true;
					break;
				} else cnt--;
			}
			// 반복문이 끝나고 cnt가 남아있거나 wrong 스위치가 바꼈다면 NO, 아니라면 YES
			if (cnt > 0 || wrong) sb.append("NO\n");
			else sb.append("YES\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}
