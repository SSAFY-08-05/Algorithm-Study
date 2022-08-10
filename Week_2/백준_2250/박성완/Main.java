//package week2.bj_2250;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2250 트리의 높이와 너비
public class Main {
	// 전역변수 선언
	static int N,cnt, root,maxLevel, maxWidth, thatOfLevel;
	static Node[] tree;
	static int[][] width;
	// 노드객체 선언
	static class Node{
		// 기본 정보
		public int data;
		public int left;
		public int right;
		// 레벨, 위치, 부모노드 번호
		public int level;
		public int index;
		public int parentNum;
		public Node(int data, int left, int right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		// 트리 선언 및 개별 객체 입력
		cnt=0;
		tree = new Node[N+1];
		tree[0] = new Node(0,1,-1); tree[0].level = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			tree[i] = new Node(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		// 번호순으로 정렬
		Arrays.sort(tree,(n1,n2)-> n1.data - n2.data);
		// 하나씩 탐색하면서 부모노드 입력.
		for (int i = 1; i <= N; i++) {
			if(tree[i].left != -1) tree[tree[i].left].parentNum = tree[i].data;
			if(tree[i].right != -1) tree[tree[i].right].parentNum = tree[i].data;
		}
		// 부모가 0인 노드가 루트노드다.
		for (int i = 1; i <= N; i++) {
			if(tree[i].parentNum == 0) {
				root = i;
				break;
			}
		}
		// 루트노드를 특정하고 레벨을 1로 선언. 나머지 전역변수 초기화
		tree[root].level = 1;
		maxWidth = 1; thatOfLevel = 1; maxLevel = 1;
		// 루트노트를 시작으로 마킹 진행
		marking(root);

		// 행 : 1부터 레벨
		// 열 : 최소, 최대, 너비
		width = new int[maxLevel+1][3];
		// 너비 계산 알고리즘 실행
		widCalc();
		// 결과 출력
		System.out.println(thatOfLevel + " " + maxWidth);
	}
	// 트리 마킹 알고리즘
	static void marking(int i) {
		// 왼쪽 탐색
		if(tree[i].left != -1) {
			// 하위노드의 레벨을 마킹하고, 동시에 최대레벨을 최신화한다.
			tree[tree[i].left].level = tree[i].level+1;
			maxLevel = maxLevel < tree[i].level+1 ? tree[i].level+1 : maxLevel;
			marking(tree[i].left);
		}
		// 본인 탐색
		tree[i].index = ++cnt;
		
		// 오른쪽 탐색
		if(tree[i].right != -1) {
			// 하위노드의 레벨을 마킹하고, 동시에 최대레벨을 최신화한다.
			tree[tree[i].right].level = tree[i].level+1;
			maxLevel = maxLevel < tree[i].level+1 ? tree[i].level+1 : maxLevel;
			marking(tree[i].right);
		}
	}
	// 너비계산
	static void widCalc() {
		// 딱히 거꾸로 탐색하는 이유는 없다. 
		for (int i = N; i > 0 ; i--) {
			int target = tree[i].index;
			int lev = tree[i].level;
			// 최소가 비었다면 입력, 아니라면 최신화.
			if(width[lev][0] == 0) width[lev][0] = target;
			else if(width[lev][0] > target) width[lev][0] = target;
			// 최대가 비었다면 입력, 아니라면 최신화.
			if(width[lev][1] == 0) width[lev][1] = target;
			else if(width[lev][1] < target) width[lev][1] = target;
				
			// 현재 너비가 2초과면 최대너비를 최신화하고, 같다면 레벨을 비교해 낮은 레벨로 최신화
			int tmp = width[lev][1] - width[lev][0] + 1;
			if(tmp > 2 && tmp > width[lev][2]) {
				width[lev][2] = tmp;
				if(maxWidth < width[lev][2]) {
					maxWidth = width[lev][2];
					thatOfLevel = lev;
				} else if(maxWidth == width[lev][2]) {
					thatOfLevel = thatOfLevel > lev ? lev : thatOfLevel;
				}
			}
		}
	}
}
