import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int size;
	static int count;
	static char[] arr;
	static char[] choice;	
	static HashSet<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
	static boolean[] visit;
	public static boolean check() {
		int result = 0;
		int result2 = 0;
		for(int i = 0; i < choice.length; i++) {
			if(set.contains(choice[i])) {
				result += 1;
			}
			else {
				result2 += 1;
			}
		}		
		if(result>=1 && result2 >=2) {
			return true;
		}
		return false;		
	}	
	public static void DFS(int a, int b) {
		if(a == size) {
			check();
			if(check()==true) {
				for(int i = 0; i < choice.length; i++) {
					System.out.print(choice[i]);
				}
				System.out.println();
			}
		}else {
			for(int i = b; i < count; i++) {
					choice[a] = arr[i];
					DFS(a+1, i+1);				
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		size = Integer.parseInt(st.nextToken());
		count = Integer.parseInt(st.nextToken());
		
		arr = new char[count];
		choice = new char[size];
		visit = new boolean[count];
		
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < count; i++) {
			arr[i] = st.nextToken().charAt(0);
		}		
		Arrays.sort(arr);
		DFS(0,0);
	}

}
