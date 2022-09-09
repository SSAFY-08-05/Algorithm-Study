import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Pair {
        int x;
        int y;
        int count;

        public Pair(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count=count;
        }
    }

    static int n, m, k;
    static int[][]arr;
    static long ans=Long.MAX_VALUE; //최단 경로
    static int []dx=new int[]{-1, 0, 1, 0};
    static int []dy=new int[]{0, 1, 0, -1};
    static int[][][]dis;

    static void bfs(int x, int y) {
        Queue<Pair> q=new ArrayDeque<>();
        dis=new int[n+1][m+1][k+1];

        dis[x][y][0]=1;
        q.add(new Pair(x, y, 0));

        while(!q.isEmpty()) {
            Pair p=q.poll();

            if(p.x==n && p.y==m) return;

            int cnt=p.count;

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if (arr[nx][ny] == 1) {
                    if(cnt>=k) continue;
                    if(dis[nx][ny][cnt+1]>0) continue;
                    q.add(new Pair(nx, ny, cnt+1));
                    dis[nx][ny][cnt+1]=dis[p.x][p.y][cnt]+1;
                } else {
                    if(dis[nx][ny][cnt]>0) continue;
                    q.add(new Pair(nx, ny, cnt));
                    dis[nx][ny][cnt]=dis[p.x][p.y][cnt]+1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        arr=new int[n+1][m+1];
        for (int i=1; i<=n; i++) {
            char []ch=br.readLine().toCharArray();
            for(int j=1; j<=m; j++) {
                arr[i][j]=ch[j-1]-'0';
            }
        }

        bfs(1, 1);

        for(int i=0; i<=k; i++) {
            if(dis[n][m][i]==0) continue;
            ans=Math.min(ans, dis[n][m][i]);
        }


        if(ans==Long.MAX_VALUE) ans=-1;
        System.out.println(ans);
    }
}
