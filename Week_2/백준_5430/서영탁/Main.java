import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            char[] comd = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();
            st = new StringTokenizer(str.substring(1, str.length()-1), ",");

            Deque<String> dq = new ArrayDeque<>();
            for(int i = 0; i < n; i++){
                dq.offer(st.nextToken());
            }

            boolean isPossible = true;
            boolean isReverse = false;
            for (char c : comd) {
                if(c == 'R') isReverse = !isReverse;
                else{
                    if(dq.isEmpty()) {
                        sb.append("error").append("\n");
                        isPossible = false;
                        break;
                    } else{
                        if(!isReverse) dq.pollFirst();
                        else dq.pollLast();
                    }
                }
            }

            if(isPossible){
                sb.append("[");

                while(!dq.isEmpty()){
                    if(isReverse) sb.append(dq.pollLast());
                    else sb.append(dq.pollFirst());

                    if(!dq.isEmpty()) sb.append(",");
                }

                sb.append("]").append("\n");
            }
        }

        System.out.println(sb.toString());
    }

}
