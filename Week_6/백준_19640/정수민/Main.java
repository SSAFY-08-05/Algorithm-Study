package week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_19640_화장실의규칙 {
	
	public static class Info implements Comparable<Info>{
		int D, H, order, num;
		
		public Info(int D, int H, int order, int num) {
			this.D = D;
			this.H = H;
			this.order = order;
			this.num = num; 
		}

		@Override
		public int compareTo(Info o) {
			if(this.D == o.D) { //근무일수
				if(this.H==o.H) { //급함
					return this.order-o.order;
				}else {
					return o.H - this.H;
				}
			}else {
				return o.D - this.D;
			}
		}
	}
	
	static int N, M, K, D, H;
	static LinkedList<Info> q[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //사원 수
		M = Integer.parseInt(st.nextToken()); //새로운 줄 수
		K = Integer.parseInt(st.nextToken()); //앞에 서 있던 사원 수
		
		q = new LinkedList[M];
		
		for (int i = 0; i < M; i++) {
			q[i] = new LinkedList<Info>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); //근무일수
			H = Integer.parseInt(st.nextToken()); //급한정도
			
			q[i%M].add(new Info(D,H,i%M,i));
		}
		
		int res = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			if(!q[i].isEmpty()) {
				pq.add(q[i].poll());
			}
		}
		while(true) {
			Info cur = pq.poll();
			if(cur.num==K) { //데카 순서가 왔을 때
				break;
			}else {
				if(!q[cur.order].isEmpty()) {
					pq.add(q[cur.order].poll());
				}
			}
			res++;
		}System.out.println(res);
	}

}
