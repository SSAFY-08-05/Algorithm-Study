//package week1.bj_17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BJ 17609 회문
public class Main {
	//  word 형 객체 선언
	public static int T;
	public static Word[] words;
	
	//  word 형 객체
	static class Word{
		public String name; //단어
		public Word(String name) { // 생생자
			super();
			this.name = name;
		}
		
		//회문인지 판별하는 함수
		public int ispalin() {
			// 투 포인터 활용
			int si = 0; int ei = name.length()-1;
			// 차이나는 갯수. 0이면 회문, 1이면 유사회면, 2이상이면 아무것도 아님.
			int cnt = 0;
			// 왼쪽과 오른쪽 중간변수
			boolean leftswitch  = false;
			boolean rightswitch = false;
			// 문자열 길이가 2이하면 문자열 길이 = 회문 판별과 같다.
			if (ei<=1) return ei;
			while(si < ei) {
				// 스위치 리셋
				leftswitch  = false;
				rightswitch = false;
				// cnt가 1을 초과하면 아무것도 아니다. 탈출.
				if(cnt > 1) return 2;
				// 양쪽 문자가 같다면 포인터 안쪽으로 전진.
				if (name.charAt(si) == name.charAt(ei)) {
					si++; ei--;
				}else {
					// 다르면 우선  cnt를 증가한다.
					// 다른 상태에서 1을 초과했다면 2 리턴
					if(++cnt > 1) return 2;
					// 다른 상태에서 포인터 차이가 1이라면  현재값 리턴
					else if (ei-si==1) return cnt;
					// 왼쪽만 포인터를 전진했을 때 같다면 왼쪽 스위치 ON
					if(name.charAt(si+1) == name.charAt(ei)) {
						leftswitch = true;
					}
					// 오른쪽만 포인터를 전진했을 때 같다면 오른쪽 스위치 ON
					if(name.charAt(si) == name.charAt(ei-1)) {
						rightswitch = true;
					}
					
					// 두 스위치 모두 켜졌다면 두 경우를 나눠서 검사를 해야한다. 왼쪽 한글자를 뺐을때, 오른쪽 한 글자를 뺐을 때를  subpalin으로 넘겨 확인한다.
					if(leftswitch && rightswitch) {
						if(subpalin(name.substring(si, ei))||subpalin(name.substring(si+1, ei+1))) return 1;
						else return 2;
					} 
					// 왼쪽 스위치만 켜졌다면 왼쪽 문자를 스킵한다.
					else if (leftswitch) {
						si += 2; ei --;
					} 
					// 오른쪽 스위치만 켜졌다면 오른쪽 문자를 스킵한다.
					else if (rightswitch) {
						si ++; ei -= 2;
					} 
					// 두 경우 모두 다르면 연속으로 두 경우가 다르므로 2를 리턴.
					else return 2;
				}
				
			}
		
			return cnt;
		}
		
		// 팰린드롬 안에서 추출하여 회문인지 확인
		// 위 함수와 다른점은 이미 한 문자가 다르다는 전제하에 들어왔으므로 하나라도 다르면 false를 반환한다.
		public boolean subpalin(String name) {
			int len = name.length();
			int si = 0; int ei = len-1;
			for (int i=0; i< len/2;i++) {
				if (name.charAt(i) != name.charAt(len-i-1)) return false;
			}
			return true;
		}

	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 단어 갯수 입력
		T = Integer.parseInt(br.readLine());
		words = new Word[T];
		// 단어를 입력받고 바로 팰린드롬인지 확인한다.
		for (int i = 0; i < T; i++) {
			words[i] = new Word(br.readLine());
			sb.append(words[i].ispalin() + "\n");
		}
		
		// StringBuilder로 출력한다.
		System.out.println(sb);
		
		br.close();
	}
}
