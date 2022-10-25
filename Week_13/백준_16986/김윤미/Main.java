
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static int[][]arr;
    static int []used;

    static int [][]gm; //경희와 민호의 손동작 저장

    static int []cnt; //승수 카운트
    static int ans=0;

    static int getNext(int p1, int p2) {
        if(p1+p2==3) return 3; //1번과 2번 경기 -> 경기 안 한 사람: 3번
        else if(p1+p2==4) return 2; //1번과 3번 경기 -> 경기 안 한 사람: 2번
        else return 1; //경기 안 한 사람: 1번
    }

    static void game(int p1, int p2, int gCnt, int mCnt) {
        for(int i=1; i<=3; i++) {
            if(cnt[i]>=k) {
                if(i==1) {
                    ans=1;
                }
                return;
            }
        }

        if(p1==1) { //player1이 지우
            int h2=gm[p2][gCnt];
            if(p2==3) h2=gm[p2][mCnt];
            for(int i=1; i<=n; i++) {
                if(used[i]==1) continue; //이미 썼던 손동작이면 넘어감
                used[i]+=1; //손동작 한번 추가
                if(arr[i][h2]==2) {
                    cnt[p1]+=1; //승수 추가
                    if(p2==2) {
                        game(p1, getNext(p1, p2), gCnt+1, mCnt);
                    }
                    else {
                        game(p1, getNext(p1, p2), gCnt, mCnt+1);
                    }
                    cnt[p1]-=1;
                }

                else { //지우가 질 때
                    cnt[p2]+=1;
                    if(p2==2) {
                        game(p2, getNext(p1, p2), gCnt+1, mCnt);
                    }
                    else {
                        game(p2, getNext(p1, p2), gCnt, mCnt+1);
                    }
                    cnt[p2]-=1;
                }
                used[i]-=1;
            }
        }

        else if(p2==1) { //player2가 지우
            int h1=gm[p1][gCnt];
            if(p1==3) h1=gm[p1][mCnt];
            for(int i=1; i<=n; i++) {
                if(used[i]==1) continue; //이미 썼던 손동작이면 넘어감
                used[i]+=1; //손동작 한번 추가
                if(arr[h1][i]==0) { //이때 지우가 이김!!
                    cnt[p2]+=1;
                    if(p1==2) {
                        game(p2, getNext(p1, p2), gCnt+1, mCnt);
                    }
                    else {
                        game(p2, getNext(p1, p2), gCnt, mCnt+1);
                    }
                    cnt[p2]-=1;
                }

                else { //지우가 질 때
                    cnt[p1]+=1;
                    if(p1==2) {
                        game(p1, getNext(p1, p2), gCnt+1, mCnt);
                    }
                    else {
                        game(p1, getNext(p1, p2), gCnt, mCnt+1);
                    }
                    cnt[p1]-=1;
                }

                used[i]-=1;
            }
        }

        else { //정해져있는 승부..
            int h1, h2;
            if(p1==2 && p2==3) {
                h1=gm[p1][gCnt];
                h2=gm[p2][mCnt]; //손동작 받아오기

                if(arr[h1][h2]==2) { //이때만 경희가 이김
                    cnt[p1]+=1;
                    game(p1, getNext(p1, p2), gCnt+1, mCnt+1);
                    cnt[p1]-=1;
                }
                else {
                    cnt[p2]+=1;
                    game(p2, getNext(p1, p2), gCnt+1, mCnt+1);
                    cnt[p2]-=1;
                }
            }
            else { //민호 vs 경희
                h1=gm[p1][mCnt];
                h2=gm[p2][gCnt];

                if(arr[h1][h2]==0) { //이때만 경희가 이김
                    cnt[p2]+=1;
                    game(p2, getNext(p1, p2), gCnt+1, mCnt+1);
                    cnt[p2]-=1;
                }
                else {
                    cnt[p1]+=1;
                    game(p1, getNext(p1, p2), gCnt+1, mCnt+1);
                    cnt[p1]-=1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        arr=new int[n+1][n+1];
        cnt=new int[4];

        for(int i=1; i<=n; i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        gm=new int[4][21];
        used=new int[n+1];

        st=new StringTokenizer(br.readLine());
        for(int i=1; i<=20; i++)
            gm[2][i]=Integer.parseInt(st.nextToken()); //경희

        st=new StringTokenizer(br.readLine());
        for(int i=1; i<=20; i++)
            gm[3][i]=Integer.parseInt(st.nextToken()); //민호

        game(1, 2, 1, 1);

        System.out.println(ans);
    }
}
