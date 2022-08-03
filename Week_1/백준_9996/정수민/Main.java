package week01;
/*
 * 한국이 그리울 땐 서버에 접속하지
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9996 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		String check = br.readLine();
		
		char check_s = check.charAt(0); //첫 알파벳 저장
		char check_e = check.charAt(check.length()-1); //글자수 체크해서 마지막 알파벳 저장
		String res = null;
		
		for (int i = 0; i < N; i++) { 
			String word = br.readLine();
			
			char word_s = word.charAt(0);//첫 알파벳 저장
			char word_e = word.charAt(word.length()-1);//글자수 체크해서 마지막 알파벳 저장
			if(check_s==word_s||check_e==word_e) { //처음과 끝이 같으면
				res = "DA";
			}else res = "NE";
			System.out.println(res);
		}

	}

}
