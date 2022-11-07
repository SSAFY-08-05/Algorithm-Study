import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Integer>[] adj; //인접행렬
    static int[][]ans; //출력행렬

    static void find(int i, int start) {
        for(int x=0; x<adj[start].size(); x++) {
            int target=adj[start].get(x);
            if(ans[i][target]==1) continue;
            ans[i][target]=1;
            if(target==i) continue;
            find(i, target);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        n=Integer.parseInt(br.readLine());
        adj=new List[n+1];
        ans=new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            adj[i]=new ArrayList<>();
            for(int j=1; j<=n; j++) {
                int num=Integer.parseInt(st.nextToken());
                if(num==1) {
                    adj[i].add(j);
                }
            }
        }

        for(int i=1; i<=n; i++) {
            find(i, i);
        }

        StringBuilder sb=new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
