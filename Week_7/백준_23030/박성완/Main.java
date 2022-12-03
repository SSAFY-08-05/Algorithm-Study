package week7.bj_23030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 23030 후다다닥을 이겨 츄르를 받자!
public class Main {
	static int N, M, K, T, U, V, least;
	static int station[];
	static boolean visited[];
	static final int connected = -1;
	static final int disconnected = -2;
	static StringBuilder sb;

	static class Info implements Comparable<Info> {
		int no, time;

		public Info(int no, int time) {
			super();
			this.no = no;
			this.time = time;
		}

		@Override
		public int compareTo(Info o) {
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		station = new int[N * 100];
		visited = new boolean[N * 100];

		Arrays.fill(station, disconnected);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Arrays.fill(station, i * 100, i * 100 + Integer.parseInt(st.nextToken()), connected);
		}

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = (Integer.parseInt(st.nextToken()) - 1) * 100 + (Integer.parseInt(st.nextToken()) - 1);
			int to = (Integer.parseInt(st.nextToken()) - 1) * 100 + (Integer.parseInt(st.nextToken()) - 1);

			station[from] = to;
			station[to] = from;
		}

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			least = Integer.MAX_VALUE;
			T = Integer.parseInt(st.nextToken());
			U = (Integer.parseInt(st.nextToken()) - 1) * 100 + (Integer.parseInt(st.nextToken()) - 1);
			V = (Integer.parseInt(st.nextToken()) - 1) * 100 + (Integer.parseInt(st.nextToken()) - 1);
			sb.append(bfs()).append("\n");
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 100; j++) {
//				System.out.print(station[i*100+j] + "\t");
//			}
//			System.out.println();
//		}

		System.out.println(sb);
		br.close();
	}

	static int bfs() {
		Arrays.fill(visited, false);
		PriorityQueue<Info> queue = new PriorityQueue<>();
		queue.offer(new Info(U, 0));
//		visited[U] = true;
		while (!queue.isEmpty()) {
			Info cur = queue.poll();

			if (visited[cur.no])
				continue;
			visited[cur.no] = true;
			if (cur.no == V)
				return cur.time;

			if (cur.no % 100 != 0 && !visited[cur.no - 1]) {
				queue.offer(new Info(cur.no - 1, cur.time + 1));
//				visited[cur.no-1] = true;
			}

			if (cur.no % 100 != 49 && station[cur.no + 1] != disconnected && !visited[cur.no + 1]) {
				queue.offer(new Info(cur.no + 1, cur.time + 1));
//				visited[cur.no+1] = true;
			}

			if (station[cur.no] != connected && !visited[station[cur.no]]) {
				queue.offer(new Info(station[cur.no], cur.time + T));
//				visited[station[cur.no]] = true;
			}
		}

		return disconnected;
	}
}
