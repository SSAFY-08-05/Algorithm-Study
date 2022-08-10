package week02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1966 {

	static LinkedList<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/week02/input_1966"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int a = 0; a < tc; a++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서의 개수
			int M = Integer.parseInt(st.nextToken()); // 몇번째로 인쇄되었는지
			st = new StringTokenizer(br.readLine());

			int count = 0;

			for (int i = 0; i < N; i++) {
				q.offer(new int[] { i, Integer.parseInt(st.nextToken()) }); // 처음 인덱스와 중요도 배열을 큐에 저장
			}

//			for (int i = 0; i < q.size(); i++) {
//				for (int j = 0; j < 2; j++) {
//					System.out.print(q.get(i)[j] + " ");
//				}
//				System.out.println();
//			}
			// 배열출력

			while (!q.isEmpty()) { // q의 값이 존재할 때
				int temp[] = q.poll(); // q의 제일 첫번째 값을 빼서 temp배열에 넣어줌
				// System.out.println(Arrays.toString(temp));
				boolean check = true; // 현재까지 가장 큰 원소인지
				//System.out.print(q.size());//3333222

				for (int i = 0; i < q.size(); i++) { // q사이즈만큼
					System.out.println(temp[1]); //1234441233
					if (temp[1] < q.get(i)[1]) { // temp의 중요도보다 q의 중요도가 크면
						q.offer(temp); // 현재 값을 q에 넣기
						for (int j = 0; j < i; j++) { 
							q.offer(q.poll()); //첫번째 값을 빼서 마지막에 넣기
						}
						check = false; //중요도가 가장 크지 않다고 표시
						break;
					}
				}
				if (check == false) { //체크가 안되어있다면 아직 중요도가 가장 큰 원소가 아님
					continue;
				}
				count++; //check가 true, 즉 중요도가 가장 크면 출력하고 count값을 더해줌
				if (temp[0] == M) { //꺼낸 원소의 index가 찾으려는 번호면 break;
					break;
				}

			}
			//System.out.println(count);
			System.out.println();
		}
	}
}
