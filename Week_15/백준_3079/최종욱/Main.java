import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static long[] time;
    static long max;
    

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    
    N = Integer.parseInt(st.nextToken());//심사대 갯수
    M = Integer.parseInt(st.nextToken());// 상근이 친구 수
    
    time = new long[N];//심사시간
    
    max = 0;
    for(int i = 0; i < N; i++) {
        time[i] = Integer.parseInt(br.readLine());
        max = Math.max(max, time[i]);
    }
    
    //이진탐색
    long bottom = 0;
    long top = max*M; //가장 오래걸리는 시간
    long result = Long.MAX_VALUE;
    
    while(bottom <= top) {
        long mid = (bottom + top)/2; //중간값
        
        long sum = 0;
        for(int i = 0; i < N; i++) { //중간값 시간에 심사할 수 있는 사람의 수
            sum += (mid/time[i]);
        }
        
        if(sum >= M) { //심사가능인원이 현재 인원보다 클 경우
            top = mid - 1;
        }else { //심사가능 인원이 현재 인원보다 작을 경우
            bottom = mid + 1;
        }
    }
    
    System.out.println(bottom);
    

}
}
