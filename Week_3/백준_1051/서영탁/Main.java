import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int bigSize = findBigSize();
        System.out.println(bigSize * bigSize);
    }

    public static int findBigSize(){
        int size = Math.min(n, m);
        for(int s = size; s >= 2; s--){
            for(int i = 0; i <= n-s; i++){
                for(int j = 0; j <= m-s; j++){
                    if(map[i][j] == map[i][j+s-1] && map[i][j] == map[i+s-1][j] && map[i][j] == map[i+s-1][j+s-1])
                        return s;
                }
            }
        }

        return 1;
    }
}
