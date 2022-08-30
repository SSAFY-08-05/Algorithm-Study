package week05;

import java.util.Scanner;

public class BJ_14926_NotEqual {
	static int N;
	static boolean Edge[][];
	static int output[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Edge = new boolean[N+1][N+1];
		output = new int[N*(N-1)/2];
		graph();
		for (int i = 0; i < output.length; i++) {
			System.out.print("a"+output[i]+" ");
		}System.out.println("a1");

	}
	
	static void graph() {
		Edge[1][N] =true;
		Edge[N][1] =true;
		int index = 0;
		int cur = 0;
		for (int i = 0; i < output.length; i++) {
			for (int j = 1; j <= N; j++) {
				if(cur==j||Edge[cur][j])
					continue;
				Edge[cur][j] = true;
				Edge[j][cur] = true;
				cur = j;
				break;
			}
			output[index++] = cur;
		}
	}

}
