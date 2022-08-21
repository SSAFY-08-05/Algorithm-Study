
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static boolean[][]arr;
	static boolean[]visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		m=Integer.parseInt(br.readLine());
		
		arr=new boolean[n+1][n+1];
		visited=new boolean[n+1];
		
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			arr[x][y]=true;
			arr[y][x]=true;
		}
		
		Queue<Integer>q=new ArrayDeque<>();
		q.add(1); //1번컴퓨터
		visited[1]=true;
		
		while(!q.isEmpty()) {
			int num=q.poll();
			for(int i=1; i<=n; i++) {
				if(!arr[i][num]) continue;
				if(visited[i]) continue;
				else {
					visited[i]=true;
					q.add(i);
				}
			}
		}
		int ans=0;
		for(int i=1; i<=n; i++) {
			if(visited[i]) ans++;
		}
		System.out.println(ans-1);
	}

}
