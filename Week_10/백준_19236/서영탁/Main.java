import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        int num, dir;
        int eat;

        // 물고기용 생성자
        public Pos(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }

        // 상어용 생성자
        public Pos(int x, int y, int num, int dir, int eat) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
            this.eat = eat;  // 상어가 먹은 물고기 번호 합
        }

    }

    public static int n = 4, size, ans;

    public static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = n*n;
        int[][] map = new int[n][n];  // 물고기 번호 저장 배열
        Pos[] fishes = new Pos[size+1];  // 물고기 객체 저장

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                fishes[num] = new Pos(i, j, num, dir);
                map[i][j] = num;  // map에는 물고기의 번호만 저장
            }
        }

        // 초기 상어 : map[0][0] 물고기의 방향, eat : map[0][0] 물고기의 번호
        Pos shark = new Pos(fishes[map[0][0]].x, fishes[map[0][0]].y, 0, fishes[map[0][0]].dir, fishes[map[0][0]].num);
        fishes[map[0][0]].dir = 0;  // 먹힌 물고기는 dir(방향)을 0으로 설정.
        map[0][0] = -1;  // 상어 위치는 map에서 -1로 표시

        dfs(map, fishes, shark);  // 가능한 모든 경우를 dfs 탐색

        System.out.println(ans);
    }

    public static void dfs(int[][] map, Pos[] fishes, Pos shark){
        if(ans < shark.eat){
            ans = shark.eat;
        }

        moveFish(map, fishes);  // 물고기 이동

        // 상어 이동
        int x = shark.x;
        int y = shark.y;

        for(int d = 1; d < 4; d++){  // map이 4 * 4 이므로 최대 4번까지 이동 가능
            int nx = x + dx[shark.dir] * d;
            int ny = y + dy[shark.dir] * d;

            if(!isRange(nx, ny)) continue;
            if(map[nx][ny] <= 0) continue;  // 물고기가 있는 위치로만 이동 가능.

            int[][] cMap = copyMap(map);  // map 복사
            Pos[] cFishes = copyFishes(fishes);  // fishes 복사

            Pos eatenFish = cFishes[cMap[nx][ny]];  // 먹힐 물고기
            // 상어 위치 변경 -> 방향과 먹은 물고기 합 증가
            Pos newShark = new Pos(eatenFish.x, eatenFish.y, 0, eatenFish.dir, shark.eat+eatenFish.num);
            eatenFish.dir = 0;  // 먹힌 물고기는 dir = 0
            cMap[x][y] = 0;  // 기존 상어 위치 = 0
            cMap[eatenFish.x][eatenFish.y] = -1;  // 이동한 후 상어 위치 = -1

            dfs(cMap, cFishes, newShark);  // dfs 탐색
        }
    }

    public static void moveFish(int[][] map, Pos[] fishes){
        for(int i = 1; i <= size; i++){  // 1번부터 16번 물고기까지 차례로 이동
            Pos fish = fishes[i];
            int x = fish.x;
            int y = fish.y;
            int dir = fish.dir;

            if(dir == 0) continue;  // dir == 0이면 먹힌 물고기이므로 패스

            for(int j = 0; j < 9; j++){  // 현재 dir로 움직일 수 없으면 반시계 방향으로 45도씩 회전
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(!isRange(nx, ny)) {
                    dir++;
                    if(dir == 9) dir = 1;
                    continue;
                }

                if(map[nx][ny] > -1){   // 이동할 위치에 상어가 아니면 이동
                    if(map[nx][ny] == 0){  // 이미 먹혀서 물고기가 없는 자리
                        map[x][y] = 0;
                    }else {  // 물고기가 있어 위치를 서로 바꿔야하는 자리
                        Pos changedFish = fishes[map[nx][ny]];
                        changedFish.x = fish.x;
                        changedFish.y = fish.y;
                        map[fish.x][fish.y] = changedFish.num;
                    }

                    fish.x = nx;
                    fish.y = ny;

                    map[nx][ny] = fish.num;
                    fish.dir = dir;
                    break;
                }

                dir++;
                if(dir == 9) dir = 1;
            }

        }
    }

    public static int[][] copyMap(int[][] map){
        int[][] copy = new int[n][n];

        for(int i = 0; i < 4; i++){
            copy[i] = map[i].clone();
        }

        return copy;
    }

    public static Pos[] copyFishes(Pos[] fishes){
        Pos[] copy = new Pos[size+1];
        for(int i = 1; i <= size; i++){
            Pos fish = fishes[i];
            copy[i] = new Pos(fish.x, fish.y, fish.num, fish.dir);
        }

        return copy;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
