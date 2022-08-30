import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int depth;
	int num;
	
	Node(int depth, int num) {
		this.depth=depth;
		this.num=num;
	}

	@Override
	public int compareTo(Node o) {
		return this.depth-o.depth;
	}
	
	
}

public class Main {
	
	static int k;
	static int []arr; //받은 번호들 저장
	static ArrayList<Node> list;
	
	static void func(int start, int end, int depth) { //출력 인덱스
		int mid=(start+end)/2;
		list.add(new Node(depth, arr[mid]));
		if(Math.abs(start-end)<=1) return;
		//sb.append("\n");
		func(start, mid, depth+1);
		func(mid+1, end, depth+1);
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		k=Integer.parseInt(br.readLine());
		
		int sz=(int)Math.pow(2, k);
		arr=new int[sz+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=1; i<sz; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		list=new ArrayList<>();
		func(1, sz-1, 1);
		
		Collections.sort(list);
		StringBuilder sb=new StringBuilder();
		int depth=1;
		int cnt=0;
		
		for(Node node:list) {
			cnt++;
			sb.append(node.num).append(" ");
			
			if(cnt==Math.pow(2, depth-1)) {
				depth++;
				cnt=0;
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
