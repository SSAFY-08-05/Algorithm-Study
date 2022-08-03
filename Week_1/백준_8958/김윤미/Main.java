

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
		for(int i=0; i<n; i++) {
			char[] arr=br.readLine().toCharArray();
			int sum=0;
			int score=0;
			for(char c:arr) {
				sum+=score; //직전 문자의 점수 더해주기
				if(c=='X') { //X일 때 점수 초기화
					score=0;
				}
				else { //O일 때 점수 증가
					score++;
				}
			}
			if(arr[arr.length-1]=='O') sum+=score; //마지막 문자가 O이면 점수 더해주기
			
			bw.write(sum+"\n");
		}
		
		bw.flush();
		bw.close();
	}

}
