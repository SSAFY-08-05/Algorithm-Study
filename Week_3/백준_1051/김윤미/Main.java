
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BJ_1051_숫자정사각형_김윤미
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String []str=br.readLine().split(" ");
        int n=Integer.parseInt(str[0]);
        int m=Integer.parseInt(str[1]);
        
        int [][]arr=new int[n][m];
        
        for(int i=0; i<n; i++) {
        	str=br.readLine().split("");
        	for(int j=0; j<m; j++) {
        		arr[i][j]=Integer.parseInt(str[j]);
        	}
        }
        int max=Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
        	for(int j=0; j<m; j++) {
        		int num=arr[i][j];
        		int dis=0;
        		for(int k=j+1; k<m; k++) { //같은 행에서 같은 숫자 찾기
        			if(arr[i][k]==num) { //같은 숫자라면
        				dis=k-j; //거리 구하기
        			}
        			else continue; //아니면 넘어감
        			
        			if((i+dis)>=n || (j+dis)>=m) continue; //범위 체크
        			
        			if(arr[i+dis][j]!=num || arr[i][j+dis]!=num || arr[i+dis][j+dis]!=num) continue; //같은 거리에 떨어진 세 점이 다르면 넘어감
        			
        			max=Math.max(max, (dis+1)*(dis+1)); //거리 최댓값 구하기
        		}
        	}
        }
        if(max==Integer.MIN_VALUE) max=1; //다른 값으로 세팅되지 않았으면 1
        System.out.println(max);
    }

}
