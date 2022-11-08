import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class info{
		int screen;
		int clipboard;
		int time;
		
		public info(int screen, int clipboard, int time) {
			super();
			this.screen = screen;
			this.clipboard = clipboard;
			this.time = time;
		}
		
		
	}
	static int s;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = Integer.parseInt(br.readLine());
		visited = new boolean[1001][10001]; //중복된 정보 제거를 위한 배열
		
		bfs();
		
		

	}

	private static void bfs() {
		Queue<info> q = new LinkedList<>();
		q.offer(new info(1,0,0)); //처음 화면 이모티콘 하나
		visited[1][0] =true;
		
		while(!q.isEmpty()) {
			info cur = q.poll(); //현재 화면 클립보드 시간 정보
			
			if(cur.screen == s) { //원하는 이모티콘 수에 도달
				System.out.println(cur.time);
				break;
			}
			
			//클립보드에 복사
			if(cur.screen != 0 && !visited[cur.screen][cur.screen]) { //화면이 안비어 있고 이전에 들어왔던 값이 아니면
				q.offer(new info(cur.screen, cur.screen, cur.time + 1));
				visited[cur.screen][cur.screen] = true;
			}
			
			//화면에 클립보드 붙여넣기
			if(cur.clipboard != 0 &&cur.screen+cur.clipboard <= 1000  && !visited[cur.clipboard + cur.screen][cur.clipboard] ) {
				q.offer(new info(cur.screen + cur.clipboard, cur.clipboard, cur.time + 1));
				visited[cur.clipboard + cur.screen][cur.clipboard] = true;
			}
			
			//화면에 있는 이모티콘 중 하나 제거
			if(cur.screen > 0 && !visited[cur.screen - 1][cur.clipboard]) {
				q.offer(new info(cur.screen-1, cur.clipboard, cur.time+1));
				visited[cur.screen - 1][cur.clipboard] = true;
			}
			
		}
		
	}

}
