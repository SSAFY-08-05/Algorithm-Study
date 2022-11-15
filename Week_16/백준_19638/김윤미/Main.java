
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, h, t;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		h=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		
		pq=new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.valueOf(o2).compareTo(Integer.valueOf(o1));
			}
		});
		
		for(int i=0; i<n; i++) {
			int num=Integer.parseInt(br.readLine());
			pq.add(num);
		}
		
		int cnt=0;
		while(true) {
			if(!pq.isEmpty()) {
				int num=pq.peek();
				if(num<h) {
					System.out.println("YES");
					System.out.println(cnt);
					break;
				}
				else {
					if(cnt<t) { //횟수 남음
						pq.poll();
						if(num!=1) pq.add(num/2);
						else pq.add(num);
						cnt++;
					}
					else {
						System.out.println("NO");
						System.out.println(num);
						break;
					}
				}
			}
		}
		
	}

}
