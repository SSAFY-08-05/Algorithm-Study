//package week1.bj_8958;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테스트케이스 갯수 입력
		int N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			// 문자열 입력
			String data = sc.next();
			
			// 출력할 점수와 카운터 입력
			int score = 0;
			int counter = 0;
			
			for (int j = 0; j < data.length(); j++) {				
				switch(data.charAt(j)) {
				// O면 카운터를 1 증가시키고 점수에 더한다.
				case 'O':
					counter+=1;
					score+=counter;
					break;
				// X면 카운터를 최신화한다.
				case 'X':
					counter=0;
					break;
				default:
					break;
				}
			}
			System.out.println(score);
		}
	}
}
