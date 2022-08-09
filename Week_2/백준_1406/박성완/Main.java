//package week2.bj_1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

// 백준 1406 에디터
public class Main {
	// 전역변수 선언
	static Deque<Character> left;
	static Stack<Character> right;
	static int com_N,cursor,size;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 커서를 중심으로 좌우 나눠서 저장
		// 왼쪽은 넣을때는 스택 방식이지만, 뺄때는 첫 요소부터 탐색해야 하므로, 왼쪽은 deque
		// 오른쪽은 stack
		left = new ArrayDeque<Character>();
		right = new Stack<Character>();
		// 기본 데이터 입력
		String rawdata = br.readLine();
		com_N = Integer.parseInt(br.readLine());
		for (int i = 0; i < rawdata.length() ; i++) left.add(rawdata.charAt(i));
		// 스위치 기반으로 작동
		for (int i = 0; i < com_N; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken().charAt(0)) {
			case 'L':
				if(!left.isEmpty()) right.push(left.pollLast());
				break;
			case 'D':
				if(!right.isEmpty()) left.add(right.pop());
				break;
			case 'B':
				if(!left.isEmpty()) left.pollLast();
				break;
			case 'P':
				left.add(st.nextToken().charAt(0));
				break;
			default:
				break;
			}
		}
		// 작동 후 left는 처음부터, right는 반대로 빼서 sb에 입력
		while(!left.isEmpty()) sb.append(left.poll());
		while(!right.isEmpty()) sb.append(right.pop());
		System.out.println(sb);
		br.close();
	}
}
