package week4.bj_16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 메모리 44262 메모리 292
// 백준 16943 숫자 재배치
public class Main {
	// 전역변수 선언
	static String a,b;
	static char current[],data[],target[],prev[];
	static int A,B,Na,Nb,ans;
	static boolean isVisited[],end;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		// 두 단어 변수로 정리
		a = st.nextToken() ; 		b = st.nextToken();
		A = Integer.parseInt(a);	B = Integer.parseInt(b);
		Na = a.length();			Nb = b.length();
		// 변수 세팅
		ans = -1;
		data = new char[Na];
		current = new char[Na];
		isVisited = new boolean[Na];
		for (int i = 0; i < Na; i++) data[i] = a.charAt(i);
		// 우선 데이터를 정렬
		Arrays.sort(data);
		// a가 길이가 더 작으면
		if(Na < Nb) {
			// 바로 뒤집어서 출력
			for (int i = 0; i < Na/2; i++) {
				char tmp = data[i];
				data[i] = data[Na-1-i];
				data[Na-1-i] = tmp;
			}
			System.out.println(String.valueOf(data));
		// a가 더 커버리면 -1출력
		}else if(Na > Nb) {
			System.out.println(ans);
			// 길이가 같으면
		}else {
			// 순열 실행
			permutation(0);
			System.out.println(ans);
		}
				
		br.close();
	}
	// 순열 알고리즘
	static void permutation(int cnt) {
		// 찾았으면 더 찾지 않음
		if(end) return;
		// 길이만큼 채웠으면
		if(cnt==Na) {
			// 앞이 0이면 검사하지않음
			if(current[0] == '0' ) return;
			// 두 수를 비교해서
			int number = Integer.parseInt(String.valueOf(current));
			// a가 이상이면 그만 탐색
			if(number >= B) {
                end = true;
                return;
            }
			// 아니라면 prev에 객체를 복사하고 ans에 number 저장
			prev = current.clone();
			ans = number;
			return;
		}
		// 순열 알고리즘
		for (int i = 0; i < Na; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			current[cnt] = data[i];
			permutation(cnt+1);
			isVisited[i] = false;
		}
	}

}
