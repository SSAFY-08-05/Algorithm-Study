
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static Pair[]pos;
    static int dx[]=new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[]=new int[]{0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int n=4;
    static int ans=Integer.MIN_VALUE;
    static int[][]arr;
    static int[][]dir;

    static void fishMove(Pair shark) {
        for(int i=1; i<=n*n; i++) {
            Pair p=pos[i];
            if(p.x==-1) continue; //이미 먹힌 물고기

            int d=dir[p.x][p.y];
            if(d==-1) continue;
            boolean isblank=false;

            for(int k=0; k<8; k++) {
                int rotateDir=d+k;
                if(rotateDir>8) rotateDir-=8; //회전

                int nx=p.x+dx[rotateDir];
                int ny=p.y+dy[rotateDir];

                if(nx<0 || nx>=n || ny<0 || ny>=n) continue; //범위 체크
                if(nx==shark.x && ny==shark.y) continue; //상어가 있는 칸
                if(arr[nx][ny]==-1) { //여기는 빈칸
                    isblank=true;
                }

                dir[p.x][p.y]=rotateDir; //회전한 방향으로 다시 방향 세팅

                if(isblank) { //이동할 칸이 빈칸 -> 옮기기만 함
                    arr[nx][ny]=i;
                    dir[nx][ny]=rotateDir;
                    pos[i]=new Pair(nx, ny);

                    arr[p.x][p.y]=-1;
                    dir[p.x][p.y]=-1;
                }
                else { //이동할 칸에 물고기 있으면 자리 바꾸기
                    int tmpNum=arr[nx][ny];
                    int tmpDir=dir[nx][ny];
                    pos[arr[nx][ny]]=new Pair(p.x, p.y);
                    arr[nx][ny]=arr[p.x][p.y];
                    dir[nx][ny]=rotateDir;
                    arr[p.x][p.y]=tmpNum;
                    dir[p.x][p.y]=tmpDir;

                    pos[i]=new Pair(nx, ny);
                }
                break;
            }
        }
    }

    static void func(Pair shark, int sharkDir, int sum) {
        int [][]tmpArr=new int[n][n];
        int [][]tmpDir=new int[n][n];
        Pair[] tmpPos=new Pair[n*n+1];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                tmpArr[i][j]=arr[i][j];
                tmpDir[i][j]=dir[i][j];
            }
        }
        for(int i=1; i<=n*n; i++) {
            tmpPos[i]=new Pair(pos[i].x, pos[i].y); //백업
        }

        Pair tmpShark=shark;
        int tmpSharkDir=sharkDir; //백업
        int nx=shark.x;
        int ny=shark.y;

        while(true) {
            nx+=dx[sharkDir];
            ny+=dy[sharkDir]; //상어 갈 곳 찾는다

            if(nx<0 || nx>=n || ny<0 || ny>=n) { //범위 벗어나면 상어는 집으로 가
                ans=Math.max(ans, sum); //갱신
                break;
            }

            if(arr[nx][ny]==-1) continue; //물고기 없으면 지나감

            int add=arr[nx][ny];
            shark=new Pair(nx, ny);
            sharkDir=dir[nx][ny]; //먹음
            pos[arr[nx][ny]]=new Pair(-1, -1);
            arr[nx][ny]=-1; //상어가 물고기 먹음!
            dir[nx][ny]=-1;

            fishMove(shark); //물고기들 움직임
            func(shark, sharkDir, sum+add); //다시 먹으러

            shark=tmpShark;
            sharkDir=tmpSharkDir;

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    arr[i][j]=tmpArr[i][j];
                    dir[i][j]=tmpDir[i][j];
                }
            }
            for(int i=1; i<=n*n; i++) {
                pos[i]=new Pair(tmpPos[i].x, tmpPos[i].y); //복원
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        arr=new int[n][n];
        dir=new int[n][n];
        pos=new Pair[n*n+1];

        for(int i=0; i<n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int num=Integer.parseInt(st.nextToken());
                int d=Integer.parseInt(st.nextToken());

                arr[i][j]=num;
                dir[i][j]=d;

                pos[num]=new Pair(i, j);
            }
        }

        Pair shark=new Pair(0, 0);
        int sharkDir=dir[0][0];
        int num=arr[0][0];

        pos[num]=new Pair(-1, -1);
        arr[0][0]=-1;
        dir[0][0]=-1; //(0, 0)에 있는 거 먼저 먹음


        fishMove(shark); //먼저 물고기 이동하고..
        func(shark, sharkDir, num); //출발!

        System.out.println(ans);
    }
}
