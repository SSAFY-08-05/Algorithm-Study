import java.io.*;
import java.util.*;

public class Main {

    public static class Egg{
        int durability, weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    public static int n, max;
    public static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        eggs = new Egg[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dfs(0, 0);
        System.out.println(max);
    }

    public static void dfs(int idx, int cnt){
        if(idx == n){
            max = Math.max(max, cnt);
            return;
        }

        Egg curEgg = eggs[idx];

        if(curEgg.durability <= 0 || cnt == n-1){
            dfs(idx+1, cnt);
            return;
        }

        int nCnt = cnt;
        for(int i = 0; i < n; i++){
            if(idx == i) continue;
            if(eggs[i].durability <= 0) continue;

            curEgg.durability -= eggs[i].weight;
            eggs[i].durability -= curEgg.weight;

            if(curEgg.durability <= 0) cnt++;
            if(eggs[i].durability <= 0) cnt++;

            dfs(idx+1, cnt);

            curEgg.durability += eggs[i].weight;
            eggs[i].durability += curEgg.weight;
            cnt = nCnt;
        }
    }

}
