
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * BJ_2529_부등호_김윤미
 */
public class Main {
	static int k;
	static int SIZE=10;
	static int[]res; //조합된 수들 저장
	static int[]use; //순서 정해진 수들 저장
	static int []nums; //0~9까지 수 있는 배열
	static boolean[]isused; //백트래킹 시 res배열 수 사용 여부 체크
	static char[]op; //부등호 저장 배열
	static long max=Long.MIN_VALUE; //실제 최댓값 비교
	static long min=Long.MAX_VALUE; //실제 최솟값 비교
	static String maxStr; //출력문자열
	static String minStr; //출력문자열
	
	static boolean np(int []numbers) {
		int i=SIZE-1;
		
		while(i>0 && numbers[i-1]>=numbers[i]) --i;
		
		if(i==0) return false;
		
		int j=SIZE-1;
		while(numbers[i-1]>=numbers[j])	--j;
		
		swap(numbers, i-1, j);
		
		int k=SIZE-1;
		while(i<k) swap(numbers, i++, k--);
		
		return true;
	}
	
	static void swap(int []numbers, int i, int j) {
		int tmp=numbers[i];
		numbers[i]=numbers[j];
		numbers[j]=tmp;
	}

	static void func(int m) {
		if(m==k+1) { //(k+1)개 다 정했을 때
			int idx=0; //부등호 배열 탐색 인덱스
			String str=String.valueOf(use[0]);
			for(int i=0; i<k; i++) {
				int num1=use[i]; //부등호 앞 숫자
				int num2=use[i+1]; //부등호 뒤 숫자
				if(op[idx]=='<') { //< 라면 작아야 함
					idx++; //다음 부등호로
					if(num1<num2) {
						str+=String.valueOf(num2);
					}
					else return; //아니면 바로 리턴
				}
				else if(op[idx]=='>') { // > 라면 커야 함
					idx++; //다음 부등호로
					if(num1>num2) { //
						str+=String.valueOf(num2);
					}
					else return; //아니면 리턴
				}
			}
			if(max<Long.parseLong(str)) { //실제 계산은 수로
				max=Long.parseLong(str);
				maxStr=str; //실제 출력값은 문자열로 저장
			}
			if(min>Long.parseLong(str)) {
				min=Long.parseLong(str);
				minStr=str;
			}
		}
		
		for(int i=0; i<k+1; i++) { //백트래킹
			if(!isused[i]) {
				isused[i]=true;
				use[m]=res[i];
				func(m+1);
				isused[i]=false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		nums=new int[SIZE];
		for(int i=0; i<SIZE; i++) nums[i]=i;
		int []input=new int[SIZE];
		
		k=Integer.parseInt(br.readLine());
		op=new char[k];
		
		String []str=br.readLine().split(" ");
		for(int i=0; i<k; i++) {
			op[i]=str[i].charAt(0);
		}
		
		res=new int[k+1];
		use=new int[k+1];
		
		for(int i=SIZE-(k+1); i<SIZE; i++) input[i]=1;
		
		do { //1. k+1개의 수 뽑기(조합)
			int cnt=0;
			isused=new boolean[k+1];
			for(int i=0; i<SIZE; i++) {
				if(input[i]==1) {
					res[cnt++]=nums[i];
				}
			}
			func(0); //2. 순서 정하기 백트래킹
		}while(np(input));
		
		System.out.println(maxStr);
		System.out.println(minStr);
	}

}
