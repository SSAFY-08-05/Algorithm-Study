import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int []arr;
    static char[]res;
    static int max, min;
    static int[]input;

    static void calc() {
        int index=0;
        int ans=arr[index++];

        for(int i=0; i<n-1; i++) {
            char op=res[i];
            int num2=arr[index++];

            if(op=='+') ans+=num2;
            else if(op=='-') ans-=num2;
            else if(op=='*') ans*=num2;
            else {
                if(num2==0) return;
                ans/=num2;
            }
        }

        max=Math.max(max, ans);
        min=Math.min(min, ans);
    }

    static boolean np(int []input) {
        int i=n-2;
        while(i>0 && input[i-1]>=input[i]) --i;

        if(i==0) return false;

        int j=n-2;
        while(input[i-1]>=input[j]) --j;

        swap(input, i-1, j);

        int k=n-2;
        while(i<k) swap(input, i++, k--);

        return true;
    }

    static void swap(int []input, int i, int j) {
        int tmp=input[i];
        input[i]=input[j];
        input[j]=tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        for(int t=1; t<=T; t++) {
            n=Integer.parseInt(br.readLine());

            arr=new int[n];
            res=new char[n-1];
            input=new int[n-1];

            max=Integer.MIN_VALUE;
            min=Integer.MAX_VALUE;

            StringTokenizer st=new StringTokenizer(br.readLine());
            int index=0;
            for(int i=0; i<4; i++) {
                int cnt=Integer.parseInt(st.nextToken());
                for(int k=0; k<cnt; k++) {
                    input[index++]=i;
                }
            }

            st=new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                arr[i]=Integer.parseInt(st.nextToken());
            }

            do{
                for(int i=0; i<n-1; i++) {
                    if(input[i]==0) res[i]='+';
                    else if(input[i]==1) res[i]='-';
                    else if(input[i]==2) res[i]='*';
                    else res[i]='/';
                }
                calc();
            }while(np(input));

            sb.append("#").append(t).append(" ").append(max-min).append("\n");
        }
        System.out.println(sb.toString());
    }
}
