import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] ch = br.readLine().toCharArray();
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for (char c : ch) {
            left.push(c);
        }

        int m = Integer.parseInt(br.readLine());
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            char comd = st.nextToken().charAt(0);

            if(comd == 'L'){
                if(!left.isEmpty()) right.push(left.pop());
            } else if(comd == 'D'){
                if(!right.isEmpty()) left.push(right.pop());
            } else if(comd == 'B'){
                if(!left.isEmpty()) left.pop();
            } else{
                left.push(st.nextToken().charAt(0));
            }
        }

        while(!left.isEmpty()){
            right.push(left.pop());
        }

        StringBuilder sb = new StringBuilder();
        while(!right.isEmpty()){
            sb.append(right.pop());
        }

        System.out.println(sb.toString());
    }

}
