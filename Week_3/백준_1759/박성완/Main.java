//package week3.bj_1759;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1759 암호 만들기
public class Main {
	// 전역변수 생성
	static int L,C;
	static char[] clist, current;
	static boolean[] isVisited;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		// L,C 입력
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 본래 문자열, 현재 문자열, 사용 여부 배열
		clist = new char[C];
		current = new char[L];
		isVisited = new boolean[C];
		// 문자 입력
		st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<C ; i++) clist[i] = st.nextToken().charAt(0);
		Arrays.sort(clist);

		// 조합 생성
		combinations(0,0);
		
		System.out.println(sb.toString());
		br.close();
	}
	static boolean isCorrect() {
		int vowel = 0; // 모음의 갯수를 센다.
		for(int i=0; i<L ; i++) {
			switch(current[i]) {
			case 'a': case 'e': case 'i': case 'o' : case'u':
				vowel++;
				if(vowel > L-2) return false; // 중간에 모음 갯수가 많아 자음 갯수가 2개가 들어갈 수 없다면 탈출
				break;
			default:
				break;
			}
		}
		if(vowel==0) return false; // 모음이 2개여도 탈출
		else return true;
	}
	static void combinations(int cnt, int start) {
		if(cnt==L) { // 매 조합이 생성될 때 마다
			if(isCorrect()) { // 조합이 조건을 만족하는지 판단
			for(int i=0; i<L ; i++) sb.append(current[i]);
			sb.append("\n");
			} 
			return;
		}
		for(int i=start ; i<C ; i++ ) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			current[cnt] = clist[i];
			combinations(cnt+1,i+1);
			isVisited[i] = false;
		}
	}
}
