
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int N,K,turn, numberlength,password;
	static String[] number;
	static ArrayList<String> arr;
	static HashSet<String> set;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
					
			numberlength = N/4;
			number = new String[4]; 
			arr = new ArrayList<String>();
			set = new HashSet<String>();
			
			String str = bf.readLine();
			String[] s = str.split("");
			
			int index = 0;
			
			for(int i = 0; i < str.length(); i += numberlength) { //생성비밀번호들
				number[index++] = str.substring(i, i + numberlength);
			}
			
			for(int i = 0 ; i < N; i++) {//회전용
				arr.add(s[i]);
			}
			
			for(int i = 0 ; i < number.length; i++) {//같은 번호로 다시 돌아오는 회전을 찾기 위함
				set.add(number[i]);
			}
			turn = 0;
			turning();
			
			cal();
	
			System.out.println("#"+ tc + " " +password);
		}
	}

	private static void cal() { //K번째 큰 수 10진수 전환
		ArrayList<String> list = new ArrayList<String>(set);
		
		Collections.sort(list);
		
		String result = list.get(list.size()-K);
		
		String[] re = result.split("");
		
		password = Integer.parseInt(result, 16); //16진수 값 10진수로 전환
		
		return;
		
	}

	private static void turning() {//보물상자 뚜껑회전
		while(turn < 4) {

			String a = arr.get(arr.size()-1);
			arr.add(0, a);
			arr.remove(arr.size()-1);
			
			String sums = String.join("", arr);
		
			int index = 0;			
			for(int i = 0; i < sums.length(); i += numberlength) { //생성비밀번호들
				number[index++] = sums.substring(i, i + numberlength);
			}
			
			for(int i = 0; i < number.length; i++) { //이미 번호가 존재하지 않으면 값을 넣어주고 있으면 trun을 더해 후에 값들이 다시 돌아오는 걸 알도록 함
				if( set.contains(number[i]) ) {
					turn += 1;
				}else {
					set.add(number[i]);
				}
			}			
		}
		return;
		
	}

}
