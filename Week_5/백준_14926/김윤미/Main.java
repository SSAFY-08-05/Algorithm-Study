import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static int max(int n) { //최대 != 개수 반환
		return (n*(n-1))/2;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		ArrayList<boolean[]> list=new ArrayList<>();
		ArrayList<Integer> ans=new ArrayList<>();
		
		for(int i=1; i<=n; i++) {
			boolean []tmp=new boolean[n+1];
			list.add(tmp);
		}
		
		ans.add(1);
		int index=0;
		while(true) {
			int i=ans.get(index); //마지막 원소
			boolean []arr=list.get(i-1);
			boolean flag=false;
			for(int j=1; j<=n; j++) {
				if(arr[j]) continue; //i의 인접리스트 중 이미 방문했을 때
				if(j==i) continue; //값 같은 원소는 지나감
				if(i==n && j==1 && ans.size()!=max(n)) continue; //아직 끝 원소 추가할 때 아님
					flag=true;
					ans.add(j);
					arr[j]=true;
					list.get(j-1)[i]=true;
					break;
			}
			if(!flag) break;
			index++;
		}
		
		for(int num:ans) {
			sb.append("a").append(num).append(" ");
		}
		System.out.println(sb.toString());
	}

}
