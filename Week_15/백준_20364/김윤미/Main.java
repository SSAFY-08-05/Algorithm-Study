import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        q=Integer.parseInt(st.nextToken());
        StringBuilder sb=new StringBuilder();

        boolean[]visited=new boolean[n+1];

        for(int i=0; i<q; i++) {
            int x=Integer.parseInt(br.readLine());
            boolean isPossible=true;
            int ans=0;
            int num=x;
            while(num>=1) {
                if(visited[num]) {
                    isPossible=false;
                    ans=num;
                }
                num/=2;
            }
            if(!isPossible) {
                sb.append(ans).append("\n");
            }
            else {
                visited[x]=true;
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
