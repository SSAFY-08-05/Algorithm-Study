

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Snake{
        int dir;
        Point head;
        Point tail;

        Snake(int dir, Point head, Point tail) {
            this.dir=dir;
            this.head=head;
            this.tail=tail;
        }
    }

    static class Pair {
        int x;
        char c;

        Pair(int x, char c) {
            this.x=x;
            this.c=c;
        }
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static int [][]arr;
    static int n, k, l;
    static int x;
    static char c;
    static Queue<Pair> q;
    static int dx[]=new int[] {0, -1, 0, 1, 0};
    static int dy[]=new int[] {0, 0, 1, 0, -1}; //위 오 아래 왼
    static Snake snake;

    static int rotateDir(int dir) { //오른쪽으로 방향 회전
        int d=dir+1;
        if(d>4) d-=4;
        return d;
    }

    static int rotateOpDir(int dir) { //왼쪽으로 방향 회전
        int d=dir-1;
        if(d<1) d+=4;
        return d;
    }

    static boolean func() {
        //1. 먼저 뱀은 몸길이를 늘려 다음 칸에 위치시킨다.
        Point head=snake.head;
        int hx=head.x;
        int hy=head.y;
        int dir=snake.dir;

        hx+=dx[dir];
        hy+=dy[dir];
        if(hx<1 || hx>n || hy<1 || hy>n) return true; //벽에 부딪힘
        if(arr[hx][hy]>0) return true; //자기 자신의 몸과 부딪힘

        int num=arr[hx][hy];

        arr[hx][hy]=dir; //뱀 위치시키기, 사과 있으면 사과 없어짐
        snake.head=new Point(hx, hy);

        //만약 이동한 칸에 사과가 없다면, 몸 길이를 줄여서 꼬리가 위치한 칸을 비워준다.
        //즉, 몸 길이는 변하지 않는다.
        if(num!=-1) {
            Point tail=snake.tail;
            int d=arr[tail.x][tail.y];
            snake.tail=new Point(tail.x+dx[d], tail.y+dy[d]); //꼬리 위치 바꿔주기
            arr[tail.x][tail.y]=0;

        }


        return false;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        k=Integer.parseInt(br.readLine());

        arr=new int[n+1][n+1];
        q=new ArrayDeque<>();

        for(int i=0; i<k; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            arr[x][y]=-1; //사과 표시
        }

        l=Integer.parseInt(br.readLine());
        for(int i=0; i<l; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            char c=st.nextToken().charAt(0);

            q.add(new Pair(x, c));
        }

        snake=new Snake(2, new Point(1, 1), new Point(1, 1));
        arr[1][1]=2; //뱀 위치

        int time=0;
        while(true) {
            boolean isEnd=func();
            time++;

            if(isEnd) break;

            if(!q.isEmpty() && q.peek().x==time) {
                Pair p=q.poll();
                if(p.c=='L') {
                    snake.dir=rotateOpDir(snake.dir);
                }
                else if(p.c=='D') {
                    snake.dir=rotateDir(snake.dir);
                }
                arr[snake.head.x][snake.head.y]=snake.dir;
            }
        }
        System.out.println(time);
    }

}
