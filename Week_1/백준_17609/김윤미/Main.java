
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static boolean func(int start, int end, String str) {
		boolean flag=true;
		for(int d=0; d<=(end-start)/2; d++) { //start와 end 사이만 동일한 방식으로 검사
			if(str.charAt(start+d)==str.charAt(end-d)) continue; //같으면 계속 검사
			
			flag=false; //아니면 false
			break;
		}
		return flag;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		Main m=new Main();
		
		int n=Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			String str=br.readLine();
			int start=0; 
			int end=str.length()-1; //끝에서 가운데로 
			
			int res=0;
			
			for(int d=0; d<str.length()/2; d++) { //중간까지 이동
				if(str.charAt(start+d)==str.charAt(end-d)) continue; //같으면 계속 검사
				
				//start만 오른쪽으로 한 칸 이동 또는 end만 왼쪽으로 한 칸 이동하고 다시 검사 후 둘 중 하나만 true여도 유사회문
				if(func(start+d+1, end-d, str) || func(start+d, end-d-1, str)) {
					res=1;
				}
				else { //모두 아닐 때
					res=2;
				}
				break;
			}
			bw.write(res+"\n");
		}
		
		bw.flush();
		bw.close();
	}

}
