
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
	
	static boolean np(int []numbers) {
		int i=blank-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i;
		
		if(i==0) return false;
		
		int j=blank-1;
		while(numbers[i-1]>=numbers[j]) --j;
		
		swap(numbers, i-1, j);
		
		int k=blank-1;
		while(i<k) swap(numbers, i++, k--);
		
		return true;
	}
	
	static void swap(int numbers[], int i, int j) {
		int tmp=numbers[i];
		numbers[i]=numbers[j];
		numbers[j]=tmp;
	}

	static int n, m;
	static int blank=0; //빈 칸의 개수
	static int arr[][];
	static Pair[]blanks; //빈 칸 배열
	static Queue<Pair> q;
	static int dx[]=new int[] {-1, 0, 1, 0};
	static int dy[]=new int[] {0, 1, 0, -1};
	static boolean[][]visited;
	
	static int bfs() {
		int sum=0;
		visited=new boolean[n][m];
		Queue<Pair> tmpq=new ArrayDeque<>();
		for(Pair p:q) {
			tmpq.add(p);
		}
		
		for(Pair p:tmpq) {
			visited[p.x][p.y]=true;
		}
		
		while(!tmpq.isEmpty()) {
			Pair p=tmpq.poll();
			for(int d=0; d<4; d++) {
				int nx=p.x+dx[d];
				int ny=p.y+dy[d];
				
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(visited[nx][ny]) continue;
				if(arr[nx][ny]==1) continue;
				
				sum++;
				visited[nx][ny]=true;
				tmpq.add(new Pair(nx, ny));
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		q=new ArrayDeque<>();
		
		arr=new int[n][m];
		blanks=new Pair[n*m];
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) {
					blanks[blank++]=new Pair(i, j);
				}
				else if(arr[i][j]==2) {
					q.add(new Pair(i, j));
				}
			}
		}
		
		int []input=new int[blank];
		for(int i=blank-3; i<blank; i++) {
			input[i]=1; //3개 뽑기
		}
		int max=Integer.MIN_VALUE;
		do {
			int ans=n*m;
			for(int i=0; i<blank; i++) {
				if(input[i]==1) {
					Pair p=blanks[i];
					arr[p.x][p.y]=1;
				}
			}
			
			ans=blank-bfs()-3;
			max=Math.max(max, ans);
			
			for(int i=0; i<blank; i++) {
				if(input[i]==1) {
					Pair p=blanks[i];
					arr[p.x][p.y]=0;
				}
			}
		}while(np(input));
		
		System.out.println(max);
	}

}
