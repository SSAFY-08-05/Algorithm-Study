import java.io.*;
import java.util.*;

public class Main {

    public static int k;
    public static String max = "0", min = "999999999";
    public static char[] sign;
    public static int[] select;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        sign = new char[k];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            sign[i] = st.nextToken().charAt(0);
        }

        select = new int[k+1];
        visited = new boolean[10];

        dfs(0);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int idx){
        if(idx == k+1){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < k+1; i++){
                sb.append(select[i]);
            }

            String result = sb.toString();
            if(max.compareTo(result) < 0) max = result;
            if(min.compareTo(result) > 0) min = result;
            return;
        }

        for(int i = 0; i < 10; i++){
            if(!visited[i]){
                visited[i] = true;
                select[idx] = i;

                if(idx > 0){
                    if(sign[idx-1] == '>' && select[idx-1] > select[idx]) dfs(idx+1);
                    if(sign[idx-1] == '<' && select[idx-1] < select[idx]) dfs(idx+1);
                } else dfs(idx+1);

                visited[i] = false;
            }
        }
    }

}
