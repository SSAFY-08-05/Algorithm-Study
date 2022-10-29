package week14.bj_14226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
// 메모리 16864	시간 128
// 백준 14226 이모티콘
public class Main {
	static int S, b, c, t, cnt;
	static boolean visited[][];
	static class Status implements Comparable<Status> {
		int board, clip, time;
		boolean copied;

		public Status(int board, int clip, int time, boolean copied) {
			super();
			this.board = board;
			this.clip = clip;
			this.time = time;
			this.copied = copied;
		}

		@Override
		public String toString() {
			return "Status [board=" + board + ", clip=" + clip + ", time=" + time + ", copied=" + copied + "]";
		}

		@Override
		public int compareTo(Status o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		visited = new boolean[S*2+1][S*2+1];
		System.out.println(bfs());
	}

	static int bfs() {
		PriorityQueue<Status> queue = new PriorityQueue<>();
		queue.offer(new Status(1, 0, 0, false));
		visited[1][0] = true;
		while (!queue.isEmpty()) {
			Status cur = queue.poll();
			//System.out.println(++cnt + " " + cur.toString());
			if (cur.board == S)
				return cur.time;

			b = cur.board;
			c = cur.clip;
			t = cur.time;

			if (b < S && !cur.copied && !visited[b][b]) {
				queue.offer(new Status(b, b, t + 1, true));
				visited[b][b] = true;
			}
			if (c > 0 && b + c < S * 2 && !visited[b+c][c]) {
				queue.offer(new Status(b + c, c, t + 1, false));
				visited[b+c][c] = true;
			}
			if (b > 1 && !visited[b-1][c]) {
				queue.offer(new Status(b - 1, c, t + 1, false));
				visited[b-1][c] = true;
			}

		}
		return -1;
	}

}
