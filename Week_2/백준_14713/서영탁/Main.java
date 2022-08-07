import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Queue<String>[] q = new Queue[n];
        for(int i = 0; i < n; i++){
            q[i] = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()){
                q[i].offer(st.nextToken());
            }
        }

        String[] str = br.readLine().split(" ");
        for (String s : str) {
            boolean flag = false;
            for(int i = 0; i < n; i++){
                if(!q[i].isEmpty() && q[i].peek().equals(s)){
                    q[i].poll();
                    flag = true;
                    break;
                }
            }

            if(!flag) {
                System.out.println("Impossible");
                return;
            }
        }

        for(int i = 0; i < n; i++){
            if(!q[i].isEmpty()){
                System.out.println("Impossible");
                return;
            }
        }

        System.out.println("Possible");
    }

}
