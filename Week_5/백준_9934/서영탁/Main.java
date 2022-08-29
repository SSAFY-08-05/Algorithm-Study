import java.io.*;
import java.util.*;

public class Main {

    public static int k;
    public static int[] arr;
    public static StringBuilder[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        int n = (int)Math.pow(2, k) - 1;

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = new StringBuilder[k];
        for(int i = 0; i < k; i++){
            ans[i] = new StringBuilder();
        }

        rec(0, n, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++){
            sb.append(ans[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void rec(int left, int right, int idx){
        if(idx == k) return;

        int mid = (left + right) / 2;
        ans[idx].append(arr[mid]).append(" ");

        rec(left, mid-1, idx+1);
        rec(mid+1, right, idx+1);
    }

}
