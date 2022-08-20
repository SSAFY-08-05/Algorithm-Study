import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        ArrayList<Integer> countList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    countList.add(bfs(i, j));
                }
            }
        }

        Collections.sort(countList);
        System.out.println(countList.size());
        for (int count : countList) {
            System.out.println(count);
        }
    }

    public static int bfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        int count = 0;
        while(!q.isEmpty()){
            int[] now = q.poll();
            count++;

            for(int i = 0; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }

        return count;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
