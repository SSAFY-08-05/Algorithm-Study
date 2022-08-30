import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_9934 {
	static ArrayList<Integer>[] list;
	static int[] node;
	static int size;
	static void tree(int start, int end, int depth) {
		if(start > end) {
			return;
		}
		else {
			int mid = (start+end)/2;//부모 위치 = 전체 범위의 가운데 값
			
			list[depth].add(node[mid]);
			
			tree(start,mid-1,depth+1);//왼쪽 자식
			tree(mid+1,end,depth+1);//오른쪽 자식
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(bf.readLine());
		size = (int)Math.pow(2, K)-1; //트리 노드 개수
		node = new int[size];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < size; i++) {//노드 값 입력
			node[i] = Integer.parseInt(st.nextToken());
		}
				
		list = new ArrayList[K];						
		for(int i = 0; i < K; i++) { // 트리 순서 대로 노드 저장할 리스트
				list[i] = new ArrayList<Integer>();
			}
		
		tree(0, size-1, 0);//트리 탐색
		
		for(int i = 0; i < K; i++) {
			for(int n : list[i]) {
				System.out.print(n+" ");
			}
			System.out.println();
		}
		
	}

}
