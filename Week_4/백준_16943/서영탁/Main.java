import java.io.*;
import java.util.*;

public class Main {

    public static int B, size, ans = -1;
    public static int[] num;
    public static boolean flag;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        B = Integer.parseInt(b);
        size = a.length();

        num = new int[10];
        for(int i = 0; i < a.length(); i++){
            num['9' - a.charAt(i)]++;
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    public static void dfs(int cnt, int sum){
        if(cnt == size){
            if(sum < B){
                flag = true;
                ans = sum;
            }
            return;
        }

        int len = cnt == 0 ? 9 : 10;
        for(int i = 0; i < len; i++){
            if(num[i] > 0){
                num[i]--;
                dfs(cnt+1, sum*10 + (9-i));
                if(flag) return;
                num[i]++;
            }
        }
    }

}
