package week01;

import java.util.Scanner;

public class BJ_11053 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int arr[] = new int[N];
		int value = 0; //지금까지 저장된 가장 큰 값을 넣을 값
		int len = 1; // 부분 수열의 길이
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt(); //배열을 담아주고
			value = arr[0]; //첫번째 인덱스를 초기 값으로 변경
		}
		
		for (int i = 1; i < N; i++) {
			if(arr[i]>value) { //만약 인덱스 값이 value보다 크다면
				value = arr[i]; //가장 큰 값을 현재 인덱스로 변경하고
				len++; //부분수열 길이를 하나 늘려줌
			}
		}
		System.out.println(len);
	}

}
