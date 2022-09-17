import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2638_치즈_김윤미 {
    static int n, m;
    static int [][]arr;
    static int dx[]=new int[]{0, 0, 1, -1};
    static int dy[]=new int[]{1, -1, 0, 0};

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static void markOuter() {
        Queue<Pair> q=new ArrayDeque<>();
        q.add(new Pair(0, 0));

        boolean [][]visited=new boolean[n][m];
        visited[0][0]=true;

        while(!q.isEmpty()) {
            Pair p=q.poll();

            for(int d=0; d<4; d++) {
                int nx=p.x+dx[d];
                int ny=p.y+dy[d];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(visited[nx][ny]) continue;
                if(arr[nx][ny]>=1) { //치즈 발견..
                    arr[nx][ny]++; //접한 부분의 수 증가...
                    continue;
                }
                visited[nx][ny]=true;
                q.add(new Pair(nx, ny));
            }
        }
    }

    static boolean isAllMelted() {
        int cnt=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j]>=3) { //접한 부분이 2개 이상..!!
                    arr[i][j]=0; //치즈 녹다
                    cnt++;
                }
                else if(arr[i][j]!=0) {
                    arr[i][j]=1; //녹지 않은 치즈 복구
                }
            }
        }

        if(cnt>0) return false;
        else return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        arr=new int[n][m];

        for(int i=0; i<n; i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        int hour=0;

        while(true) {
            markOuter();
            if(!isAllMelted()) {
                hour++;
            }
            else break;
        }
        System.out.println(hour);
    }
}
