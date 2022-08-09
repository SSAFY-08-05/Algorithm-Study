//package week2.bj_5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 백준 5430 AC
public class Main {
	// 전역변수
	static int TC,N;
	static String command, rawdata;
	static Deque<Integer> queue;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 테스트 케이스 입력
		TC = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < TC; i++) {
			// 데이터 입력 및 사용 큐 선언
			command = br.readLine();
			N = Integer.parseInt(br.readLine());
			rawdata = br.readLine();
			rawdata = rawdata.substring(1,rawdata.length()-1);
			queue = new LinkedList<Integer>();
			
			// N이 0이라면 입력 과정이 필요없다.
			if(N>0) {
				int[] data = new int[N];
				int idx = -1;
				for(String s : rawdata.split(",")) {
					data[++idx] = Integer.parseInt(s);
					queue.add(data[idx]);
				}
			}
			
			// true : 기본 순서. 앞에서 빠짐
			// false : 역 순서. 뒤에서 빠짐
			boolean swit = true;
			// err 판별 변수
			boolean err = false;
			// 스위치문으로 명령 하나씩 실행		
			for (int j = 0; j < command.length(); j++) {
				switch(command.charAt(j)) {
				// 스위치 변수로 방향만 변경
				case 'R':
					swit = !swit;
					break;
				// 요소가 없이 뺀다면 바로 err로 직행
				case 'D':
					if(queue.isEmpty()) {
						err = true;
						break;
					}
					// 방향에 따라 큐에서 뺀다.
					if(swit) queue.poll();
					else queue.pollLast();
					break;
				default:
					break;
				}
				if(err) break;
			}
			
			// err이라면 error를 추가
			if(err) sb.append("error\n");
			// 요소가 없다면 괄호만 추가
			else if (queue.isEmpty()) sb.append("[]\n");
			// 요소가 있다면 없을때까지 빼서 추가하고 괄호닫기
			else {
				sb.append("[");
				if(swit) while(!queue.isEmpty()) sb.append(queue.poll()+",");
				else while(!queue.isEmpty()) sb.append(queue.pollLast()+",");
				
				sb.deleteCharAt(sb.length()-1);
				sb.append("]\n");
			}
		}
		
		System.out.println(sb);
		br.close();
	}

}
