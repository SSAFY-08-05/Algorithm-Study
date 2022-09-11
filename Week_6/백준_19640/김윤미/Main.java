import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Employee implements Comparable<Employee>{
        int d; //근무일수
        int h; //화장실이 급한 정도
        int lineNum;
        boolean isDeca;

        public Employee(int d, int h, int lineNum, boolean isDeca) {
            this.d = d;
            this.h = h;
            this.lineNum = lineNum;
            this.isDeca = isDeca;
        }

        @Override
        public int compareTo(Employee o) {
            int ans=o.d-this.d;
            if(ans==0) ans=o.h-this.h;
            if(ans==0) ans=this.lineNum-o.lineNum;
            return ans;
        }
    }

    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        Queue<Employee>[]arr=new ArrayDeque[n+1];

        for(int i=1; i<=n; i++) {
            arr[i]=new ArrayDeque<>();
        }

        int lineNum=1;
        for(int i=1; i<=n; i++) {
            st=new StringTokenizer(br.readLine());
            int d=Integer.parseInt(st.nextToken());
            int h=Integer.parseInt(st.nextToken());

            if(i==k+1) {
                arr[lineNum].add(new Employee(d, h, lineNum, true));
            }
            else {
                arr[lineNum].add(new Employee(d, h, lineNum, false));
            }

            if(lineNum==m) {
                lineNum=0;
            }

            lineNum++;
        }

        int cnt=0;
        PriorityQueue<Employee> pq=new PriorityQueue<>();
        for(int i=1; i<=m; i++) {
            if(i<=n && !arr[i].isEmpty()) {
                pq.add(arr[i].peek());
            }
        }
        while(true) {
            Employee e=pq.poll();
            arr[e.lineNum].poll();
            if(!arr[e.lineNum].isEmpty()) {
                pq.add(arr[e.lineNum].peek());
            }

            if(e.isDeca) break;

            cnt++;
        }
        System.out.println(cnt);
    }
}

/**
서영탁
저도 똑같이 풀었는데 boolean isDeca로 두는 것보다 데카의 번호를 int로 두는게 조금 더 빠르더라고요.
왜 그런지는 잘 모르겠습니다.
**/
/**
박성완
저랑 비슷하게 풀었네요. 저는 처음엔 큐 자체를 priorityqueue에 넣어서 돌리려다가 이상해서 저렇게 풀었어요
**/
