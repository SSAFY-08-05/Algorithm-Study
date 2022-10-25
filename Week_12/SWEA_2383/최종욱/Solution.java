
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr;
	static ArrayList<Point> human;
	static Point[] stair;
	static int[] choice,Stime;
	static int min;
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());//testcase수
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());//한변의 길이
			
			arr = new int[N+1][N+1]; //사람과 계단 위치 표시
			human = new ArrayList<Point>();//사람 위치를 저장
			stair = new Point[2]; //계단 위치
			Stime = new int[2];
			
			int s = 0;
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]== 1) {
						human.add(new Point(i,j)); //사람 위치 저장
					}
					if(arr[i][j] != 0 && arr[i][j] != 1) {	
						Stime[s] = arr[i][j];
						stair[s] = new Point(i,j); //계단 위치 저장
						s++;
					}
				}
			}
			
			choice = new int[human.size()];
			
			min = Integer.MAX_VALUE; //최소시간
			perm(0);
			
			System.out.println("#"+ tc + " " + min);	
		}

	}

	private static void perm(int dep) {
		if(dep == human.size()) {
			cal(); 
			return;
		}
		for (int i = 0; i < 2; i++) { //내려갈 계단 선택
            choice[dep] = i;
            perm(dep + 1);
        }
		
		
		
	}

	private static void cal() {
		PriorityQueue<Integer> pq1 = new PriorityQueue<>(); //1번 계단 이용하는 사람
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(); //2번 계단 이용하는 사람
        
        for(int i = 0; i < human.size(); i++) { //사람과 계단 사이의 시간 계산
        	if(choice[i] == 0) {
        		pq1.add(Math.abs(human.get(i).x - stair[0].x) + Math.abs(human.get(i).y - stair[0].y));
        	}else {
        		pq2.add(Math.abs(human.get(i).x - stair[1].x) + Math.abs(human.get(i).y - stair[1].y));
        	}
        }
        
        int people = human.size(); //남은 인원
        
        int[] stair1 = new int[3]; //한번에 내려갈 수 있는 인원 3명
        int[] stair2 = new int[3];
        
        int time = 0;
        while(true) {
        	
        	if(people == 0) {
        		boolean flag = true;
        		for (int i = 0; i < 3; i++) { //이용 중인 인원이 있다면 계속 진행
                    if (stair1[i] != 0) {
                        flag = false;
                        break;
                    }
                    if (stair2[i] != 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    break;
        		
        	}
        	
        	for (int i = 0; i < 3; i++) { // 계단 동시 3명 이용 가능
                if (stair1[i] == 0) { // 현재 이용하는 사람이 없을때
                    if (!pq1.isEmpty()) { // 이용할 사람이 있다
                        if (pq1.peek() <= time) { //이용할 인원이 도착
                            people--; // 남은 이용자수를 내림
                            stair1[i] = Stime[0];// 해당 계단의 이동시간을 주고
                            pq1.poll(); //이용할 인원의 pq에서 인원 시간 제거
                        }
                    }
                } else { // 현재 계단을 사용하고 있다면
                    stair1[i]--; // 값을 내리고
                    if (stair1[i] == 0) {// 계단을 이용하고 있지 않다면.
                        if (!pq1.isEmpty()) {
                            if (pq1.peek() <= time) {
                                people--; // 이용자를 내린다.
                                stair1[i] = Stime[0];
                                pq1.poll();
                            }
                        }
                    }
                }

                if (stair2[i] == 0) { 
                    if (!pq2.isEmpty()) { 
                        if (pq2.peek() <= time) { 
                            people--; 
                            stair2[i] = Stime[1];
                            pq2.poll();
                        }
                    }
                } else { 
                    stair2[i]--; 
                    if (stair2[i] == 0) {
                        if (!pq2.isEmpty()) {
                            if (pq2.peek() <= time) {
                                people--; 
                                stair2[i] = Stime[1];
                                pq2.poll();
                            }
                        }
                    }
                }

            }
        	
        	time += 1;
        	
        	
        
        	
        	
        	
        }
        min = Math.min(min, time); //최솟값 계산
        
		
	}

}
