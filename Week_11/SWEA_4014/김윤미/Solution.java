import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n, x;
    static int [][]arr;
    static boolean[][] r1Placed; //오른쪽 방향에 활주로 건설여부 표시
    static boolean[][] r2Placed; //왼쪽 방향에 활주로 건설여부 표시
    static boolean[][] c1Placed; //윗쪽 방향에 활주로 건설여부 표시
    static boolean[][] c2Placed; //아랫쪽 방향에 활주로 건설여부 표시
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        for(int t=1; t<=T; t++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            x=Integer.parseInt(st.nextToken());

            arr=new int[n][n];
            r1Placed=new boolean[n][n];
            r2Placed=new boolean[n][n];
            c1Placed=new boolean[n][n];
            c2Placed=new boolean[n][n];

            for(int i=0; i<n; i++) {
                st=new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<n; i++) { //가로줄 먼저 보기
                for(int j=0; j<n-1; j++) { // -> 방향으로 설치할 수 있는지 살펴보기
                    if(arr[i][j]==arr[i][j+1]+1) { //만약 왼쪽 칸 값 == 오른쪽칸 값 + 1 이면 여기서 부터 활주로 건설 가능 (우측으로)
                        boolean isPossible=true;
                        for(int k=j+1; k<=j+x; k++) { //활주로의 길이는 x 만큼
                            
                            //범위 체크, 값이 1 차이가 아니거나 이미 가로방향에 건설되었으면 
                            if(k>=n || arr[i][k]!=arr[i][j]-1 || r1Placed[i][k] || r2Placed[i][k]) { 
                                isPossible=false; //세울 수 없음..
                                break;
                            }
                        }

                        if(isPossible) { //놓을 수 있을 때
                            for(int k=j+1; k<=j+x; k++) { //범위 그대로
                                r1Placed[i][k]=true; //경사로 놓기
                            }
                        }
                    }
                }

                for(int j=n-1; j>0; j--) { // <- 방향으로 설치할 수 있는지 검사
                    if(arr[i][j]==arr[i][j-1]+1) { //좌측으로 건설할 수 있는지
                        boolean isPossible=true;
                        for(int k=j-1; k>=j-x; k--) { //활주로의 길이는 x
                            
                            if(k<0 || arr[i][k]!=arr[i][j]-1 || r1Placed[i][k] || r2Placed[i][k]) {
                                isPossible=false;
                                break;
                            }
                        }

                        if(isPossible) { //놓을 수 있을 때
                            for(int k=j-1; k>=j-x; k--) {
                                r2Placed[i][k]=true; //경사로 놓기
                            }
                        }
                    }
                }
            }

            //////////////////////////////////////////

            for(int j=0; j<n; j++) { //세로줄 보기
                for(int i=n-1; i>0; i--) { // ↑ 방향으로 설치할 수 있는지 살펴보기
                    if(arr[i][j]==arr[i-1][j]+1) { //윗칸이 아랫칸보다 1 작을 때 위로 설치 가능
                        boolean isPossible=true;
                        for(int k=i-1; k>=i-x; k--) {
                            if(k<0 || arr[k][j]!=arr[i][j]-1 || c1Placed[k][j] || c2Placed[k][j]) {
                                isPossible=false; //경사로를 놓을 수 있는지
                                break;
                            }
                        }

                        if(isPossible) { //놓을 수 있을 때
                            for(int k=i-1; k>=i-x; k--) {
                                c1Placed[k][j]=true; //경사로 놓기
                            }
                        }
                    }
                }


                for(int i=0; i<n-1; i++) { // 아래 방향으로 설치할 수 있는지 검사
                    if(arr[i][j]==arr[i+1][j]+1) { //아랫칸이 윗칸보다 1 작을 때
                        boolean isPossible=true;
                        for(int k=i+1; k<=i+x; k++) {
                            if(k>=n || arr[k][j]!=arr[i][j]-1 || c1Placed[k][j] || c2Placed[k][j]) {
                                isPossible=false; //경사로를 놓을 수 있는지
                                break;
                            }
                        }

                        if(isPossible) { //놓을 수 있을 때
                            for(int k=i+1; k<=i+x; k++) {
                                c2Placed[k][j]=true; //경사로 놓기
                            }
                        }
                    }
                }
            }

            //경사로를 놓은 후 지나갈 수 있는지 검사
            
            int rCnt=0; //가능한 행의 수
            for(int i=0; i<n; i++) { //가로줄 보기
                boolean isPossible=true;
                for(int j=0; j<n-1; j++) {
                    if(arr[i][j]==arr[i][j+1]) continue; //값이 같으면 넘어감~

                    if(arr[i][j]<arr[i][j+1]) { //왼쪽 값 < 오른쪽 값이면 왼쪽에 경사로가 있어야 함
                        if(!r2Placed[i][j]) { //그렇지만 왼쪽에 경사로가 없다면
                            isPossible=false; //불가능한 행
                            break;
                        }
                    }

                    else if(arr[i][j]>arr[i][j+1]) { //왼쪽값 > 오른쪽 값이면 오른쪽에 경사로가 있어야 함
                        if(!r1Placed[i][j+1]) { //그렇지만 오른쪽에 경사로가 없다면
                            isPossible=false; //불가
                            break;
                        }
                    }
                }
                if(isPossible) rCnt++; //가능한 행의 개수 증가시키기
            }

            int cCnt=0; //가능한 열의 수
            for(int j=0; j<n; j++) { //세로줄 보기
                boolean isPossible=true;
                for(int i=0; i<n-1; i++) {
                    if(arr[i][j]==arr[i+1][j]) continue; //값이 같으면 지나감

                    if(arr[i][j]<arr[i+1][j]) { //윗쪽 값 < 아랫쪽 값이면 위에 경사로가 있어야 함
                        if(!c1Placed[i][j]) { //그렇지만 위에 경사로가 없다면
                            isPossible=false; //불가능한 열
                            break;
                        }
                    }

                    else if(arr[i][j]>arr[i+1][j]) { //윗쪽 값 > 아랫쪽 값이면 아랫쪽에 경사로가 있어야 함
                        if(!c2Placed[i+1][j]) { //그렇지만 아래에 경사로가 없다면..
                            isPossible=false; //불가
                            break;
                        }
                    }
                }
                if(isPossible) cCnt++; //가능한 열의 개수 증가시키기
            }

            sb.append("#").append(t).append(" ").append(rCnt+cCnt).append("\n"); //둘이 더한 게 답
        }
        System.out.println(sb.toString());
    }
}
