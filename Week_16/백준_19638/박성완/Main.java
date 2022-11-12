package week16.bj_19638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 메모리 31908	시간 448
// 백준 19638 센티와 마법의 뿅망치
public class Main {
	// 전역번수
	static int N,H,T,least;
	static PriorityQueue<Integer> pqueue;
	public static void main(String[] args) throws IOException {
		// 입력 방식
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		// 우선순위 큐
		pqueue = new PriorityQueue<>((o1,o2) -> (o2-o1));
		
		for (int i = 0; i < N; i++) {
			pqueue.offer(Integer.parseInt(br.readLine()));
		}
		
		//횟수만큼 작동
		least = Integer.MAX_VALUE;
		for (int i = 0; i < T; i++) {
			// 중간에 키가 모두 작다면 최솟값 저장하고 나옴
			if(pqueue.peek() < H) {
				least = i;
				break;
			}
			// 중간에 최대가 1이라면 더이상 계산이 불필요
			if(pqueue.peek() == 1) break;
			pqueue.offer((int)Math.floor(pqueue.poll()/2));
		}
		// 조건에 따라 결과 출력
		if(pqueue.peek() < H) {
			System.out.println("YES\n" + Math.min(least,T));
		}else {
			System.out.println("NO\n" + pqueue.peek());
		}
	}

}
