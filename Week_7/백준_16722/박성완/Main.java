//package week7.bj_16722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 메모리 11628	시간 84
// 백준 16722 결! 합 !
public class Main {
	// 전역변수 생성
	static int ROUND,amount,score,find;
	static boolean gyul;
	static Card[] cards;
	static int idxList[];
	static short board[][][];
	static final int SIZE = 9;
	// 카드 클래스
	static class Card{
		short shape,color,backc;
		// 생성자
		// 각 요소는 -1/0/1로 변환된다.
		public Card(String shape, String color, String backc) {
			super();
			switch(shape) {
			case "CIRCLE" :
				this.shape = -1;
				break;
			case "TRIANGLE" :
				this.shape = 0;
				break;
			case "SQUARE" :
				this.shape = 1;
				break;
			default :
				System.out.println("error");
				break;
			}
			
			switch(color) {
			case "YELLOW" :
				this.color = -1;
				break;
			case "RED" :
				this.color = 0;
				break;
			case "BLUE" :
				this.color = 1;
				break;
			default :
				System.out.println("error");
				break;
			}
			
			switch(backc) {
			case "GRAY" :
				this.backc = -1;
				break;
			case "WHITE" :
				this.backc = 0;
				break;
			case "BLACK" :
				this.backc = 1;
				break;
			default :
				System.out.println("error");
				break;
			}
		}
		
	}
	// 합이 맞는지 판단
	// 맞는 합이면 점수 계산을 위해 설정
	static boolean isRightHap(int a, int b, int c) {
		switch(board[a][b][c]) {
		case 0: case 2: return false;
		case 1:
			board[a][b][c] = 2;
			find++;
			return true;
		default :
			System.out.println("error3");
			break;
		}
		return true;
	}
	// 세트인지 판단하는 함수
	// 각 요소의 합이 모두 3/0/-3이면 세트이다.
	// 그 경우 값을 1로 세팅해준다.
	static boolean isSet(int a, int b, int c) {
		Card aa = cards[a];
		Card bb = cards[b];
		Card cc = cards[c];
		
		short one = (short) (aa.shape + bb.shape + cc.shape);
		if(!( one==3 || one==0 || one==-3 )) return false;
		short two = (short) (aa.color + bb.color + cc.color);
		if(!( two==3 || two==0 || two==-3 )) return false;
		short thr = (short) (aa.backc + bb.backc + cc.backc);
		if(!( thr==3 || thr==0 || thr==-3 )) return false;
		
		board[a][b][c] = 1;
		return true;
	}
	// 조합 방식으로 최대 합 갯수를 찾는다.
	// 조합 방식으로 찾으므로 idx가 항상 오름차순이다.
	static void comb(int cnt, int start) {
		if(cnt==3) {
			if(isSet(idxList[0],idxList[1],idxList[2])) amount++;
			return;
		}
		for (int i = start; i < 9; i++) {
			idxList[cnt] = i;
			comb(cnt+1,i+1);
		}
	}
	// 결인지 판단
	static boolean isFinished() {
		// 결은 한번 점수를 얻으면 이상 못받는다.
		if(find==amount && !gyul) {
			gyul = true;
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		// 입력방식 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 생성자 초기화
		cards = new Card[SIZE];
		idxList = new int[3];
		board = new short[SIZE][SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			cards[i] = new Card(st.nextToken(),st.nextToken(),st.nextToken());
		}
		// 합 갯수 생성
		comb(0,0);
		
		ROUND = Integer.parseInt(br.readLine());
		// 라운드마다
		for (int i = 0; i < ROUND; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken().charAt(0)) {
			case 'H':
				int data[] = new int[3];
				// 합이면 인덱스 계산을 위해 -1해주고 오름차순으로 정렬
				data[0] = Integer.parseInt(st.nextToken())-1;
				data[1] = Integer.parseInt(st.nextToken())-1;
				data[2] = Integer.parseInt(st.nextToken())-1;
				Arrays.sort(data);
				// 합인지 판단
				if(isRightHap(data[0],data[1],data[2])) score++;
				else score--;
				break;
				// 결인지 판단
			case 'G':
				if(isFinished()) score += 3;
				else score--;
				break;
			default:
				System.out.println("error2");
				break;
			}
		}
		// 점수 출력
		System.out.println(score);
		br.close();

	}

}
