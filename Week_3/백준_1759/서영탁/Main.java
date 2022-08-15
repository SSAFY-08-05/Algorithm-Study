import java.io.*;
import java.util.*;

public class Main {

    public static int l, c;
    public static char[] alpa;
    public static char[] selected;
    public static HashSet<Character> set = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        alpa = new char[c];
        selected = new char[l];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < c; i++){
            alpa[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alpa);

        backtracking(0, 0);
        System.out.println(sb.toString());
    }

    public static void backtracking(int idx, int cnt){
        if(cnt == l){
            if(check()){
                StringBuilder sub = new StringBuilder();
                for (char ch : selected) {
                    sub.append(ch);
                }

                sb.append(sub.toString()).append("\n");
            }
            return;
        }

        for(int i = idx; i < c; i++){
            selected[cnt] = alpa[i];
            backtracking(i+1, cnt+1);
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
