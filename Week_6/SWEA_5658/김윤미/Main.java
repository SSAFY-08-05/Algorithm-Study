import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW_5658_보물상자비밀번호_김윤미 {

    static int n, k;
    static char []arr;
    static Set<Long> set;
    static int count;

    static void rotate() {
        char c=arr[n-1];
        for(int i=n-1; i>=1; i--) {
            arr[i]=arr[i-1];
        }
        arr[0]=c;
    }

    static void calc() {
        count=n/4; //한 변에 있는 숫자의 개수
        for(int i=0; i<4; i++) {
            String str="";
            int start=i*count;
            int limit=start+count;
            for(int j=start; j<limit; j++) {
                str+=String.valueOf(arr[j]);
            }
            set.add(Long.parseLong(str, 16));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        StringBuilder sb=new StringBuilder();

        int T=Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            k=Integer.parseInt(st.nextToken());
            set=new TreeSet<>();
            arr=br.readLine().toCharArray();

            calc();
            for(int i=0; i<count; i++) {
                rotate();
                calc();
            }

            List<Long> list=new ArrayList<>(set);

            Collections.sort(list, Collections.reverseOrder());
            sb.append("#").append(t).append(" ").append(list.get(k-1)).append("\n");
        }
        System.out.println(sb.toString());
    }
}


/**
서영탁
전반적인 풀이는 저와 비슷한 것 같습니다.
calc() 함수에서 str을 붙일 때 저는 StringBuilder를 사용했습니다.
**/

/**
박성완
흐름은 비슷하나 로직은 좀 다르네요! 저는 실제로 돌리지 않고 substring을 이용해서 1회 탐색하였읍니다.
set를 한번에 list에 넣는 방법은 몰랐네요. 전 priorityqueue를 사용했어요 
**/
