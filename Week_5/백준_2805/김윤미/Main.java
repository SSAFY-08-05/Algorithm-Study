import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long ans=Long.MIN_VALUE; //높이의 최댓값 (m 이상)
	static long max=Long.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		long m=Long.parseLong(st.nextToken());
		long []arr=new long[n];
		
		ans=Long.MIN_VALUE; //높이의 최댓값 (m 이상)
		long max=Long.MIN_VALUE;
		
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i]=Long.parseLong(st.nextToken());
			max=Math.max(max, arr[i]); //제일 높은 나무 -- 이게 한계
		}
		long start=0;
		
		while(start<=max) {
			long mid=(start+max)/2;
			
			long sum=0;
			for(int i=0; i<n; i++) {
				if(arr[i]<=mid) continue;
				
				sum+=(arr[i]-mid);
			}
			
			if(sum>=m) {
				if(ans<mid) ans=mid;
				start=mid+1;
			}
			else {
				max=mid-1;
			}
		}
		System.out.println(ans);
	}

}
