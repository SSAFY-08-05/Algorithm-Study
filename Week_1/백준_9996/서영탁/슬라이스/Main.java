import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), "*");

        String front = st.nextToken();
        String back = st.nextToken();

        int size = front.length() + back.length();

        StringBuilder sb = new StringBuilder();

        while(n-->0){
            String fileName = br.readLine();

            if(fileName.length() < size){
                sb.append("NE").append("\n");
                continue;
            }

            String f = fileName.substring(0, front.length());
            String b = fileName.substring(fileName.length() - back.length());

            if(f.equals(front) && b.equals(back)){
                sb.append("DA").append("\n");
            }else{
                sb.append("NE").append("\n");
            }
        }

        System.out.println(sb.toString());
    }

}