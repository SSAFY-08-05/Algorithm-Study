//package week5.bj_2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 메모리 201244	시간 916
// 백준 2805 나무 자르기
public class Main {
	// 전역변수 생성
	static int N,M;
	static long height, amount;
	static PriorityQueue<Long> queue;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// n,m 입력받고 변소 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		// 내림차순 long형 큐를 생성
		queue = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return o2.compareTo(o1);
			}
		});
		
		for (int i = 0; i < N; i++) queue.offer(Long.parseLong(st.nextToken()));
		// 마지막 나무까지도 계산이 안났을 때를 대비해 높이가 0인 나무를 넣어준다.
		queue.offer(0L);
		
		int cnt = 0;
		height = 0;
		amount = 0;
		// 나무를 하나씩 빼서 높이를 책정해주고 높이계산을 한다.
		while(!queue.isEmpty()){
			Long curHeight = queue.poll();
			Long curAmount = amount + cnt * (height - curHeight);
			
			// 현재 량보다 많다면, (필요한 높이 - 현재 자른 량) 을  현재 잘리는 나무 갯수로 나눠주고 올림해서 그 높이를 뺀다. 
			if( M < curAmount ) {
				height -= Math.ceil((double)(M-amount)/cnt);
				break;
			}
			
			height = curHeight;
			amount = curAmount;
			cnt++;
		}
		
		System.out.println(height);
			
		br.close();

	}

}
