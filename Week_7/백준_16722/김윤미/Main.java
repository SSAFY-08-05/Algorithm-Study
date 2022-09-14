import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static class Picture {
        String shape;
        String color;
        String back;

        public Picture(String shape, String color, String back) {
            this.shape = shape;
            this.color = color;
            this.back = back;
        }
    }

    static Picture[] arr;
    static int[][][]isSum;
    static int []input;
    static int[] res;
    static int n;
    static int []shout;
    static int score=0;
    static boolean isEnd=false;

    static boolean np(int []numbers) {
        int i=8;
        while(i>0 && numbers[i-1]>=numbers[i]) --i;

        if(i==0) return false;

        int j=8;
        while(numbers[i-1]>=numbers[j]) --j;
        swap(numbers, i-1, j);

        int k=8;
        while(i<k) swap(numbers, i++, k--);

        return true;
    }

    static void swap(int []numbers, int i, int j) {
        int tmp=numbers[i];
        numbers[i]=numbers[j];
        numbers[j]=tmp;
    }

    static boolean checkSum() {
        Set<String> shapes=new HashSet<>();
        Set<String> colors=new HashSet<>();
        Set<String> backs=new HashSet<>();

        for(int i=0; i<3; i++) {
            shapes.add(arr[res[i]].shape);
            colors.add(arr[res[i]].color);
            backs.add(arr[res[i]].back);
        }

        if(shapes.size()==2 || colors.size()==2 || backs.size()==2) {
            return false;
        }
        else return true;
    }

    static void shoutSum() {
        if(isSum[shout[0]][shout[1]][shout[2]]==1) {
            score+=1;
            isSum[shout[0]][shout[1]][shout[2]]=2;
        }
        else {
            score-=1;
        }
    }

    static void shoutEnd() {
        boolean flag=true;
        for(int i=1; i<=9; i++) {
            for(int j=1; j<=9; j++) {
                for(int k=1; k<=9; k++) {
                    if(isSum[i][j][k]==1) {
                        flag=false;
                        break;
                    }
                }
                if(!flag) break;
            }
            if(!flag) break;
        }

        if(flag && !isEnd) {
            score+=3;
            isEnd=true;
        }
        else {
            score-=1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;

        arr=new Picture[10];
        input=new int[10];
        isSum=new int[10][10][10];
        res=new int[3];
        shout=new int[3];

        for(int i=6; i<9; i++) {
            input[i]=1;
        }

        for(int i=1; i<=9; i++) {
            st=new StringTokenizer(br.readLine());
            String shape=st.nextToken();
            String color=st.nextToken();
            String back=st.nextToken();

            arr[i]=new Picture(shape, color, back);
        }

        do{
            int idx=0;
            for(int i=0; i<9; i++) {
                if(input[i]==1) {
                    res[idx++]=i+1; //arr의 인덱스 저장
                }
            }

            if(checkSum()) {
                isSum[res[0]][res[1]][res[2]] = 1;
            }

        }while(np(input));

        n=Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            st=new StringTokenizer(br.readLine());
            String com=st.nextToken();
            if(com.equals("H")) { //합
                shout[0]=Integer.parseInt(st.nextToken());
                shout[1]=Integer.parseInt(st.nextToken());
                shout[2]=Integer.parseInt(st.nextToken());

                Arrays.sort(shout);
                shoutSum();
            }
            else if(com.equals("G")) { //결
                shoutEnd();
            }
        }

        System.out.println(score);
    }
}
