import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
// 백준  1021 회전하는 큐
public class Main {
	// 전역변수 선언
	static int N,M, idx,cnt,target;
	static Deque<Integer> queue;
	static boolean[] isVisit;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// N,M 입력
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		// 탐색 데이터 입력
		st = new StringTokenizer(br.readLine());
		// 현재 인덱스, 2,3연산횟수
		idx = 0;	cnt = 0; 
		// 방문여부 배열 (1~N -> 0~N-1)
		isVisit = new boolean[N];
		// 실제 회전 큐가 작동할 큐
		queue = new LinkedList<Integer>();
		// 초기 데이터 입력
		for (int i = 1; i <= N ; i++) queue.add(i);
		// 탐색 횟수만큼 진행
		for (int i = 0; i < M; i++) {
			// 탐색 숫자
			target = Integer.parseInt(st.nextToken());
			// 현재 위치를 기준으로 왼쪽, 오른쪽으로 얼마만큼 떨어져있는지 계산한다.
			// 이 때, 실제 탐색은 -1하여 이뤄진다.
			int left = searchToLeft(target-1);
			int right = searchToRight(target-1);
			// 둘 중 더 작은 경우로 진행된다. 같으면 사실 상관없다.
			if(left < right) {
				// 횟수를 더하고,인덱스를 이동한다.
				cnt += left;
				idxMove(true,left);
				// 왼쪽 방향으로 탐색했었으니 회전은 3번 연산이다.
				for (int j = 0; j < left; j++) queue.addFirst(queue.pollLast());
				
			}else{
				// 횟수를 더하고, 인덱스를 이동한다.
				cnt += right;
				idxMove(false,right);
				// 오른쪽 방향으로 탐색했으니 회전은 2번 연산이다.
				for (int j = 0; j < right; j++) queue.addLast(queue.pollFirst());
			}
			// 요소를 빼고 해당 요소를 방문처리한다. 이때도 실제로 는 -1해야 한다.
			isVisit[(queue.pollFirst()+N-1)%N] = true;
			// 모든 요소를 방문했다면 idx가 무한으로 이동할 수 있다. 큐가 비었다면 작동시키지 않는다.
			if(!queue.isEmpty()) idxMove(false,1);
		}
		// 결과를 출력한다.
		System.out.println(cnt);

	}
	// idx가 움직이는 함수
	// swit가 true면 왼쪽으로, false면 오른쪽으로 움직인다.
	static void idxMove(boolean swit, int amount) {
		// true는 이미 방문한 요소이므로 지나가야 한다.
		while(amount > 0) {
			if(swit) idx = (idx+N-1)%N;
			else idx = (idx+1)%N;
			if(!isVisit[idx]) {
				amount--;
			}
		}
	}
	// 오른쪽 방향으로 탐색
	static int searchToRight(int num) {
		// 횟수와 인덱스
		int count = 0;
		int index = idx;
		// 현위치면 0 반환
		if (index==num) return 0;
		// 마찬가지로 아직 방문하지 않은 false요소만 탐색한다.
		while(true) {
			index = (index+1)%N;
			if(!isVisit[index]) {
				count++;
				if(index==num) return count;
			}
		}
	}
	// 왼쪽 방향으로 탐색
	static int searchToLeft(int num) {
		// 횟수와 인덱스
		int count = 0;
		int index = idx;
		// 현위치면 0 반환
		if (index==num) return 0;
		// 마찬가지로 아직 방문하지 않은 false요소만 탐색한다.
		while(true) {
			index = (index+N-1)%N;
			if(!isVisit[index]) {
				count++;
				if(index==num) return count;
			}
		}
	}
}
