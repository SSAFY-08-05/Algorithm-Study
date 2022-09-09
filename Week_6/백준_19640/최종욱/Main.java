import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
class Info implements Comparable<Info>{
	int D; //근무 일수
	int H; //급합 정도
	int L; //서있는 줄
	boolean deca;
	
	Info(int D, int H, int L, boolean deca){
		this.D = D; 
		this.H = H;
		this.L = L;
		this.deca = deca;
	}

	@Override
	public int compareTo(Info o) {
		if(this.D == o.D) { //근무 일수가 같으면 화장실이 급한 정도
			if(this.H == o.H) { //화장실 급한 정도가 같으면 줄번호가 낮은 사람부터
				return this.L - o.L;
			}
			return o.H-this.H;
		}
		return o.D-this.D;
	}
}

public class Main {
	static int N,M,K;
	static Queue<Info> q[];

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken()); //대기인원
		M = Integer.parseInt(st.nextToken()); //사장 지시 새로운 줄
		K = Integer.parseInt(st.nextToken()); //데카보다 앞에 있는 인원
		
			
		q = new Queue[M];
		for(int i = 0; i < M; i++) {
			q[i] = new LinkedList<>();
		}
		
		int c = 0;//줄	
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int D = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int L = c;
			boolean deca = false; //데카의 위치
			if(i == K) { //입력받은 데카 앞 인원(K)와 동일한 값 == 데카위치
				deca = true;				
			}
			q[c++].add(new Info(D,H,L,deca));
			if(c == M) { //사장이 지시한 줄과 동일해지면 뒷줄로
				c = 0;
			}			
		}
		
		int num = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			if(!q[i].isEmpty()) {
				Info human = q[i].poll();
				pq.add(human);
			}
		}
						
		while(true) {
			Info human1 = pq.poll();
			if(human1.deca == true) {
				break;
			}
			else { //데카위치가 아니면
				if(!q[human1.L].isEmpty()) {
					pq.add(q[human1.L].poll());
				}
			}
			num++;//이용인원카운트 추가
		}
		
		System.out.println(num);

	}

}
