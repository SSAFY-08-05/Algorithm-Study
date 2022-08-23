import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int A, B, result;
	static char[] num;
	static char[] num2;
	static int max;
	static boolean[] isSelected;
	static void perm(int cnt) {
		if(cnt == num.length) {
			if(num2[0] != '0') {
				String str = new String(num2);
				result = Integer.parseInt(str);
				if(result < B && result >= max) {
					max = result;
				}
			}
			else {
				result = -1;
			}

		}
		else {
			for(int i = 0 ; i < num.length; i++) {
				if(isSelected[i])continue;
				if(cnt == 0 && num[i] == 0) continue;
				num2[cnt] = num[i];
				isSelected[i] = true;
				perm(cnt+1);
				isSelected[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(bf.readLine());
        
    	A = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	
    	String number = String.valueOf(A);
    	num = number.toCharArray();
    	num2 = new char[num.length];
    	max = -1;
    	
    	isSelected = new boolean[num.length];
    	
    	perm(0);
    	System.out.println(max);
    	
	}

}
