import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int cost, idx;

        public Node(int cost, int idx) {
            this.cost = cost;
            this.idx = idx;
        }
    }

    public static LinkedList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                list.add(new Node(Integer.parseInt(st.nextToken()), i));
            }

            int ans = 0;
            while(true){
                int idx = check();

                if(idx == 0) {
                    Node node = pop();
                    ans++;
                    if(node.idx == m) break;
                } else{
                  push();
                }
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int check(){
        int cost = list.get(0).cost;

        for(int i = 1; i < list.size(); i++){
            if(cost < list.get(i).cost) return i;
        }

        return 0;
    }

    public static Node pop(){
        Node node = list.get(0);
        list.remove(0);
        return node;
    }

    public static void push(){
        list.add(list.get(0));
        list.remove(0);
    }

}
