import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N,c,result;
	static int min;
	static int max;
	static int[] num;
	static int[] arr;
	static boolean[] visited;
	static int[] arr2;
	static ArrayList<String> cal;
	static StringTokenizer st;
  
  //주석처리 된 코드는 지금 코드 전에 시도했던 코드로 입력된 +,-,*,/의 개수 맞게 list에 저장해서(EX. ++-/) 순열을 진행한 다음 Cal()계산을 진행 = 시간초과 나옴

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[4];
			num = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				arr[i] =  Integer.parseInt(st.nextToken());
			}
								
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}			
			
//			cal = new ArrayList<String>();
//			
//			for(int i = 0; i < 4; i++) { //연산 입력
//				switch(i) {
//				case 0:
//					c = arr[i];
//
//					for(int j = 0; j < c; j++) {
//						cal.add("+");
//					}
//					break;
//				case 1:
//					c = arr[i];
//					for(int j = 0; j < c; j++) {
//						cal.add("-");
//					}
//					break;
//				case 2:
//					c = arr[i];
//					for(int j = 0; j < c; j++) {
//						cal.add("*");
//					}
//					break;
//				case 3:
//					c = arr[i];
//					for(int j = 0; j < c; j++) {
//						cal.add("/");
//					}
//					break;
//				}
//			}
			arr2 = new int[N-1];
			visited = new boolean[N-1];
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			perm(0);
						
			System.out.print("#"+tc + " ");
			System.out.println(max-min);		
		}

	}

	private static void perm(int n) {
		if(n == N-1) {
			Cal();			
		}
		
		for(int i = 0; i < 4; i++) { //arr[i]의 i가 0 = +, 1 = -, 2 = *, 3 = / 로 생각해서 각 index의 수에 맞춰 arr2[]에 나열해서 저장
			if(arr[i] == 0)continue;			
			arr[i] -= 1;
			arr2[n] = i;
			perm(n+1);
			arr[i] += 1;
			
			
//			if(!visited[i]) {
//				visited[i] = true;
//				arr2[n] = cal.get(i);
//				perm(n+1);
//				visited[i] = false;
//			}
			
		}
		
	}

	private static void Cal() { //나열된 숫자에 따라 계산
		result = num[0];
		for(int i = 0 ; i < N-1; i++) {
			switch(arr2[i]) {
			case 0:
				result += num[i+1];
				break;
			case 1:
				result -= num[i+1];
				break;
			case 2:
				result *= num[i+1];
				break;
			case 3:
				result /= num[i+1];
				break;			
			}
		}
//		System.out.println(result);
		min = Math.min(min, result);
		max = Math.max(max, result);		
	}
}
