import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        char[] ch = br.readLine().toCharArray();
        StringBuilder tmp = new StringBuilder();

        for (char c : ch) {
            tmp.append(c == '*' ? "(.*)" : c);
        }
        String pattern = tmp.toString();

        StringBuilder sb = new StringBuilder();
        while(n-->0){
            String fileName = br.readLine();
            sb.append(fileName.matches(pattern) ? "DA" : "NE").append("\n");
        }

        System.out.println(sb.toString());
    }

}