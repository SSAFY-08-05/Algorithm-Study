import java.util.Scanner;

/*
 * OX퀴즈
 */
public class BJ_8958 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		//System.out.println();
		
		for (int a = 0; a < tc; a++) {
			String OX = sc.next(); //한 줄 문자열을 저장
			int sum = 0;
			int count = 0;

			for (int i = 0; i < OX.length(); i++) {
				if(OX.charAt(i)=='O') { //i번째 문자열이 O이면
					count++; //연속 점수를 ++;
					sum+=count; //합산 점수에 더하기
				}else {
					count = 0; //연속이 끝나면 초기화 해주기
				}
				
			}
			
			System.out.println(sum);
			
		}

	}

}
