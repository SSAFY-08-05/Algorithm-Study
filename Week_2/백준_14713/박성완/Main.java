//package week2.bj_14713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 전역변수 선언
	static int N;
	static List<LinkedList<String>> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 기초 정보 입력
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<LinkedList<String>>();
		boolean ans = true;
		
		// 앵무새의 말을 단어 단위로 끊어  Queue 에넣는다.
		// 한 앵무새의 말이 끝나면 그 queue를 list에 넣는다.
		for (int i = 0; i < N; i++) {
			LinkedList<String> temp = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) temp.offer(st.nextToken());
			list.add(temp);
		}
		
		// 받아적은 말을 저장
		st = new StringTokenizer(br.readLine());
		
		// 리스트안의  queue의 최상단을 탐색하면서 해당 단어를 뺀다.
		// 각 단어별로 빠지지 안았다면 impossible 이다.
		while(st.hasMoreTokens()) {
			String target = st.nextToken();
			// 위치 중간 변수
			int idx = -1;
			for (int i = list.size()-1; i >= 0; i--) {
				if(!list.get(i).isEmpty() && list.get(i).peek().equals(target)) {
					list.get(i).poll();
					idx = i;
				}
			}
			// 위치 중간변수가 변하지 않았다면 impossible
			if(idx==-1) {
				ans = false;
				break;
			}
		}
		// 남은 단어가 있다면 impossible
		for (int i = 0; i < list.size(); i++) {
			if(!list.get(i).isEmpty()) {
				ans = false;
				break;
			}
		}
		
		if(ans) System.out.println("Possible");
		else System.out.println("Impossible");
	}
}
