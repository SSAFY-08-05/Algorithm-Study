//package week11.sw_4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// SWEA 4014 활주로 건설
public class Solution {
	// 전역변수 설정
	static int TC,map[][],N,X,ables;
	static StringBuilder sb;
	static boolean way[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			// 입력받고
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			// 가능한 경우의 수 초기화
			ables = 0;
			
			// 열을 돌린 배열을 아래 추가해서 만들기 위해 행을 두배로
			map = new int[N*2][N];
			// 활주로가 겹친지 확인해야 하니까 배열 만들어줌
			way = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = map[N+j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 한 행씩 검증해본다 (주석처리한 부분은 확인용 코드)
			for(int[] arr : map) {
//				for(int a : arr) System.out.print(a + " ");
//				System.out.print(" || ");
				if(isRunway(arr)) {
//					System.out.println("yes");
					ables++;
				}
//				else System.out.println("no");
//				for(boolean b : way) {
//					if(b)System.out.print("# ");
//					else System.out.print("- ");
//				}
//				System.out.println();
			}
			
			// 결과 추가
			sb.append("#").append(t).append(" ").append(ables).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	// 활주로인지 판별하는 코드
	static boolean isRunway(int[] arr) {
		// 활주로 설치 여부 배열 초기화
		Arrays.fill(way, false);
		boolean swit = false;
		int cnt = 1;
		int prev = arr[0];
		// 기본 로직은 한칸씩 탐색
		// 현재까지 같은 수 갯수를 cnt로 두고, 다음 수가 높은지, 낮은지, 같은지, 범위 밖인지에 따라 분기
		for (int i = 1; i < N; i++) {
			int cur = arr[i];
			// 같다면
			if(cur == prev) {
				// 개수 증가
				cnt++;
				// 활주로 탐색중이었고 설치가 가능하다면
				if(swit && cnt >= X) {
					// 설치해주고 스위치 끔
					int idx = i;
					for (int j = 0; j < X; j++) {
						// 이때 이미 깐 활주로를 만나면 불가
						if(way[idx]) return false;
						way[idx--] = true;
					}
					swit= false;
				}
			}else if (cur + 1 == prev) { //이번 수가 한 칸 적다면
				// 다음칸이 한칸 적다면 활주로를 탐색해야 함
				// 이미 탐색중이라면 불가
				if(swit) return false;
				swit = true;
				cnt = 1;
			}else if (cur - 1 == prev) { //이번 수가 한 칸 높다면
				// 다음 칸이 한칸 높다면 그동안 지나온 cnt가 활주로를 설치할 수 있어야 함
				// 근데 이미 탐색중이면 불가
				if(swit) return false;
				// 이전가지의 cnt가 갯수미달이면 불가
				if(cnt < X) return false;
				// 활주로를 깔아보고 이미 있는 위치면 불가
				int idx = i;
				for (int j = 0; j < X; j++) {
					if(way[--idx]) return false;
					way[idx] = true;
				}
				
				cnt = 1;
			}else {
				return false;
			}
			
			prev = cur;
		}
		
		// 마지막까지 왔는데 활주로 탐색중이라면 cnt 갯수에 따라 처리
		if(swit) {
			if(cnt >= X) {
				int idx = N-1;
				for (int j = 0; j < X; j++) {
					if(way[--idx]) return false;
					way[idx] = true;
				}
				
				return true;
			}else return false;
		}
		
		return true;
	}
	// 디버깅용 메소드
	static void print() {
		for(int[] arr : map) {
			for(int a : arr) System.out.print(a + " ");
			System.out.println();
		}
		System.out.println();
	}
}
