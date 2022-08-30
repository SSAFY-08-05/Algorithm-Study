

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int n;
	static int arr[][];
	static int ans=0;
	
	static boolean isRangeOf(int x, int y) {
		return (x>=1 && x<=n && y>=1 && y<=n)?true:false;
	}
	
	static void dfs(int x, int y, int dir) { //dir=0, 1, 2 (가로, 대각선, 세로)
		if(x==n && y==n) {
			ans++;
			return;
		}
		
		if(dir==0) { //가로방향일 때 - 가로 아님 대각선
			if(isRangeOf(x, y+1) && arr[x][y+1]!=1) {
				dfs(x, y+1, 0); //가로 방향
			}
		}
		else if(dir==1) { //대각선 방향일 때 - 가로 아님 세로 아님 대각선
			if(isRangeOf(x, y+1) && arr[x][y+1]!=1) {
				dfs(x, y+1, 0); //가로 방향
			}
			if(isRangeOf(x+1, y) && arr[x+1][y]!=1) {
				dfs(x+1, y, 2); //세로 방향
			}
		}
		else if(dir==2) { //세로 방향일 때 - 세로 아님 대각선
			if(isRangeOf(x+1, y) && arr[x+1][y]!=1) {
				dfs(x+1, y, 2); //세로 방향
			}
		}
		
		if(isRangeOf(x+1, y+1) && isRangeOf(x, y+1) && isRangeOf(x+1, y) && arr[x][y+1]!=1 && arr[x+1][y]!=1 && arr[x+1][y+1]!=1) {
			dfs(x+1, y+1, 1); //대각선 방향
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 2, 0);
		System.out.println(ans);
	}

}
