package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11399 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int time[] = new int[N];
		
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time); //오름차순으로 정리 : 가장 적은 시간 걸리려면 작은 수부터 더해야함
		
		int fin_sum = 0; //최종 시간
		int sum = 0; //이전 누적 시간
		
		for (int i = 0; i < time.length; i++) {
			sum += time[i]; //누적 시간 계산을 위해 차례로 더한 값을 변수에 저장
			fin_sum += sum; //변수에 저장된 누적 시간을 최종 시간에 저장
		}
		
		System.out.println(fin_sum);
		

	}

}
