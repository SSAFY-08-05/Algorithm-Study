
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int x;
	int y;
	
	Pair(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class Main {
	static int n; //배열 크기
	static List<Integer> homes;
	static int [][]arr; //입력받은 배열
	static boolean[][]visited; //bfs 방문여부
	static int []dx=new int[] {-1, 0, 1, 0};
	static int[]dy=new int[] {0, 1, 0, -1};
	
	static void bfs(int x, int y) { //bfs 시작 좌표 전달받기
		Queue<Pair> q=new ArrayDeque<>();
		q.add(new Pair(x, y));
		visited[x][y]=true;
		int cnt=1;
		
		while(!q.isEmpty()) {
			Pair p=q.poll();
			
			for(int d=0; d<4; d++) {
				int nx=p.x+dx[d];
				int ny=p.y+dy[d];
				
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(arr[nx][ny]==0) continue;
				if(visited[nx][ny]) continue;
				
				visited[nx][ny]=true;
				q.add(new Pair(nx, ny));
				cnt++;
			}
		}
		homes.add(cnt);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n][n];
		for(int i=0; i<n; i++) {
			char []input=br.readLine().toCharArray();
			for(int j=0; j<n; j++) {
				arr[i][j]=input[j]-'0';
			}
		}
		int homeCnt=0;
		homes=new ArrayList<>();
		visited=new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j]==0 || visited[i][j]) continue;
				homeCnt++;
				bfs(i, j);
			}
		}
		Collections.sort(homes);
		
		sb.append(homeCnt).append("\n");
		for(int num:homes) {
			sb.append(num).append("\n");
		}
		System.out.println(sb.toString());
	}

}
