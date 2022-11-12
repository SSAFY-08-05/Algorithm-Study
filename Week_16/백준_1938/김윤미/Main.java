import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static class Log {
        Pair md;
        int d; //1이면 세로, 2이면 가로 방향
        int cnt;

        Log() {}

        public Log(Pair md, int d, int cnt) {
            this.md = md;
            this.d = d;
            this.cnt = cnt;
        }
    }

    static int dx[]=new int[]{-1, 1, 0, 0};
    static int dy[]=new int[]{0, 0, -1, 1}; //위 아래 왼 오
    static char[][]arr;
    static int n;
    static Log cur; //현재 통나무 위치 저장
    static Log des; //목적지 통나무
    static int ans;
    static boolean visited[][][]; //중심점과 그 방향으로 방문했는지 여부를 저장해야 함..(세로 or 가로)
    
    static boolean isPossible(int x, int y, int curD, int dir) {
        if(dir==4) { //회전
            if(curD==1) { //방문 여부 체크
                if(visited[x][y][2]) return false;
            }
            else {
                if(visited[x][y][1]) return false;
            }
            for(int i=x-1; i<=x+1; i++) {
                for(int j=y-1; j<=y+1; j++) {
                    if(!isRange(i, j)) return false;
                    if(arr[i][j]=='1') return false;
                }
            }
        }
        else {
            int x2=x+dx[dir];
            int y2=y+dy[dir];


            if(curD==1) { //세로
                if(!isRange(x2-1, y2) || !isRange(x2, y2) || !isRange(x2+1, y2)) {
                    return false;
                }
                if(arr[x2-1][y2]=='1' || arr[x2][y2]=='1' || arr[x2+1][y2]=='1') {
                    return false;
                }
            }

            else { //가로
                if (!isRange(x2, y2-1) || !isRange(x2, y2) || !isRange(x2, y2+1)) {
                    return false;
                }
                if(arr[x2][y2-1]=='1' || arr[x2][y2]=='1' || arr[x2][y2+1]=='1') {
                    return false;
                }
            }

            if(visited[x2][y2][curD]) return false;
        }

        return true;
    }

    static void bfs(int cnt) { //cnt: 현재 동작 횟수
        Queue<Log> q=new ArrayDeque<>();
        visited[cur.md.x][cur.md.y][cur.d]=true; //통나무 방향으로 현재 중심점 방문함..

        q.add(cur);

        while(!q.isEmpty()) {
            Log l=q.poll();
            for(int i=0; i<5; i++) {
                if(!isPossible(l.md.x, l.md.y, l.d, i)) continue;

                if(i==4) { //회전
                    if(l.d==1) { //지금 세로 방향이면
                        q.add(new Log(new Pair(l.md.x, l.md.y), 2, l.cnt+1));
                        if(isEqual(l.md.x, l.md.y, 2)) {
                            ans=Math.min(ans, l.cnt+1);
                            return;
                        }
                        visited[l.md.x][l.md.y][2]=true;
                    }
                    else { //지금 가로 방향이면
                        q.add(new Log(new Pair(l.md.x, l.md.y), 1, l.cnt+1));
                        if(isEqual(l.md.x, l.md.y, 1)) {
                            ans=Math.min(ans, l.cnt+1);
                            return;
                        }
                        visited[l.md.x][l.md.y][1]=true;
                    }
                }

                else {
                    int nx=l.md.x+dx[i];
                    int ny=l.md.y+dy[i];

                    if(isEqual(nx, ny, l.d)) {
                        ans=Math.min(ans, l.cnt+1);
                        return;
                    }

                    q.add(new Log(new Pair(nx, ny), l.d, l.cnt+1));
                    visited[nx][ny][l.d]=true;
                }

            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        arr=new char[n][n];
        visited=new boolean[n][n][3];
        cur=new Log();
        des=new Log();
        ans=Integer.MAX_VALUE;

        int cnt1=0;
        int cnt2=0;
        for(int i=0; i<n; i++) {
            arr[i]=br.readLine().toCharArray();
            for(int j=0; j<n; j++) {
                if(arr[i][j]=='B') {
                    if(cnt1==1) cur.md=new Pair(i, j);
                    cnt1++;
                }
                else if(arr[i][j]=='E') {
                    if(cnt2==1) des.md=new Pair(i, j);
                    cnt2++;
                }
            }
        }


        if(isRange(cur.md.x+1, cur.md.y) && arr[cur.md.x+1][cur.md.y]=='B') { //현재 통나무 방향 설정
            cur.d=1;
        }
        else cur.d=2;

        if(isRange(des.md.x+1, des.md.y) && arr[des.md.x+1][des.md.y]=='E') { //목적지 통나무 방향 설정
            des.d=1;
        }
        else des.d=2;

        cur.cnt=0;

        bfs(0);

        if(ans==Integer.MAX_VALUE) {
            ans=0;
        }
        System.out.println(ans);
    }

    static boolean isEqual(int mx, int my, int md) {
        if(mx==des.md.x && my==des.md.y && md==des.d) {
            return true;
        }
        else return false;
    }

    static boolean isRange(int x, int y) {
        if(x<0 || x>=n || y<0 || y>=n) return false;
        else return true;
    }
}
