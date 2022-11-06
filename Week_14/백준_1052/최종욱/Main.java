import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //물병 갯수
		int K = Integer.parseInt(st.nextToken()); //한번에 옮길 물병 갯수
		
		
		int count = 0; //물병 그룹을 샘
		int result = 0;//상점에 갈 횟수
		int x = N;
		
		if(N < K ) {
			System.out.println(0);
			return;
		}
		
		while(true) {
			count = 0;
			int n = N;
			while(n != 0) {
				if(n % 2 == 1) { //홀수 = 합치고 하나가 남음
					count += 1;//물병 그룹 +1
				}
				n = n/2;
			}
			if(count <= K) { //그룹의 수가 K보다 작으면 끝
				break;
			}
			N += 1; //그룹의 수가 K보다 많으면 상점에 가야하므로 +1
			result += 1; //상점간 횟수 추가
		}


		
		System.out.println(result);

	}

}
