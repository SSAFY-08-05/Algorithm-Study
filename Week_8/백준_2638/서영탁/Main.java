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

    public static int n, m;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        int cheese = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese++;
            }
        }

        System.out.println(bfs(cheese));
    }

    public static int bfs(int cheese){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));  // 테두리에는 치즈가 없다고 문제에 주어졌기 때문에 테두리 중 아무 곳에서 시작.
        int time = 0;

        while(cheese > 0){  // 남아있는 치즈가 없을 때까지 반복
            // 새롭게 생긴 외부 공간을 모두 10으로 변경
            int size = q.size();
            while(size-->0){
                Pos p = q.poll();
                map[p.x][p.y] = 10;
                q.offer(p);
            }

            // 새로운 외부 공간으로부터 이어진 빈 공간을 10으로 변경
            while(!q.isEmpty()){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(!isRange(nx, ny)) continue;

                    if(map[nx][ny] == 0){
                        map[nx][ny] = 10;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }

            // 전체 좌표를 보면서 녹을 치즈 탐색
            // 외부 공간을 10으로 설정해두었기 때문에
            // 치즈 위치에서 4방 탐색하여 합이 20이 넘어가면 그 치즈는 이번 턴에 녹을 것임.
            for(int i = 1; i < n-1; i++){
                for(int j = 1; j < m-1; j++){
                    if(map[i][j] == 1){
                        int sum = 0;
                        for(int k = 0; k < 4; k++){
                            sum += map[i+dx[k]][j+dy[k]];
                        }

                        if(sum >= 20) q.offer(new Pos(i, j));
                    }
                }
            }

            cheese -= q.size();
            time++;
        }

        return time;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
