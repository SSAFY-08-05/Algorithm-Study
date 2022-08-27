import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, max;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]); //절단기 범위의 최고 위치 설정 = 가장 긴 나무
		}
		
		//탐색할 범위 = 나무 높이
		int start = 0;
		int end = max;
		
		
		//시작 위치가 끝 위치보다 크면 찾으려는 높이 도착
		while (start <= end) {
            int mid = (start + end) / 2;
            long sum = 0;

            for (int tree : arr) {
                if (tree > mid) {
                    sum += tree - mid;
                }
            }

            if (sum >= M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
		System.out.println(end);

	

	}
}
