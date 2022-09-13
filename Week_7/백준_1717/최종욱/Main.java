import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	static int n,m;
	static int[] parents;
	
	static void union(int a, int b) { //집합 합치기
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot)return;
		else {
			parents[bRoot] = aRoot;
			return;
		}
		
	}
	
	static int find(int a) { //집합의 첫 값? 찾기
		if(a == parents[a])return a;
		else return parents[a] = find(parents[a]);
	}


	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1]; //합친 집합의 첫 값을 찾기 위한 배열
		for(int i = 0; i<=n; i++ ) {
			parents[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken()); //통합(= 0) or 집합 포함 여부(=1) 
			int a = Integer.parseInt(st.nextToken()); //합칠 집합의 값
			int b = Integer.parseInt(st.nextToken()); // 합칩 집합의 값
			
			
			if(num == 0) {
				union(a , b);
			}
			else {
				if(find(a) == find(b)) {
					System.out.println("YES");
				}
				else {
					System.out.println("NO");
				}
			}
		}			
	}
}
