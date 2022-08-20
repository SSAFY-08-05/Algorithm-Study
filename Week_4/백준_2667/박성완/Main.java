//package week4.bj_2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 백준 2667 단지번호붙이기
public class Main {
	// 전역변수 선언
	static int N,count,dangi;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static boolean map[][];
	static List<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		// map입력 (계산과 메모리를 위해 boolean으로 선언)
		String buffer[];
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			buffer = br.readLine().split("");
			for (int j = 0; j < N; j++) if(buffer[j].equals("1")) map[i][j] = true;
		}
		
		// 집을 만날때마다 단지계산 실행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]) {
					// 단지의 시작점을 만나면 단지 수를 증가시키고 dfs 계산.
					dangi++;
					count = 0;
					dfs(i,j);
					// 계산 결과값을 리스트에 추가
					list.add(count);
				}
			}
		}
		// 리스트를 정렬하고 정보를 출력
		System.out.println(list.size());
		Collections.sort(list);
		for(int n : list) System.out.println(n);
		br.close();
	}
	// dfs알고리즘
	static void dfs(int i, int j) {
		count++;
		map[i][j] = false;
		// 4방탐색을 하여 단지 내라면 dfs재귀 호출
		for (int k = 0; k < 4; k++) {
			int ii = i+dx[k]; int jj = j+dy[k];
			if(isRange(ii,jj) && map[ii][jj]) dfs(ii,jj);
		}
	}
	// 범위 내 여부를 판단하는 메소드
	static boolean isRange(int i, int j) {
		if(i<0||i>=N||j<0||j>=N) return false;
		return true;
	}
}
