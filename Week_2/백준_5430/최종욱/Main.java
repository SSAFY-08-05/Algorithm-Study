
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class AC {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			char[] p = br.readLine().toCharArray();
			
			int n = Integer.parseInt(br.readLine());
			
			//char[] str = br.readLine().toCharArray();
			String str=br.readLine();
			 
			str=str.substring(1, str.length()-1);
			
			//char[] cha= str.split(",").toCharArray();
			 
			String []input=str.split(",");
			
			ArrayList<Character> num = new ArrayList<>();
			
			int poss = 0;
			int index = 0;
			for(int i = 0; i < cha.length; i++) {
				if(cha[i] != ',') {
					num.add(cha[i]);
				}
			}
			
			for(int i = 0; i < p.length; i++) {
				if(num.size() == 0) {
					System.out.println("error");
					poss = 1;
					break;
				}
				switch(p[i]) {
				case 'R':
					Collections.reverse(num);
					break;
				case 'D':
					num.remove(0);
					System.out.println("size"+num.size());
					break;
				}
			}
			
			
			if(poss != 1) {
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				for(int i = 0; i < num.size(); i++) {
					if(i == num.size()-1) {
						sb.append(num.get(i));
					
					}else {
						sb.append(num.get(i));
						sb.append(",");
					}
				}
				sb.append("]");
			
			
				System.out.println(sb.toString());
				}
			
			
			
			
		}
		
		

	}

}
