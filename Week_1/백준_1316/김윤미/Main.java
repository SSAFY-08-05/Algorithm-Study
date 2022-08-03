

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		int cnt=0; //그룹 단어 개수
		for(int n=0; n<N; n++) {
			boolean flag=true;
			boolean[]isCheck=new boolean[26]; //알파벳 개수만큼의 배열 생성.
			//isCheck[0]은 'a'가 문자열 내 나온 적이 있는지를 의미
			char []arr=br.readLine().toCharArray();
			for(int i=0; i<arr.length; i++) {	
				int index=arr[i]-97; //'a'의 아스키코드값인 97을 뺀 값이 isCheck의 index가 됨
				
				//문자가 나온적이 있는데, 직전 문자와 다르면 그룹 단어가 아니다.
				if(i!=0 && isCheck[index] && arr[i-1]!=arr[i]) {
					flag=false;
					break;
				}
				
				if(!isCheck[index]) isCheck[index]=true; //나온 적이 없는 문자라면 check
			}
			if(flag) cnt++; //그룹 단어 개수 증가
		}
		
		bw.write(cnt+"");
		
		bw.flush();
		bw.close();
	}

}
