
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		int []arr=new int[n+1];
		int []cnt=new int[n+1];
		
		String []str=br.readLine().split(" ");
		for(int i=1; i<=n; i++) {
			arr[i]=Integer.parseInt(str[i-1]);
			cnt[i]=1;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=i; j++) {
				if(arr[j]<arr[i]) { //i앞에 원소 중에 i보다 작은 원소가 있을 때
					cnt[i]=Math.max(cnt[i], cnt[j]+1); //cnt[i]에는 최댓값을 저장
				}
			}
		}
		int max=Integer.MIN_VALUE;
		for(int i=1; i<=n; i++) {
			max=Math.max(max, cnt[i]);
		}
		bw.write(max+"");
		
		bw.flush();
		bw.close();
	}

}
