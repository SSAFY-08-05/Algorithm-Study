package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 회문
 */
public class BJ_17609 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //반복 읽어오기
		String str;
		
		
		for (int a = 0; a < N; a++) {
			str = (br.readLine());
			char arr[] = new char[str.length()];
			int value = 0; //결과값 회문을 기본
			
			for (int i = 0; i < str.length(); i++) {
				arr[i] = str.charAt(i);
				//System.out.print(arr[i] + " ");
			}
			
			if(str.length()%2==0) {//짝수일때
				for (int i = 0; i < arr.length/2; i++) {
					if(arr[i]!=arr[arr.length-i-1]) { //양쪽 비교해서 다르면
						value = 2; //둘 다 아님
					}
				}
			}
			
			else { //홀수일 때
				char temp[] = new char[str.length()-1]; //길이가 하나 짧은 배열 생성
				temp =arr;//잠시 저장
				for (int i = arr.length/2; i < arr.length-1; i++) {
					arr[i]=arr[i+1]; //가운데를 삭제
					for (int j = 0; j < arr.length/2; j++) {
						if(arr[j]==arr[arr.length-j-1]) { //양쪽 비교해서 같으면
							value = 0; //회문맞음
						}
					}
				}
				
				arr = temp; //다시 원래 값
				//temp = arr; //원래 값 저장해놓기
				for (int k = 0; k < arr.length; k++) { //처음부터 끝까지 하나씩 삭제해보기			
					for (int i = k; i < arr.length-1; i++) {
						arr[i]=arr[i+1]; //가운데를 삭제
						for (int j = 0; j < arr.length/2; j++) { //다시 짝수일 때랑 같은 비교 시작
							if(arr[j]==arr[arr.length-j-1]) { //양쪽 비교해서 같으면
								value = 1; //유사회문
							}else value = 2;
						}
					}
				}
			}System.out.println(value); //값 출력
		}
	}

}
