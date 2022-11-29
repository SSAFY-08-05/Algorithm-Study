import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 16987 계란으로 계란치기
public class Main {
	static int N, maxx;
	static Egg[] eggs;

	static class Egg {
		int s, w;

		public Egg(int s, int w) {
			super();
			this.s = s;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Egg [s=" + s + ", w=" + w + "]";
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		maxx = 0;
		simul(0, 0);

		System.out.println(maxx);

	}

	static void simul(int cur, int score) {
		if (cur >= N) {
			maxx = Math.max(maxx, score);
			return;
		}
		if (eggs[cur].s <= 0) {
			simul(cur + 1, score);
		} else {
			boolean breaked_at_least_once = false;
			for (int i = 0; i < N; i++) {
				if (i == cur || eggs[i].s <= 0)
					continue;
				breaked_at_least_once = true;
				eggs[cur].s -= eggs[i].w;
				eggs[i].s -= eggs[cur].w;
				simul(cur + 1, score + (eggs[cur].s <= 0 ? 1 : 0) + (eggs[i].s <= 0 ? 1 : 0));
				eggs[cur].s += eggs[i].w;
				eggs[i].s += eggs[cur].w;
				
			}
			if(!breaked_at_least_once) simul(cur + 1 , score);
		}
		
	}
}
