import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }

    static int n, m, k;
    static int t;
    static List<Node> arr[][];
    static int d[][];
    static int []ends;
    static int min;
    static PriorityQueue<Node> pq;

    static void dijkstra(int u1, int u2, int v1, int v2) {
        pq=new PriorityQueue<>();
        pq.add(new Node(u1, u2, 0));

        while(!pq.isEmpty()) {
            Node node=pq.poll();
            int nx=node.from;
            int ny=node.to;
            int nCost=node.cost;

            if(nx==v1 && ny==v2) {
                min=nCost;
                return;
            }

            for (Node next : arr[nx][ny]) { //좌표 리스트 보기
                int addCost=t;
                if(nx==next.from) {
                    addCost=1;
                }
                if (d[next.from][next.to] > nCost + addCost) {
                    d[next.from][next.to] = nCost + addCost;
                    pq.add(new Node(next.from, next.to, nCost + addCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        StringBuilder sb=new StringBuilder();

        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());

        arr=new ArrayList[n+1][51];
        d=new int[n+1][51];
        ends=new int[n+1];

        st=new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            int num=Integer.parseInt(st.nextToken());
            ends[i]=num;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=ends[i]; j++) {
                arr[i][j]=new ArrayList<>();
                if(j-1>=1) {
                    arr[i][j].add(new Node(i, j-1)); //인접 점들 추가
                }
                if(j+1<=ends[i]) {
                    arr[i][j].add(new Node(i, j+1));
                }
            }
        }

        m=Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            st=new StringTokenizer(br.readLine());
            int p1=Integer.parseInt(st.nextToken());
            int p2=Integer.parseInt(st.nextToken());
            int q1=Integer.parseInt(st.nextToken());
            int q2=Integer.parseInt(st.nextToken());

            arr[p1][p2].add(new Node(q1, q2));
            arr[q1][q2].add(new Node(p1, p2));
        }

        k=Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++) {
            min=-1;
            st=new StringTokenizer(br.readLine());
            t=Integer.parseInt(st.nextToken());
            int u1=Integer.parseInt(st.nextToken());
            int u2=Integer.parseInt(st.nextToken());
            int v1=Integer.parseInt(st.nextToken());
            int v2=Integer.parseInt(st.nextToken());

            for(int j=1; j<=n; j++) {
                Arrays.fill(d[j], Integer.MAX_VALUE);
            }
            d[u1][u2]=0; //시작점 초기화

            dijkstra(u1, u2, v1, v2);
            sb.append(min).append("\n");
        }

        System.out.println(sb.toString());
    }
}
