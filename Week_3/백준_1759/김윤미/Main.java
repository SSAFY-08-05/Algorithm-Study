
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
 * BJ_1759_암호만들기_김윤미
 */
public class Main {

	static int l;
	static int c;
	static char[]arr;
	
	static boolean np(int []numbers) {
		int i=c-1;
		
		while(i>0 && numbers[i-1]>=numbers[i]) {
			--i;
		}
		
		if(i==0) {
			return false;
		}
		
		int j=c-1;
		while(numbers[i-1]>=numbers[j]) {
			--j;
		}
		swap(numbers, i-1, j);
		
		int k=c-1;
		while(i<k) {
			swap(numbers, i++, k--);
		}
		return true;
	}
	
	static void swap(int []numbers, int i, int j) {
		int tmp=numbers[i];
		numbers[i]=numbers[j];
		numbers[j]=tmp;
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		String []str=br.readLine().split(" ");
		l=Integer.parseInt(str[0]);
		c=Integer.parseInt(str[1]);
		
		
		str=br.readLine().split(" ");
		arr=new char[c];
		for(int i=0; i<c; i++) {
			arr[i]=str[i].charAt(0);
		}
		
		int []input=new int[c];
		for(int i=0; i<c-l; i++) {
			input[i]=0;
		}
		for(int i=c-l; i<c; i++) {
			input[i]=1;
		}
		char []ch;
		List<String> list=new ArrayList<>();
		do {
			int aeiou=0;
			int etc=0;
			ch=new char[l];
			int cnt=0;
			for(int i=0; i<c; i++) {
				if(input[i]==1) {
					if(arr[i]=='a' || arr[i]=='e' || arr[i]=='i' || arr[i]=='o' || arr[i]=='u') {
						aeiou++;
					}
					else {
						etc++;
					}
					ch[cnt++]=arr[i];
				}
			}
			if(aeiou<1 || etc<2) continue;
			Arrays.sort(ch);
			String string=String.valueOf(ch);
			list.add(string);
		}while(np(input));
		Collections.sort(list);
		for(String s:list) sb.append(s).append("\n");
		System.out.println(sb.toString());
	}

}
