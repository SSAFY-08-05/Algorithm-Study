import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 19244	시간 288
// 백준 17232 생명 게임
public class Main {
	// 전역변수 생성
	static int N,M,T,K,a,b,count,hap[][], SX, SY, EX, EY;
	static Block map[][];
	static class Block{
		boolean cur_life, next_life, birth;

		public Block() {
			this.cur_life = false;
			this.next_life = false;
			this.birth = false;
		}

		public Block(char c) {
			if(c == '*') this.cur_life = true;
			else this.cur_life = false;
			this.next_life = false;
			this.birth = false;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 변수 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		map = new Block[N][M];
		hap = new int[N][M];
		
		// 블록 입력
		for (int i = 0; i < N; i++) {
			String buffer = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = new Block(buffer.charAt(j));
			}
		}
		
		for (int i = 0; i < T; i++) {
			// 누적합 테이블 계산
			hap[0][0] = map[0][0].cur_life ? 1 : 0;
			
			for (int x = 1; x < N; x++) {
				hap[x][0] = hap[x-1][0] + (map[x][0].cur_life ? 1 : 0);
			}
			for (int y = 1; y < M; y++) {
				hap[0][y] = hap[0][y-1] + (map[0][y].cur_life ? 1 : 0);
			}
			
			for (int x = 1; x < N; x++) {
				for (int y = 1; y < M; y++) {
					hap[x][y] = hap[x-1][y] + hap[x][y-1] - hap[x-1][y-1] + (map[x][y].cur_life ? 1 : 0);
				}
			}
			
			// 누적 합을 이용하여 주변 확인하며 전처리
			for (int x = 0; x < N; x++) {
				SX = Math.max(-1, x-K-1);
				EX = Math.min(N-1, x+K);
				for (int y = 0; y < M; y++) {
					SY = Math.max(-1, y-K-1);
					EY = Math.min(M-1, y+K);
					
					int cur = hap[EX][EY];
					if( SY != -1 ) cur-= hap[EX][SY];
					if( SX != -1 ) cur-= hap[SX][EY];
					if( (SX != -1) && (SY != -1)) cur += hap[SX][SY];
					
					Block curb = map[x][y];
										
					if( curb.cur_life) {
						cur--;
						if ( cur < a || cur > b ) curb.next_life = false;
						else curb.next_life = true;
					}else {
						if ( a < cur && cur <= b) curb.birth = true;
					}
					
				}
			}
			
			// 결과를 바탕으로 다음 세대 처리
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					Block curb = map[x][y];
					if(curb.birth) {
						curb.cur_life = true;
						curb.birth = false;
					}else {
						curb.cur_life = curb.next_life;
					}
				}
			}
		}
		print();
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		for (Block[] arr : map) {
			for (Block b : arr) {
				if(b.cur_life) sb.append('*');
				else sb.append('.');
			}
			sb.append("\n");
		}
		sb.append("\n");
		System.out.println(sb);
	}
}
