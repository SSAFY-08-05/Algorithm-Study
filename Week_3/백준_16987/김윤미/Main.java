
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * BJ_16987_계란으로계란치기_김윤미
 */
class Pair { //내구도와 무게 저장
	int s;
	int w;
	
	Pair(int s, int w) {
		this.s=s;
		this.w=w;
	}
}

public class Main {
	static int n;
	static Pair[] arr;
	static int max=Integer.MIN_VALUE;
	
	static void func(int k) { //k번째 계란 보는 중
		if(k==n) { //끝까지 다 봤을 때
			int sum=0; //친 계란 개수
			for(int i=0; i<n; i++) {
				if(arr[i].s<=0) sum++; //내구도가 0 이하인 개수 증가
			}
			max=Math.max(max, sum); //최댓값
			return;
		}
		
		if(arr[k].s<=0) func(k+1); //k번쨰 계란이 이미 깨져있으면 그 다음 계란 들기
		
		else {
			boolean flag=false; //칠 계란 있는지 유무 판별
			for(int i=0; i<n; i++) {
				if(arr[i].s>0 && i!=k) { //들고있는 계란이 아니고 계란이 깨져있지 않을 때
					arr[i].s-=arr[k].w; //쳐
					arr[k].s-=arr[i].w;
					flag=true; //침
					func(k+1);
					arr[i].s+=arr[k].w; //복구
					arr[k].s+=arr[i].w;
				}
			}
			if(!flag) func(k+1); //친 계란 없으면 다음 계란 집음
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		n=Integer.parseInt(br.readLine());
		arr=new Pair[n];
		for(int i=0; i<n; i++) {
			String []str=br.readLine().split(" ");
			int s=Integer.parseInt(str[0]);
			int w=Integer.parseInt(str[1]);
			
			arr[i]=new Pair(s, w);
		}
		
		func(0);
		
		System.out.println(max);
	}

}
