import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int N,X;
	static int count;
	static int[][] field;
	static int[][] field2;


	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			field = new int[N][N];
			field2 = new int[N][N];
				
			for(int i = 0; i< N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j = 0 ; j < N; j++) {
					field[i][j] = Integer.parseInt(st.nextToken());					
					field2[j][i] = field[i][j];
				}
			}
			
			count = 0;
			for(int i = 0; i < N; i++) {
				if(explore(field, i)) {
					count += 1;
				}		
				if(explore(field2, i)) {
					count += 1;
				}
			}

			
			System.out.println("#"+ tc +" "+count);

	}

}

	private static boolean explore(int[][] arr, int now) {
		int possible = 1;
		int height = arr[now][0];
		
		for(int i = 1; i < N; i++) {
			if(height == arr[now][i]) {
				possible += 1;
			}else if(arr[now][i] - height == 1) {//오르막길
				if(possible < X) {//올라오기 전 필드가 x만큼 존재하는지 여부
					return false;
				}else {
					possible = 1;
					height = arr[now][i];
				}
			}else if(height - arr[now][i] == 1) {//내리막길
				if(N < X+i) {//내려간 지점부터 남아있는 땅이  x보다 짧으면
					return false;
				}

				for(int j = 1; j < X; j++) {// 내려간 지점부터 x크기만틈 확인 같은 높이가 아니면 false
					if(height - arr[now][++i] != 1) {
						return false;
					}
				}
				height = arr[now][i];//for문을 수행하면 그 다음 위치부터 다시 확인	
				possible = 0;	
			}else {//차이가 1을 넘을 때
				return false;
			}		
		}
		
		
		return true;
	}


}
