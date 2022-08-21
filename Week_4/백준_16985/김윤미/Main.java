
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int z;
	int x;
	int y;
	
	Pair(int z, int x, int y) {
		this.z=z;
		this.x=x;
		this.y=y;
	}
}

public class Main {
	
	static int [][][]arr; //입력받은 5*5*5 배열 저장
	static int [][][]rotated; //회전된 5*5*5 배열 저장
	static int[][][]filed; //쌓아진 5*5*5 배열 저장
	static int dis[][][]; //참가자 이동 거리 저장
	static int n=5; //배열 사이즈
	static int dx[]=new int[] {-1, 1, 0, 0, 0, 0};
	static int dy[]=new int[] {0, 0, 1, -1, 0, 0};
	static int dz[]=new int[] {0, 0, 0, 0, 1, -1}; //참가자의 이동
	static boolean[]isused; //순서 정하기 용도
	static int[]res; //순서 정한 판 넣는 배열
	static int ans=Integer.MAX_VALUE; //출력값
	
	static int[][] rotate(int [][]input) { //5*5 판 1번 회전
		int [][]tmp=new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				tmp[j][n-1-i]=input[i][j];
			}
		}
		
		return tmp;
	}
	
	static void setRotate(int k) { //회전 횟수 정하기
		if(k==n) {
			for(int i=0; i<n; i++) { //5개의 판에 대해서
				for(int r=0; r<res[i]; r++) { //회전 횟수만큼 각각 회전시키기
					rotated[i]=rotate(rotated[i]);
				}
			}
			isused=new boolean[n];
			setFile(0); //다 회전시키면 쌓기
			for(int i=0; i<n; i++) {
				rotated[i]=arr[i]; //다시 복구
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			res[k]=i;
			setRotate(k+1);
		}
	}
	
	static void setFile(int k) { //쌓을 순서 정하기
		if(k==n) {
			int num=move(); //참가자 이동해보기
			if(num!=-1) {
				ans=Math.min(ans, num);
			}
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!isused[i]) {
				isused[i]=true;
				filed[k]=rotated[i];
				setFile(k+1);
				isused[i]=false;
			}
		}
	}
	
	static int move() { //참가자 이동
		
		//입구나 출구가 막혀있으면 불가
		if(filed[0][0][0]==0 || filed[n-1][n-1][n-1]==0) {
			return -1;
		}
		
		dis=new int[n][n][n]; //거리 저장
		
		Queue<Pair> q=new ArrayDeque<>(); //bfs
		q.add(new Pair(0, 0, 0));
		dis[0][0][0]=1;
		
		while(!q.isEmpty()) {
			Pair p=q.poll();
			if(p.z==n-1 && p.x==n-1 && p.y==n-1) {
				return dis[p.z][p.x][p.y];
			}
			
			for(int d=0; d<6; d++) {
				int nz=p.z+dz[d];
				int nx=p.x+dx[d];
				int ny=p.y+dy[d];
				
				if(nz<0 || nz>=n || nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(filed[nz][nx][ny]==0) continue;
				if(dis[nz][nx][ny]>0) continue;
				
				dis[nz][nx][ny]=dis[p.z][p.x][p.y]+1;
				q.add(new Pair(nz, nx, ny));
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		arr=new int[n][n][n];
		rotated=new int[n][n][n];
		filed=new int[n][n][n];
		res=new int[n];
		
		for(int j=0; j<n*n; j++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int k=0; k<n; k++) {
				arr[j/n][j%n][k]=Integer.parseInt(st.nextToken());
				rotated[j/n][j%n][k]=arr[j/n][j%n][k];
			}
		}
		
		isused=new boolean[n];
		setRotate(0);
		
		if(ans!=Integer.MAX_VALUE) System.out.println(ans-1);
		else System.out.println(-1);
	}

}
