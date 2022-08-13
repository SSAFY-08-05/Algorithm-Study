import java.io.*;
import java.util.*;

public class Main {

    public static int l;
    public static boolean[] alpa;
    public static char[] selected;
    public static HashSet<Character> set = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        alpa = new boolean[26];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < c; i++){
            alpa[st.nextToken().charAt(0) - 'a'] = true;
        }

        selected = new char[l];
        backtracking(0, 0);
        System.out.println(sb.toString());
    }

    public static void backtracking(int idx, int cnt){
        if(cnt == l){
            if(check()){
                StringBuilder sub = new StringBuilder();
                for (char c : selected) {
                    sub.append(c);
                }

                sb.append(sub.toString()).append("\n");
            }
            return;
        }

        for(int i = idx; i < 26; i++){
            if(alpa[i]){
                alpa[i] = false;
                selected[cnt] = (char)(i + 'a');
                backtracking(i+1, cnt+1);
                alpa[i] = true;
            }
        }
    }

    public static boolean check(){
        boolean flag = false;
        int count = 0;

        for (char c : selected) {
            if(set.contains(c)) flag = true;
            else count++;

            if(flag && count >= 2) return true;
        }

        return false;
    }

}
