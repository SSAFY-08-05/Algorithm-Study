import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class User implements Comparable<User>{
        int start;
        int end;

        User(int start, int end) {
            this.start=start;
            this.end=end;
        }

        @Override
        public int compareTo(User o) {
            int ans=this.start-o.start;
            if(ans==0) {
                ans=this.end-o.end;
            }
            return ans;
        }
    }

    static int getSeat(int h, int w) { //앉을 차리
        int res=0;
        int index=0;
        boolean flag=true;
        for(int i=1; i<=n; i++) {
            if(arr[h][w][i]) { //누가 앉아있음
                flag=false;
                continue;
            }
            int minDis=101; //최소 거리구하기..
            for(int j=1; j<=n; j++) {
                if(i==j) continue;
                if(!arr[h][w][j]) continue; //누가 앉아있는 자리 최초로 찾을 때까지
                int dis=Math.abs(i-j);
                minDis=Math.min(minDis, dis);
            }

            if(res<minDis) {
                res=minDis;
                index=i;
            }
        }
        if(flag) {
            return 1;
        }
        else return index;
    }

    static void visit() {
        while(!pq.isEmpty()) {
            User u=pq.poll();
            int seat = getSeat(u.start/100, u.start%100);
            for(int i=u.start/100; i<=u.end/100; i++) {
                if(i==u.start/100) { //시작 시간
                    if(u.start/100==u.end/100) { //끝 시간하고 같을 때
                        for (int j = u.start % 100; j < u.end % 100; j++) {
                            arr[i][j][seat] = true;
                        }
                    }
                    else {
                        for (int j = u.start % 100; j <= 59; j++) {
                            arr[i][j][seat] = true;
                        }
                    }
                }
                else if(i==u.end/100) { //끝 시간
                    for(int j=0; j<u.end%100; j++) {
                        arr[i][j][seat]=true;
                    }
                }
                else { //시작 시간과 끝 시간 사이 시간일 때
                    for(int j=0; j<=59; j++) {
                        arr[i][j][seat]=true;
                    }
                }
            }
        }
    }

    static int n, t, p; //좌석 개수, 예약자 수, 민규가 좋아하는 좌석 번호
    static boolean [][][]arr; //끝나는 시간 저장
    static PriorityQueue<User>pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        t=Integer.parseInt(st.nextToken());
        p=Integer.parseInt(st.nextToken()); //p번 자리 선호

        pq=new PriorityQueue<>();

        arr=new boolean[22][60][n+1]; //9시00분부터 21시00분까지 n번 자리 비었니

        for(int i=0; i<t; i++) {
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());

            pq.add(new User(start, end));
        }

        visit();

        int ans=0;
        for(int h=9; h<=20; h++) {
            for(int m=0; m<=59; m++) {
                if(!arr[h][m][p]) ans++;
            }
        }


        System.out.println(ans);
    }
}
