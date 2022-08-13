
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * BJ_11399_ATM_김윤미
 */
public class Main {
	
	static int n;
	static int[]arr;
	static int min=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        
        n=Integer.parseInt(br.readLine());
        arr=new int[n];
        
        String []str=br.readLine().split(" ");
        
        for(int i=0; i<n; i++) {
        	arr[i]=Integer.parseInt(str[i]);
        }
        
        Arrays.sort(arr); //오름차순 정렬
        
        int tmp=0;
        int sum=0;
        for(int i=0; i<n; i++) { //계산
        	tmp+=arr[i];
        	sum+=tmp;
        }      
        System.out.println(sum);
	}

}
