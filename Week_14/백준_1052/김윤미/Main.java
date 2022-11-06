import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        //n을 이진수로 만들었을 때 1의 개수가 k보다 같거나 작아야 하는 듯..
        //그럴때까지 계속 한개씩 더해라
        int cnt=0;
        while(true) {
            int ones = Integer.bitCount(n); //이진수로 만들었을 때 1의 개수 리턴
            if (ones <= k) break;
            n += 1;
            cnt++;
        }

        System.out.println(cnt);

    }
}

/*
13개 물병으로 2개 만들기..
13 = 1101 -> 1개 사기 -> 1110 -> 1개 더 사 -> 1111
-> 1개 더 사 ->  10000

아 비트...
 */
