import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static String s;
	public static char[] str;
	public static int word1, word2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			s = br.readLine(); //회문 여부 확인할 단어
			str = s.toCharArray();
			
			int left = 0; //가장 좌측
			int right = s.length()-1; //가장 우측
			
			if(OneCheck(left, right)) { //회문 여부 판단
				System.out.println(0);
				continue;
			}if(TwoCheck(left, right)) { //유사회문 여부 판단
				System.out.println(1);				
			}else {
				System.out.println(2); //회문이 아님
			}
			
			
		}
	}

	private static boolean TwoCheck(int left, int right) {
		while(left <= right) { //좌측과 우측 만나는 순간까지
			if(str[left] != str[right]) {  //일치하지 않는 부분 존재
				boolean a = OneCheck(left+1,right); //좌측 불일치 단어 제거
				boolean b = OneCheck(left, right-1); //우측 불일치 단어 제거
				
				if(a == false && b == false) { //제거 후에도 회문이아니면 유사회문 x
					return false;
				}else {
					return true;
				}
			}
			left++;
			right--;
		}
		
		return true;
	}

	private static boolean OneCheck(int left, int right) {	
		while(left <= right) { //좌측과 우측 만나는 순간까지
			if(str[left] != str[right]) return false; //일치하지 않는 단어가 있으면 회문x
			
			left++;
			right--;
		}
		
		return true;
	}

}
