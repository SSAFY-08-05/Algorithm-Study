import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_2606 {
	static int[][] arr;
	static int count;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int comp = sc.nextInt();
		int pair = sc.nextInt();

		arr = new int[comp + 1][comp + 1];
		visited = new boolean[comp + 1];

		for (int i = 0; i < pair; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			arr[a][b] = arr[b][a] = 1;
		}

		bfs(1);
		System.out.println(count);

	}

	static void bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		visited[start] = true;

		while (!que.isEmpty()) {
			int temp = que.poll();
			for (int i = 1; i < arr.length; i++) {
				if (arr[temp][i] == 1 && !visited[i]) {
					que.add(i);
					count += 1;
					visited[i] = true;
				}

			}
		}

	}
}
