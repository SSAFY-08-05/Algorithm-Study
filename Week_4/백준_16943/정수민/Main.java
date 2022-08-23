package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16943_숫자재배치 {

	static int a[], rearr[];
	static int A, b, max, ans;
	static String str;
	static boolean isSelected[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		str = st.nextToken();
		isSelected = new boolean[str.length()];
		a = new int[str.length()]; //순열 뽑기 위해서 배열로 받음
		b = Integer.parseInt(st.nextToken());

		for (int i = 0; i < str.length(); i++) {
			a[i] = str.charAt(i) - '0';
		}

		ans = -1; // 처음을 불가로 설정
		rearr = new int[str.length()]; //정렬한 숫자 담을 배열

		perm(0);
		System.out.println(ans);
	}

	static void perm(int count) { //순열 함수
		if (count == str.length() && rearr[0] != 0) { // 재조합한 수의 첫번째자리는 0이면 안됨
			A = 0; //시작 전 초기화
			findmax();
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			if (isSelected[i])
				continue;
			rearr[count] = a[i];
			isSelected[i] = true;
			perm(count + 1);
			isSelected[i] = false;

		}
	}

	static void findmax() { //정렬된 숫자중에서 b보다 작으면서 가장 큰 숫자 뽑는 함수
		int multi = 1; //곱해주는 수
		for (int i = rearr.length - 1; i >= 0; i--) { //현재 배열로 돼있으므로 뒤부터 곱해서 int형으로 바꿔줌
			A = A + rearr[i] * multi;
			multi *= 10;
		}

		if (A < b) {//B보다 작다면
			ans = Math.max(ans, A);//현재까지 나온 것 중에 가장 큰 수로 바꿔줌
		} else {
			return;
		}
	}

}
