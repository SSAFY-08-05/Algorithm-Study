import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int ans = 0;

        while(n-->0){
            if(check(br.readLine().toCharArray())) ans++;
        }

        System.out.println(ans);
    }

    public static boolean check(char[] ch){
        int[] alpa = new int[26];
        Arrays.fill(alpa, -1);

        for(int i = 0; i < ch.length; i++){
            int idx = ch[i]-'a';

            if(alpa[idx] != -1 && alpa[idx] != i-1) return false;
            alpa[idx] = i;
        }
        return true;
    }

}