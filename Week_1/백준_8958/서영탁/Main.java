import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n-->0){
            char[] ch = br.readLine().toCharArray();
            int cnt = 0;
            int ans = 0;

            for (char c : ch) {
                if(c == 'O'){
                    cnt++;
                    ans += cnt;
                }
                else{
                    cnt = 0;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}