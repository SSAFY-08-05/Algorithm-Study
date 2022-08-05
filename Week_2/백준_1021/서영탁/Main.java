import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i <= n; i++) list.add(i);

        st = new StringTokenizer(br.readLine());
        int count = 0;

        while(m-->0){
            int target = Integer.parseInt(st.nextToken());
            int idx = list.indexOf(target);

            if(idx < list.size() - idx){
                while(list.get(0) != target){
                    list.add(list.get(0));
                    list.remove(0);
                    count++;
                }
            }else{
                while(list.get(0) != target){
                    list.add(0, list.get(list.size()-1));
                    list.remove(list.size()-1);
                    count++;
                }
            }

            list.remove(0);
        }

        System.out.println(count);
    }

}
