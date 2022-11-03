package week14.bj_1052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 11928	시간 92
// bj 1052 물병
public class Main {
	static int N,K, bits, result;
	static String buffer;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 배트 갯수 새기
		bits = Integer.bitCount(N);
		// 비트 갯수가 K 이하면 물병 더 안사도 됨
		if(bits <= K) {
			System.out.println(0);
			System.exit(0);
		}
		// 2진수 스트링으로 만들고
		buffer = Integer.toBinaryString(N);
		
		int count = 0; int idx = 0;
		// K개까지의 1 갯수만큼 세고, 위치를 특정한다
		for (int i = 0, size = buffer.length(); i < size; i++) {
			if(buffer.charAt(i) == '1') count++;
			if(count == K) {
				idx = i;
				break;
			}
		}
		// 그 위치 다음부터  substring
		buffer = buffer.substring(idx+1);
		sb = new StringBuilder();
		// 1의 보수 계산
		for (int i = 0, size = buffer.length(); i < size; i++) {
			if(buffer.charAt(i) == '1') sb.append('0');
			else sb.append('1');
		}
		
		buffer = sb.toString();
		// 결론적으로는 2의 보수를 구하기 위해 1을 더함
		result = buffer.length() != 0 ? Integer.parseUnsignedInt(buffer, 2) : 0;
		System.out.println(result + 1);
		
	}

}
