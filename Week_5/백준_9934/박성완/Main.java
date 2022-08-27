//package week5.bj_9934;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 메모리 20104	시간 92
// 백준 9934 완전 이진 트리
public class Main {
	// 전역변수 선언
	static int K;
	static String buffer;
	static List<String> list;
	public static void main(String[] args) throws IOException {
		// 입력방식 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 높이 입력받음
		K = Integer.parseInt(br.readLine());
		// 스트링 초기화
		buffer = "";
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) list.add(st.nextToken());
		
		// 1회 탐색 때마다 짝수 요소를 제거해서 뒤에서부터 하나씩 채운다.
		// 1회 탐색을 마무리 할 때마다 가장 하단 레벨의 노드부터 채워진다.
		while(!list.isEmpty()) {
			buffer = "\n" + buffer;
			
			for (int i = list.size()-1; i >= 0 ; i-=2) {
				buffer = list.remove(i) + " " + buffer;
			}
		}
		
		System.out.println(buffer);
		br.close();
	}
}
