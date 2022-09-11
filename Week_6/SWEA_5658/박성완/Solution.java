//package week6.sw_5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
// 시간 27,624 kb 메모리 172 ms
// 모의 SW 5658 보물상자 비밀번호
public class Solution {
	// 전역변수 선언
	static StringBuilder sb;
	static int TC,N,K,len;
	static String data;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= TC; i++) {
			//입력받음
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 단어의 길이를 계산
			len = N/4;
			// 앞에서부터 i~i+len 까지의 길이를 하나씩 탐색할 것임. 그를 위해서 뒤에 첫 단어들을 붙인다.
			data = br.readLine().trim();
			data = data + data.substring(0, len-1);
			// 계산값을 바로 붙인다.
			sb.append("#" + i + " " + findKth() + "\n");
		}
		System.out.println(sb);
		br.close();
	}
	// k번째 큰 수를 찾는 알고리즘
	static int findKth() {
		// treeset와 우선순위 큐 생성
		Set<String> set = new TreeSet<>();
		PriorityQueue<String> queue = new PriorityQueue<>((n1,n2)->n2.compareTo(n1));
		// 단어를 추출해 set에 없으면 넣으면서 큐에 넣는다.
		for (int i = 0; i < N; i++) {
			String buffer = data.substring(i,i+len);
			if(set.add(buffer)) queue.offer(buffer);
		}
		
		String ans = "";
		// K만큼 빼서
		for (int i = 0; i < K; i++) ans = queue.poll();
		// K번째를 변환해 전달
		return Integer.parseInt(ans,16);
	}
}
