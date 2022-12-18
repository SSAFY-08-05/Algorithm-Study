import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());//testcase  수
		
		for(int tc = 0; tc < T; tc++) {
			char[] p = br.readLine().toCharArray();//수행할 함수 p
			
			int n = Integer.parseInt(br.readLine()); //배열에 들어있는 수의 개수
			
			String str=br.readLine(); //[..,..,..,..]배열 입력
			 
			str=str.substring(1, str.length()-1); //[ ] 제거
			
			 
			String []input=str.split(","); //, 기준으로 나눔
			
			Deque<String> deq = new ArrayDeque<String>(); //reverse()를 하지않고도 비슷한 기능을 내기 위해 사용 
			
			for(int i = 0; i < n; i++) { //,기준으로 나뉜 값들을 deque에 넣어줌
				deq.add(input[i]);
			}
			
			boolean poss = true; //error여부 확인
			boolean reverse = true; //정방향 역방향 확인 true - 정방향 , false - 역방향
			
			for(int i = 0; i < p.length; i++) {
				switch(p[i]) {
				case 'R': //정방향 역방향 서로 바꿈
					reverse = !(reverse); 
					break;
				case 'D': // 첫번째 값 제거
					if(deq.isEmpty()) { //error상황
						poss = false;
						break;
					}
					
					if(reverse) { //정방향일 때
						deq.removeFirst();
					}else { //역방향일 때
						deq.removeLast();
					}
					break;
				}
			}
		
			
			StringBuilder sb = new StringBuilder(); 
			if(!poss) {
				sb.append("error");
			}else {
				String s = "1";
				sb.append("[");
				while(!deq.isEmpty()) {
					if(reverse) { //마지막이 정방향일 때
						s = deq.removeFirst();
					}else { //마지막이 역방향일 때
						s = deq.removeLast();
					}
					
					
					if(!deq.isEmpty()) {
						sb.append(s + ",");
					}else {
						sb.append(s);
						break;
					}
				}
				sb.append("]");
			}
								
			System.out.println(sb.toString());
							
		}
		
	}

}
