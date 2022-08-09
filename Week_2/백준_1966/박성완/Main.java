//package week2.bj_1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1966 프린터 큐
public class Main {
	// 전역변수 선언
	static int TC, N,M;
	static List<Integer> nlist;
	static Queue<Integer> queue,numbers;
	static int[] order;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			// N,M 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
			// 데이터 입력
			st = new StringTokenizer(br.readLine());
			int[] rawdata = new int[N];
			// 출력된 순서 저장 배열
			order = new int[N];
			// max값 구하기 위한 리스트
			nlist = new ArrayList<Integer>();
			// 중요도가 들어있는 큐
			queue = new LinkedList<Integer>();
			// 순서가 들어있는 큐
			numbers = new LinkedList<Integer>();
			// 데이터 입력 및 각 배열,큐 초기화
			for (int i = 0; i < N; i++) {
				rawdata[i] = Integer.parseInt(st.nextToken());
				nlist.add(rawdata[i]);
				queue.add(rawdata[i]);
				numbers.add(i);
			}
			// 출력 순서 마킹 값
			int idx = 1;
			while(!queue.isEmpty()) {
				// 현재 맨 앞 값이 가장 높다면
				if(queue.peek() >= Collections.max(nlist)) {
					// 리스트의 최고값과 큐 현재 앞 값 제거
					nlist.remove(new Integer(queue.poll()));
					// 현재 순서에 번호 마킹
					order[numbers.poll()] = idx++;
				}else {
					// 데이터 큐와 순서 큐 맨 뒤로
					queue.add(queue.poll());
					numbers.add(numbers.poll());
				}
			}
			
			sb.append((order[M])+"\n");
		}
		System.out.println(sb);
		br.close();
	}

}
