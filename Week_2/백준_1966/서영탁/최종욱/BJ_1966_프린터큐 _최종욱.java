import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
		
			st = new StringTokenizer(bf.readLine());		
			int paper = Integer.parseInt(st.nextToken()); //문서의 개수
			int location =Integer.parseInt(st.nextToken());//출력 순서 궁금한 문서 위치
			int out = 0; //출력 횟수 
			
			Queue<Integer> important = new LinkedList<>();
			
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < paper; i++) { //문서별 중요도 입력
				important.add(Integer.parseInt(st.nextToken()));
			}
						
			while(location != -1) { //궁금한 문서가 출력될 때 까지
				int max = Collections.max(important); //현재 문서 중 가장 우선순위 문서
				int temp = important.peek();
				
				if(location == 0) {//궁금한 문서가 제일 앞 도착
					if(temp != max) {  //현재 중요도가 가장 높은지 확인
						important.remove();
						important.add(temp);
						location = important.size()-1; // 가장 높지않으면 위치를 큐 가장 뒤로
					}else {
						location -= 1; //가장 높으니 출력
						out += 1; //출력 순서도 +1
					}
				}else if(temp != max) {
					important.remove();
					important.add(temp);
					location -= 1;					
				}else {
					important.remove();
					out += 1;
					location -= 1;
				}
				
			}
			
			System.out.println(out);	//순서 출력								
		}
								
	}

}
