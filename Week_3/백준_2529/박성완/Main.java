//package s0812.bj_2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2529 부등호
public class Main {
	// 전역변수 생성
	static int N;
	static char[] sign;
	static int[] digitUp = {0,1,2,3,4,5,6,7,8,9};
	static int[] digitDown = {9,8,7,6,5,4,3,2,1,0};
	static int[] current;
	static boolean[] isVisited;
	static boolean found;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 괄호 배열 선언 및 입력
		N = Integer.parseInt(br.readLine());
		sign = new char[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N ; i++) sign[i] = st.nextToken().charAt(0);
		// 숫자 배열 및 현재 순열 생성
		current = new int[N+1];
		isVisited = new boolean[N+1];
		
		// 최고 순열부터 탐색
		permutations(0,digitDown);
		found = false;
		sb.append("\n");
		// 최저 순열부터 탐색
		permutations(0,digitUp);
		
		System.out.println(sb);
		br.close();
		
	}
	// 현재 선택한 순열이 괄호 배열의 조건은 만족하는지 검사
	static boolean compare() {
		// 하나씩 비교하면서 한번이라도 틀리면 바로  return
		for (int i = 0; i < N; i++) 
			if( sign[i]=='<' ? !(current[i] < current[i+1]) : !(current[i] > current[i+1]) )
				return false;
		return true;
	}
	static void permutations(int cnt,int[] arr) {
		// 한번이라도 찾았다면 바로 돌아간다.
		if(found) return;
		// 순열이 생성되었으면 검사해보고 통과하면 바로 sb에 추가한다.
		if(cnt==N+1 && compare()) {
			found = true;
			for(int n : current) sb.append(n);
			return;
		}
		// 순열 생성 알고리즘
		for (int i = 0; i < N+1; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			current[cnt] = arr[i];
			permutations(cnt+1,arr);
			isVisited[i] = false;
		}
	
	}
}
