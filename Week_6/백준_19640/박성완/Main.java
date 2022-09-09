//package week6.bj_19640;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
// 55704 580
// 백준 19640 화장실의 규칙
public class Main2 {
	static StringBuilder sb;
	static int N,M,K;
	static class Person implements Comparable<Person>{
		int day, hurry, line;
		boolean dekar;

		public Person(int day, int hurry, int line) {
			super();
			this.day = day;
			this.hurry = hurry;
			this.line = line;
		}

		@Override
		public int compareTo(Person o) {
			if(this.day == o.day) {
				if(this.hurry == o.hurry) {
					return this.line - o.line;
				} else return o.hurry - this.hurry;
			} else return o.day - this.day;
			
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		@SuppressWarnings("unchecked")
		Queue<Person> q[] = new LinkedList[M]; 
				
		for (int i = 0; i < M; i++) {
			q[i] = new LinkedList<>();
		}
		
		for (int n = 0; n < N ; n++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int hurry = Integer.parseInt(st.nextToken());
			
			Person p = new Person(day,hurry,n%M);
			if (n==K) p.dekar = true;
						
			q[n%M].offer(p);
		}
		
		PriorityQueue<Person> queue = new PriorityQueue<>();
		
		for (int i = 0; i < M; i++) {
			if(!q[i].isEmpty())queue.offer(q[i].poll());
		}
		
		int counter = 0;
		while(!queue.isEmpty()) {
			Person cur = queue.poll();
			if(cur.dekar) break;
			counter++;
			if(!q[cur.line].isEmpty())queue.offer(q[cur.line].poll());
		}
		System.out.println(counter);
		
	}

}
