import java.io.*;
import java.util.*;

public class Main {

    public static class Pos implements Comparable<Pos>{

        int num;
        int x, y;
        int dist;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public Pos(int num, int x, int y, int dist) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.dist == o.dist){
                if(this.x == o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }

    public static int n, m, fuel;
    public static int[][] map, passengers;
    public static Pos taxi;
    public static Pos[] destinations;
    public static Pos passenger, destination;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        passengers = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        destinations = new Pos[m+1];

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int px = Integer.parseInt(st.nextToken());
            int py = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            passengers[px][py] = i;
            destinations[i] = new Pos(dx, dy);
        }

        if(move()) System.out.println(fuel);
        else System.out.println(-1);
    }

    public static boolean move(){
        for(int i = 0; i < m; i++){
            if(findPassenger()){
                int num = passenger.num;
                int dist = passenger.dist;

                if((fuel -= dist) <= 0) return false;

                if(findDestination(num)){
                    if((fuel -= destination.dist) < 0) return false;
                    fuel += destination.dist * 2;
                }else return false;
            }else return false;
        }
        return true;
    }

    public static boolean findPassenger(){
        boolean[][] visited = new boolean[n+1][n+1];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(taxi);
        visited[taxi.x][taxi.y] = true;

        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int dist = p.dist;

            if(passengers[x][y] != 0){
                passenger = new Pos(passengers[x][y], x, y, dist);
                taxi = new Pos(x, y);
                passengers[x][y] = 0;
                return true;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 0){
                    visited[nx][ny] = true;
                    pq.offer(new Pos(nx, ny, dist+1));
                }
            }
        }
        return false;
    }

    public static boolean findDestination(int num){
        boolean[][] visited = new boolean[n+1][n+1];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(taxi);
        visited[taxi.x][taxi.y] = true;

        Pos targetDestination = destinations[num];

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int dist = p.dist;

            if(x == targetDestination.x && y == targetDestination.y){
                destination = new Pos(x, y, dist);
                taxi = new Pos(x, y);
                return true;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 0){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, dist+1));
                }
            }
        }
        return false;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= n && y > 0 && y <= n;
    }
}
