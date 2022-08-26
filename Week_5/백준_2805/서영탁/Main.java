import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] tree = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        long left = 0;
        long right = 2000000000;

        while(left <= right){
            long mid = (left + right) / 2;
            long sum = 0;

            for(int i = 0; i < n; i++){
                sum += Math.max(tree[i] - mid, 0);
            }

            if(sum >= m) left = mid+1;
            else right = mid-1;
        }

        System.out.println(left-1);
    }

}
