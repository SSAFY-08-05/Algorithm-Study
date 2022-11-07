import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static long []arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken()); //입국심사대 개수
        m=Integer.parseInt(st.nextToken()); //사람 수
        arr=new long[n];

        long min=Long.MAX_VALUE;
        long max=Long.MIN_VALUE;

        for(int i=0; i<n; i++) {
            arr[i]=Long.parseLong(br.readLine());
            min=Math.min(min, arr[i]); //심사 받는 최소시간..
            max=Math.max(max, arr[i]*m); //심사 받는 최대 시간..
        }

        long ans=Long.MAX_VALUE;
        while(min<=max) {
            long mid=(min+max)/2; //이 시간 안에 검사해보겠음
            long cnt=0; //심사 받을 수 있는 사람 수

            for(int i=0; i<n; i++) {
                cnt+=(mid/arr[i]); //mid가 8이고 arr[i]가 3이면 2명 검사가능하니..
            }

            if(cnt<m) { //다 검사 못함
                min=mid+1; //시간 더 많아야함
            }
            else {
                max=mid-1;
                ans=Math.min(ans, mid); //최솟값 갱신
            }
        }
        System.out.println(ans);
    }
}
