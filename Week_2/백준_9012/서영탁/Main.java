import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n-->0){
            Stack<Character> stack = new Stack<>();
            char[] ch = br.readLine().toCharArray();
            boolean flag = true;

            for (char c : ch) {
                if(c == '('){
                    stack.push(c);
                } else{
                    if(stack.isEmpty() || stack.pop() != '('){
                        flag = false;
                        break;
                    }
                }
            }

            if(!stack.isEmpty()) flag = false;

            if(flag) sb.append("YES");
            else sb.append("NO");

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}
