package week16.bj_10472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int TC, goalFlag;
	static StringBuilder sb;
	static boolean visited[];
	static class Info{
		int flag, time;

		public Info(int flag, int time) {
			super();
			this.flag = flag;
			this.time = time;
		}
		
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < TC; t++) {
			goalFlag = 0;
			for (int i = 0; i < 3; i++) {
				String buffer = br.readLine().trim();
				for (int j = 0; j < 3; j++) {
					if(buffer.charAt(j) == '*')
						goalFlag = (goalFlag | (1 << (3*i+j)));
				}
			}
			
			sb.append(bfs()).append("\n");
		}
				
		System.out.println(sb);
		br.close();
	}
	static int bfs() {
		Queue<Info> queue = new LinkedList<>();
		queue.offer(new Info(0,0));
		visited = new boolean[1024];
		visited[0] = true;
		while(!queue.isEmpty()) {
			Info info = queue.poll();
			
			if(info.flag == goalFlag) return info.time;
			
			int nextflag = info.flag;
			
			nextflag = notGate(notGate(notGate(info.flag,0),1),3);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(notGate(info.flag,1),0),2),4);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(info.flag,2),1),5);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(notGate(info.flag,3),0),4),6);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(notGate(notGate(info.flag,4),1),3),5),7);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(notGate(info.flag,5),2),4),8);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(info.flag,6),3),7);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(notGate(info.flag,7),4),6),8);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
			
			nextflag = notGate(notGate(notGate(info.flag,8),5),7);
			if(!visited[nextflag]) {
				queue.offer(new Info(nextflag,info.time + 1));
				visited[nextflag] = true;
			}
		}
		return 0;
	}
	static int notGate(int flag, int i) {
		if((flag & (1 << i)) == 0) { // 추출한게 0이면
			return (flag | (1 << i));
		}else { // 추출한게 1이면
			return (flag - (1 << i));
		}
	}
}
