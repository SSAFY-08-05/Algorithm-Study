

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s1="";
		String s2="";
		int n=0;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), "*");
		
		s1=st.nextToken(); //앞 패턴
		s2=st.nextToken(); //뒤 패턴
		
		for(int i=0; i<n; i++) {
			String str=br.readLine();
			int index1=str.indexOf(s1); //s1이 처음 나오는 곳
			int index2=str.lastIndexOf(s2); //s2가 마지막으로 나오는 곳
			int diff=str.length()-s2.length(); //s2가 2글자 이상일 때 index 체크
			
			//s1이 처음 나오는 곳은 0이여야 함, 겹치는 곳이 없어야 함, s2의 index 체크
			if(index1==0 && s1.length()-1<index2 && index2==diff) bw.write("DA\n");
			else bw.write("NE\n");	
		}
		
		bw.flush();
		bw.close();
	}

}
