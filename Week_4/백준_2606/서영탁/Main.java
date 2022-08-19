import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        System.out.println(bfs(1));
    }

    public static int bfs(int start){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        int cnt = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]) {
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }

}
