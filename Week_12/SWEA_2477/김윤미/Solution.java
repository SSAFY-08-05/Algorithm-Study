import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static class Customer{
        int no; //손님 번호
        int arrivalTime; //창구 도착 시간
        int curTime; //남은 시간
        int wNo; //이용했던 접수 창구 번호

        Customer(int no, int curTime) {
            this.no=no;
            this.curTime=curTime;
        }

        Customer(int no, int curTime, int wNo) {
            this.no=no;
            this.curTime=curTime;
            this.wNo=wNo;
        }

        Customer(int no, int arrivalTime, int curTime, int wNo) {
            this.no=no;
            this.arrivalTime=arrivalTime;
            this.curTime=curTime;
            this.wNo=wNo;
        }
    }

    static int n, m, k, a, b;
    static int[]ai;
    static Customer[]isFullA;

    static int[]bj;
    static Customer[]isFullB;

    static PriorityQueue<Customer> cq; //접수 대기
    static PriorityQueue<Customer> rq; //정비 대기

    static boolean isEnd() {
        for(int i=1; i<=n; i++) {
            if(isFullA[i].curTime>0) return false;
        }

        for(int j=1; j<=m; j++) {
            if(isFullB[j].curTime>0) return false;
        }

        if(!cq.isEmpty()) return false;
        if(!rq.isEmpty()) return false;

        return true;
    }

//    static void printStatus() {
//        System.out.println("//isFullA//");
//        for(int i=1; i<=n; i++) {
//            System.out.println(i+"번째 접수창구에 있는 고객: "+isFullA[i].no+", 남은 시간: "+isFullA[i].curTime);
//        }
//        System.out.println();
//
//        System.out.println("//isFullB//");
//        for(int i=1; i<=m; i++) {
//            System.out.println(i+"번째 수리창구에 있는 고객: "+isFullB[i].no+", 남은 시간: "+isFullB[i].curTime);
//        }
//        System.out.println();
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        for(int t=1; t<=T; t++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            m=Integer.parseInt(st.nextToken());
            k=Integer.parseInt(st.nextToken());
            a=Integer.parseInt(st.nextToken());
            b=Integer.parseInt(st.nextToken());

            ai=new int[n+1]; //접수 창구
            isFullA=new Customer[n+1];

            bj=new int[m+1]; //정비 창구
            isFullB=new Customer[m+1];

            int ans=0;

            cq=new PriorityQueue<>(new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.no-o2.no;
                }
            }); //손님 웨이팅 큐

            rq=new PriorityQueue<>(new Comparator<Customer>() { //정비 웨이팅 큐
                @Override
                public int compare(Customer o1, Customer o2) {
                    int ans=o1.arrivalTime-o2.arrivalTime;
                    if(ans==0) {
                        ans=o1.wNo-o2.wNo;
                    }
                    return ans;
                }
            });

            st=new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) {
                ai[i]=Integer.parseInt(st.nextToken());
                isFullA[i]=new Customer(0, 0);
            }

            st=new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) {
                bj[j]=Integer.parseInt(st.nextToken());
                isFullB[j]=new Customer(0, 0);
            }

            st=new StringTokenizer(br.readLine());
            for(int i=1; i<=k; i++) {
                int arrivalTime=Integer.parseInt(st.nextToken());

                cq.add(new Customer(i, arrivalTime));
            }

            int sec=0;
            while(true) {
                for(int i=1; i<=n; i++) {
                    if(isFullA[i].curTime==1) { //정비 웨이팅으로 빠지기
                        Customer c=isFullA[i];
                        isFullA[i]=new Customer(0, 0);
                        rq.add(new Customer(c.no, sec, 0, c.wNo));
                    }
                }

                for (int i = 1; i <= n; i++) { //접수 창구에 넣어주기
                    if (isFullA[i].curTime == 0 && !cq.isEmpty()) {
                        if (cq.peek().curTime > sec) continue;
                        Customer c = cq.poll();
                        isFullA[i] = new Customer(c.no, ai[i], i);
                    }
                    else if(isFullA[i].curTime>0){
                        isFullA[i].curTime-=1;
                    }
                }

                for(int j=1; j<=m; j++) {
                    if(isFullB[j].curTime==1) {
                        isFullB[j]=new Customer(0, 0);
                    }
                }


                for(int j=1; j<=m; j++) {
                    if(isFullB[j].curTime==0 && !rq.isEmpty()) {
                        Customer c=rq.poll();
                        isFullB[j]=new Customer(c.no, bj[j], c.wNo);
                        if(c.wNo==a && j==b) {
                            ans+=c.no;
                        }
                    }
                    else if(isFullB[j].curTime>0){
                        isFullB[j].curTime-=1;
                    }
                }

//                System.out.println("시간: "+sec);
//                printStatus();

                sec++;

                if(isEnd()) break;
            }

            if(ans==0) ans=-1;

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
