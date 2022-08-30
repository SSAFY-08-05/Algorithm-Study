package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2805_나무자르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 나무의 수
		int M = Integer.parseInt(st.nextToken()); // 최소 M
		int tree[] = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(tree);

		long sum = 0;
		int max = tree[N - 1];
		int min = tree[0];

		while (true) {
			if (max <= min)
				break;
			sum = 0;
			int mid = (min + max) / 2; // 중앙값
			for (int i = 0; i < N; i++) {
				if (tree[i] - mid > 0)
					sum += (tree[i] - mid);
			}
			if (sum < M) {
				max = mid;// 자르는 위치 낮추기
			} else
				min = mid + 1; // 자르는 위치 높이기

		}

		System.out.println(min - 1);// 마지막 중앙값 출력

	}

}
