import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n;
    public static int[][] map;
    public static Pos head, tail;  // 머리, 꼬리
    public static int dir;  // 방향

    public static int[] dx = {0, 0, 1, 0, -1};
    public static int[] dy = {0, 1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];

        int k = Integer.parseInt(br.readLine());
        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 9;  // 사과는 9
        }

        dir = 1;  // 방향은 오른쪽 : 1 부터 시작
        head = new Pos(1, 1);
        tail = new Pos(1, 1);  // 머리, 꼬리 위치 초기화
        map[1][1] = dir;  // map에 뱀의 방향 저장

        int l = Integer.parseInt(br.readLine());
        int[] xs = new int[l+1];  // 방향 바꾸는 시간
        char[] comds = new char[l+1];  // 방향 전환

        for(int i = 0; i < l; i++){
            st = new StringTokenizer(br.readLine());
            xs[i] = Integer.parseInt(st.nextToken());
            comds[i] = st.nextToken().charAt(0);
        }

        xs[l] = 1000000;  // 맨 마지막에 의미없는 값 추가
        comds[l] = 'R';

        int time = 1;
        int idx = 0;

        while(true){
            int x = xs[idx];
            int comd = comds[idx++];

            for(int t = time; t <= x; t++){  // 해당 시간까지 뱀을 움직여봄.
                if(!move()){
                    System.out.println(t);
                    return;
                }
            }

            time = x+1;
            // 방향 전환
            if(comd == 'D') {
                dir++; if(dir == 5) dir = 1;
            }
            else if(comd == 'L'){
                dir--; if(dir == 0) dir = 4;
            }

            map[head.x][head.y] = dir;
        }
    }

    public static boolean move(){
        head.x += dx[dir];
        head.y += dy[dir];  // 머리 이동

        if(!isRange()) return false;  // 범위를 벗어나거나, 몸통에 부딪히면 종료

        if(map[head.x][head.y] == 0){  // 사과를 먹지 않았으면 꼬리를 줄여야함.
            int tx = tail.x;
            int ty = tail.y;

            tail.x += dx[map[tx][ty]];  // map에 방형을 저장해뒀기 때문에, 그 방향으로 꼬리를 이동시키면 됨
            tail.y += dy[map[tx][ty]];

            map[tx][ty] = 0;
        }

        map[head.x][head.y] = dir;  // map에 머리 방향 저장
        return true;
    }

    public static boolean isRange(){
        return head.x > 0 && head.x <= n && head.y > 0 && head.y <= n &&
                (map[head.x][head.y] == 0 || map[head.x][head.y] == 9);
    }

}
