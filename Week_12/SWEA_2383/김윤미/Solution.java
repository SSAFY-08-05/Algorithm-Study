
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static class Stair{
        int x;
        int y;
        int height;
        Person[] godown;
        List<Person> waiting;

        Stair(int x, int y, int height) {
            this.x=x;
            this.y=y;
            this.height=height;
        }
    }

    static class Person implements Comparable<Person>{
        int no; //사람 번호
        int x;
        int y;
        int dis; //계단까지의 거리
        int curTime; //계단 내려가기까지 남은 시간

        Person(int no, int x, int y) {
            this.no=no;
            this.x=x;
            this.y=y;
        }

        @Override
        public int compareTo(Person o) {
            return this.dis-o.dis;
        }
    }

    static boolean np(int []input) {
        int i=cnt-1;

        while(i>0 && input[i-1]>=input[i]) --i;

        if(i==0) return false;

        int j=cnt-1;

        while(input[i-1]>=input[j]) --j;

        swap(input, i-1, j);

        int k=cnt-1;

        while(i<k) swap(input, i++, k--);

        return true;
    }

    static void swap(int []input, int i, int j) {
        int tmp=input[i];
        input[i]=input[j];
        input[j]=tmp;
    }

    static boolean isEnd(PriorityQueue<Person> pq1, PriorityQueue<Person> pq2, Person[] p1, Person[]p2) {
        if(!pq1.isEmpty()) return false;
        if(!pq2.isEmpty()) return false;
        for(int i=0; i<3; i++) {
            if(p1[i]==null) continue;
            if(p1[i].curTime>0) return false;
        }

        for(int i=0; i<3; i++) {
            if(p2[i]==null) continue;
            if(p2[i].curTime>0) return false;
        }

        for(int i=0; i<2; i++) {
            for(int j=0; j<stairs[i].waiting.size(); j++) {
                if(stairs[i].waiting.get(j).dis!=-1) {
                    return false;
                }
            }
        }

        return true;
    }

    static int go(List<Person> group1, List<Person> group2) {
        //group1은 1번 계단으로
        //group2는 2번 계단으로

        //리스트 복사
        List<Person> g1=new ArrayList<>();
        for(int i=0; i<group1.size(); i++) {
            g1.add(group1.get(i));
        }

        List<Person> g2=new ArrayList<>();
        for(int i=0; i<group2.size(); i++) {
            g2.add(group2.get(i));
        }

        PriorityQueue<Person> pq1=new PriorityQueue<>();
        PriorityQueue<Person> pq2=new PriorityQueue<>();
        //웨이팅 큐
        stairs[0].godown=new Person[3];
        stairs[1].godown=new Person[3];

        int s1x=stairs[0].x;
        int s1y=stairs[0].y;
        for(int i=0; i<g1.size(); i++) {
            Person p=g1.get(i);
            p.dis=Math.abs(p.x-s1x)+Math.abs(p.y-s1y);
            p.curTime=-1;
            pq1.add(p);
        }

        int s2x=stairs[1].x;
        int s2y=stairs[1].y;
        for(int i=0; i<g2.size(); i++) {
            Person p=g2.get(i);
            p.dis=Math.abs(p.x-s2x)+Math.abs(p.y-s2y);
            p.curTime=-1;
            pq2.add(p);
        }

        stairs[0].waiting=new ArrayList<>();
        stairs[1].waiting=new ArrayList<>();

        int sec=1;
        while(true) {
            for(int i=0; i<3; i++) { //계단 내려가기
                for(int j=0; j<2; j++) { //2개의 계단..
                    if(stairs[j].godown[i]==null) continue;
                    if(stairs[j].godown[i].curTime<=0) {
                        continue;
                    }
                    stairs[j].godown[i].curTime-=1;
                }
            }

            while(!pq1.isEmpty()) { //계단 입구에 도착
                Person p=pq1.peek();
                if(p.dis==sec) {
                    pq1.poll();
                    stairs[0].waiting.add(p);
                }
                else break;
            }

            while(!pq2.isEmpty()) {
                Person p=pq2.peek();
                if(p.dis==sec) {
                    pq2.poll();
                    stairs[1].waiting.add(p);
                }
                else break;
            } //계단 입구 대기줄에 추가

            //계단 내려가는 리스트에 넣어주기
            for(int i=0; i<2; i++) {
                for(int j=0; j<stairs[i].waiting.size(); j++) {
                    if(stairs[i].waiting.get(j).dis==-1) continue;

                    if(stairs[i].waiting.get(j).curTime==-1) { //아직 계단 못 내려감(1분 안 지남)
                        stairs[i].waiting.get(j).curTime=stairs[i].height;
                    }
                    else { //계단 내려갈 수 있을 때
                        for(int k=0; k<3; k++) {
                            if(stairs[i].godown[k]==null || stairs[i].godown[k].curTime==0) {
                                stairs[i].godown[k]=stairs[i].waiting.get(j);

                                stairs[i].waiting.get(j).dis=-1; //계단 내려감 표시
                                break;
                            }
                        }
                    }
                }
            }

            if(isEnd(pq1, pq2, stairs[0].godown, stairs[1].godown)) {
                break;
            }

            sec++;
        }
        return sec;
    }

    static int n;
    static int [][]arr;
    static List<Person> people; //사람들 리스트
    static Stair[]stairs; //계단 배열 -> 크기는 2
    static int cnt; //사람 수
    static int[]input; //next_permutation용 배열
    static List<Person> group1;
    static List<Person> group2;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            n=Integer.parseInt(br.readLine());
            arr=new int[n][n];
            stairs=new Stair[2];
            people=new ArrayList<>();
            cnt=0;
            int sCnt=0; //계단 개수

            int ans=Integer.MAX_VALUE;

            for(int i=0; i<n; i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());
                    if(arr[i][j]==1) {
                        cnt++;
                        people.add(new Person(cnt, i, j));
                    }
                    else if(arr[i][j]>1) {
                        stairs[sCnt++]=new Stair(i, j, arr[i][j]);
                    }
                }
            }
            for(int c=0; c<=cnt/2; c++) {
                input=new int[cnt];

                for(int i=cnt-c; i<cnt; i++) {
                    input[i]=1;
                }

                do {
                    group1=new ArrayList<>();
                    group2=new ArrayList<>();

                    for(int i=0; i<cnt; i++) {
                        if(input[i]==0) {
                            group1.add(people.get(i));
                        }
                        else {
                            group2.add(people.get(i));
                        }
                    }
                    int res=go(group1, group2);
                    ans=Math.min(ans, res);

                    res=go(group2, group1);
                    ans=Math.min(ans, res);

                }while(np(input));
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

}
