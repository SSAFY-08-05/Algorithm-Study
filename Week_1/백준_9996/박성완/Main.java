//package week1.bj_9996;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[] pattern = br.readLine().split("\\*");
		int st_len = pattern[0].length();
		int ed_len = pattern[1].length(); 
		int pat_len = st_len + ed_len;
		
		for (int t = 0; t < N; t++) {
			String target = br.readLine();
			//System.out.println(target.substring(0,st_len) + " " + target.substring(target.length()-ed_len));
			if(target.length() < pat_len-1) System.out.println("NE");
			else if(target.substring(0,st_len).equals(pattern[0]) &&
					target.substring(target.length()-ed_len).equals(pattern[1])) {
				System.out.println("DA");
			}else System.out.println("NE");
		}
	}
}
