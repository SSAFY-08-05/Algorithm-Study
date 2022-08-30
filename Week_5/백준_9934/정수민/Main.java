package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_9934_완전이진트리 {
	static int K;
	static int input[];
	static ArrayList<Integer> list[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		input = new int[(int) (Math.pow(2, K) - 1)];
		list = new ArrayList[K];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		tree(0, input.length - 1, 0);

		for (int i = 0; i < K; i++) {
			for (int j = 0; j < list[i].size(); j++) {
				System.out.print(list[i].get(j) + " ");
			}
			System.out.println();
		}

	}

	static void tree(int start, int end, int depth) {
		if (depth == K) {
			return;
		}
		int parent = (start + end) / 2;
		list[depth].add(input[parent]);

		tree(start, parent - 1, depth + 1);
		tree(parent + 1, end, depth + 1);

	}

}
