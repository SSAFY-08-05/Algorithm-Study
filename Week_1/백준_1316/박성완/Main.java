//package bj_1316;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 단어 갯수 입력
		int N = sc.nextInt();
		int count = 0;
		// 연속된 알파벳 단위로 끊고, 각 단위별로 리스트에 없다면 추가,
		// 이미 있다면 조건을 만족하지 않으므로 반복문을 탈출해버린다.
		for (int i = 0; i < N; i++) {
			String target = sc.next();
			
			List<Character> list = new ArrayList<>();	
			// 문자열 첫 요소로 초기화
			char prev = target.charAt(0);
			// 문자열 길이가 1일 경우를 대비해 초기화
			char tmp = prev;
			// 중간변수
			boolean wrong = false;
			
			for (int j = 1; j < target.length(); j++) {
				tmp = target.charAt(j);
				// 이전 문자가 다르다면 단위로 끊는다.
				if(prev != tmp) {
					//리스트에 없다면 추가
					if(!list.contains(prev)) {
						list.add(prev);
						prev = tmp;
					} 
					//있다면 중간변수를 이용해 탈출
					else {
						wrong = true;
						break;
					}	
				}
			}
			//마지막요소까지 한 단위로 보고 똑같이 계산.
			if(list.contains(tmp)) wrong = true;
			
			// 그때까지 중간변수가 변하지 않았다면 count 증가
			if(!wrong) count++;
		}
		
		System.out.println(count);
	}
}
