
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
//메모리11612KB 시간 84ms
public class Main {
	static int N,M;
	static int[] pick;
	static ArrayList<Integer> element;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //원소큐의 크기
		
		M = Integer.parseInt(st.nextToken()); //뽑아내려고 하는 수의 개수
		
		pick = new int[M];//뽑으려는 수 정보 저장할 배열
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) { //뽑으려는 수 정보 저장
			pick[i] = Integer.parseInt(st.nextToken());
		}
		
		element = new ArrayList<>(); //원소큐의 수 저장 list
		
		for(int i = 1; i <= N; i++) { //list에 큐크기 만큼 채움
			element.add(i);
		}
		
		int count = 0; //왼쪽 이동, 오른쪽 이동의 횟수 저장
		
		for(int i = 0; i < M; i++) {
			while(pick[i] != element.get(0)) { //뽑으려는 수와 첫번째 원소의 값이 동일할 때 까지 반복
				int temp = 0;
				if(location(pick[i]) > element.size()/2) { //뽑으려는 수의 위치가 리스트 크기 중간 보다  뒤에 있다면 오른쪽으로 이동
					temp = element.get(element.size()-1); //제일 뒤에 값을 뽑아 앞으로 이동
					element.remove(element.size()-1);
					element.add(0, temp);
					count += 1;
				}else {  //뽑으려는 수의 위치가 리스트 크기 중간 보다 앞이라면 왼쪽으로 이동
					temp = element.get(0); //첫번째 값을 뽑아 뒤로 이동
					element.remove(0);
					element.add(temp);
					count += 1;
				}			
			}
			element.remove(0); //첫번째 원소와 뽑으려는 수가 동일하면 제거			
		}
		
		System.out.println(count); //왼쪽 오른쪽 연산 횟수 출력
		
		

	}

	private static int location(int number) { //리스트에서 뽑으려는 수의 위치
		int loc = 0;
		for(int i = 0; i < element.size(); i++) {
			if(element.get(i) == number) {
				loc = i;
			}
		}
		return loc;
	}

}
