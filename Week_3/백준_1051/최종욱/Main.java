import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Square {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			String str = bf.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		int len = Math.min(n, m);
		int a = 0;
		int max = 0;
		
		while(len >= 1) {
			for(int i = 0; i <= n-len; i++) {		
				for(int j = 0; j <= m-len; j++) {
					int num = arr[i][j];			
					if(num == arr[i][j+len-1] && num == arr[i+len-1][j] && num == arr[i+len-1][j+len-1]) {
						a = len*len;
						break;
					}
				}
				if(a > max) {
					max = a;
				}
			}
			len -= 1;
		}
		System.out.println(max);
		
		

	}

}
