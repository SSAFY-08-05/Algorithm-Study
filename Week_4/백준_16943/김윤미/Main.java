
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static char[]a;
	static char[]res;
	static boolean[]isused;
	static int ans=-1;
	static int b;
	
	static void func(int k) {
		if(k==n) {
			if(res[0]=='0') return;
			int num=Integer.parseInt(String.valueOf(res));
			if(num>=b) return;
			
			ans=Math.max(ans, num);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!isused[i]) {
				isused[i]=true;
				res[k]=a[i];
				func(k+1);
				isused[i]=false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		a=st.nextToken().toCharArray();
		b=Integer.parseInt(st.nextToken());
		ans=-1;
		n=a.length;
		
		isused=new boolean[n];
		res=new char[n];
		func(0);
		System.out.println(ans);
	}

}
