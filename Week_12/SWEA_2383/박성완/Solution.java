//package test.sw_2383;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 2383 점심 식사시간
public class Solution {
	// 전역변수 선언
	static int TC,N,M,tostair[][],picked[],leastTime,moved[];
	static List<Point> people,stair;
	static List<Integer> stime;
	static int stairstate[][];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			people = new ArrayList<>(); // 사람
			stair = new ArrayList<>(); // 계단
			stime = new ArrayList<>(); // 계단 시간
			
			leastTime = Integer.MAX_VALUE;
			// 사람위치, 계단 위치, 계산 시간을 ArrayList 로 동적으로 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					switch(n) {
					case 0:
						break;
					case 1:
						people.add(new Point(i,j));
						break;
					default:
						stair.add(new Point(i,j));
						stime.add((-1)*n);
						break;
					}
				}
			}
			// 각 사람별 계단까지의 시간 배열 만듦
			M = people.size();
			tostair = new int[M][2];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < 2; j++) {
					tostair[i][j] = dist(people.get(i),stair.get(j));
				}
			}
			// dfs를 위한 초기화
			picked = new int[M];
			moved = new int[M];
			dfs(0);
			
			sb.append("#").append(t).append(" ").append(leastTime).append("\n");
		}
		System.out.println(sb);
		br.close();
		
	}
	// 포인트 간 거리 계산 메소드
	static int dist(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	// dfs
	static void dfs(int cnt) {
		if(cnt == M) {
			// 다 고르면  simulate
			int least = simulate();
			if(least != -1) leastTime = least;
			return;
		}
		for (int i = 0; i < 2; i++) {
			picked[cnt] = i;
			dfs(cnt+1);
		}
	}
	static int simulate() {
		int time = 0;
		int arrived = 0;
		stairstate = new int[2][M+1];
		int[] cnt = new int[2]; // 각 계단 별 간 사람
		int[] idx = new int[2]; // 각 계단 별 현재까지 도착한사람
		// 계단 상태 초기화
		for (int i = 0; i < M; i++) {
			int p = picked[i];
			stairstate[p][cnt[p]++] = tostair[i][picked[i]];
		}
		// 사람이 가지 않은 부분은 이상한값으로 세팅하고 정렬
		for (int i = 0; i < 2; i++) {
			if(cnt[i] != M+1) Arrays.fill(stairstate[i], cnt[i], M+1, Integer.MAX_VALUE);
			Arrays.sort(stairstate[i]);

		}
		while(time < leastTime) {
//			System.out.println(time + " " + idx[0] + " " + idx[1] + " " + arrived);
//			for(int[] arr : stairstate) {
//				for(int a : arr) System.out.print(a + " ");
//				System.out.println();
//			}
//			System.out.println();
			if (arrived == M) return time+1;
			
			// 각 위치별로 사람의 시간이 -1되는데, 도착한 사람중 4번째부터 0이면 안내림
			for (int i = 0; i < 2; i++) {
				for (int j = idx[i]; j < M; j++) {
					if(j < idx[i] + 3) {
						stairstate[i][j]--;
					}else if(stairstate[i][j] > 0){
						stairstate[i][j]--;
					}
				}
			}
			// 도착햇는지 판단하여 idx 이동
			for (int i = 0; i < 2; i++) {
				if(idx[i] == M) continue;
				while(stairstate[i][idx[i]] == stime.get(i)) {
					idx[i]++;
					arrived++;
				}
			}
			
			time++;
		}
		return -1; // 시간 넘으면 -1반환
	}
}
