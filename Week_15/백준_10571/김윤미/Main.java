import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        for(int t=1; t<=T; t++) {
            int n=Integer.parseInt(br.readLine());
            double []w=new double[n+1]; //무게 저장
            double []c=new double[n+1]; //선명도 저장

            int dp[]=new int[n+1];

            for(int i=1; i<=n; i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                w[i]=Double.parseDouble(st.nextToken());
                c[i]=Double.parseDouble(st.nextToken());
            }

            dp[1]=1;

            for(int i=2; i<=n; i++) {
                dp[i]=1;
                for(int j=1; j<i; j++) {
                    if(w[j]<w[i] && c[j]>c[i]) {
                        dp[i]=Math.max(dp[i], dp[j]+1);
                    }
                }
            }

            int max=Integer.MIN_VALUE;
            for(int i=1; i<=n; i++) {
                max=Math.max(max, dp[i]);
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}
