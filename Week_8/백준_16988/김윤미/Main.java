import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int [][]arr;
    static int []dx=new int[]{-1, 1, 0, 0};
    static int []dy=new int[]{0, 0, 1, -1};
    static boolean [][]visited;
    static int size;
    static Pair[]res=new Pair[2];
    static int ans=0;
    static List<Group> groups;
    static Set<Pair> zeroSet;
    static List<Pair> setList;
    static int count=2;

    static class Pair {
        int x;
        int y;

        Pair (int x, int y) {
            this.x=x;
            this.y=y;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }

        public boolean equals(Object obj) {
            if(obj instanceof Pair) {
                Pair p=(Pair) obj;
                return Integer.valueOf(this.x).equals(p.x) && Integer.valueOf(this.y).equals(p.y);
            }
            return false;
        }
    }

    static class Group {
        Set<Pair> list; //이 그룹이 막히기 위해 있어야 하는 좌표들의 리스트
        int cnt; //그룹 내 돌의 개수

        Group(Set<Pair> list, int cnt) {
            this.list=list;
            this.cnt=cnt;
        }
    }

    static void bfs(Pair pair) {
        Queue<Pair> q=new ArrayDeque<>();
        q.add(pair);
        visited[pair.x][pair.y]=true;

        int cnt=1;
        Set<Pair> zeros=new HashSet<>();

        while(!q.isEmpty()) {
            Pair p=q.poll();
            for(int d=0; d<4; d++) {
                int nx=p.x+dx[d];
                int ny=p.y+dy[d];
                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(visited[nx][ny]) continue;
                if(arr[nx][ny]==0) {
                    zeros.add(new Pair(nx, ny));
                    continue;
                }
                if(arr[nx][ny]==1) continue;

                q.add(new Pair(nx, ny));
                cnt++;
                visited[nx][ny]=true;
            }
        }

        if(zeros.size()>2) {
            return;
        }

        groups.add(new Group(zeros, cnt));
    }

    static void func(int k, int cnt) {
        if(k==count) {
            isPossible();
            return;
        }

        for(int i=cnt; i<size; i++) {
            res[k]=setList.get(i);
            func(k+1, cnt+1);
        }
    }

    static void isPossible() {
        for(int i=0; i<count; i++) {
            arr[res[i].x][res[i].y]=1;
        }

        int sum=0;
        boolean flag;
        for(Group g:groups) {
            flag=true;
            Set<Pair> zeros=g.list;

            for(Pair p:zeros) {
                if(arr[p.x][p.y]==0) {
                    flag=false;
                    break;
                }
            }
            if(flag) sum+=g.cnt;
        }
        ans=Math.max(sum, ans);

        for(int i=0; i<count; i++) {
            arr[res[i].x][res[i].y]=0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m= Integer.parseInt(st.nextToken());

        arr=new int[n][m];
        visited=new boolean[n][m];
        zeroSet=new HashSet<>();
        groups=new ArrayList<>();
        setList=new ArrayList<>();

        for(int i=0; i<n; i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        visited=new boolean[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j]==1 || arr[i][j]==0) continue;
                if(visited[i][j]) continue;
                bfs(new Pair(i, j));
            }
        }

        for(Group g:groups) {
            for(Pair p:g.list) {
                zeroSet.add(p);
            }
        }

        size=zeroSet.size();
        for(Pair p:zeroSet) {
            setList.add(p);
        }
        if(size==1) count=1;

        func(0, 0);

        System.out.println(ans);
    }
}
