
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair { //위치 좌표 저장
    int x;
    int y;

    Pair(int x, int y) {
        this.x=x;
        this.y=y;
    }
}

class Point{ //시작점과 도착점 저장
    Pair st;
    Pair en;

    Point(Pair st, Pair en) {
        this.st=st;
        this.en=en;
    }
}

public class Main {
    static int n; //배열 크기
    static int m; //태울 승객 수
    static int fuel; //초기 연료 양
    static Pair loc; //택시 현재 위치
    static Point[] points; //승객의 출발/도착 위치 저장 배열
    static int [][]arr; //지도 배열
    static int dx[]=new int[] {-1, 0, 1, 0};
    static int dy[]=new int[] {0, 1, 0, -1}; //위 오 아래 왼
    static int minDis; //최단 거리
    static int [][]dis; //bfs 시 거리 저장 배열
    static boolean[]end; //승객의 목적지 도착 여부

    static int choosePassenger() { //제일 가까운 승객 번호 반환
        int min=Integer.MAX_VALUE;
        int num=-1; //최단 거리인 승객의 번호
        boolean flag=false; //승객 찾을 수 있나 여부
        for(int i=1; i<=m; i++) {
        	if(end[i]) continue; //이미 이동했으면 다음 승객보기
        	
        	minDis=bfs(loc.x, loc.y, points[i].st.x, points[i].st.y);
        	if(minDis==-1) continue; //이동할 수 없으면 다음 승객보기
        	flag=true; //이동할 수 있으면 승객 고를 수 있음 
            if(minDis<min) { //최단 거리 갱신
                num=i;
                min=minDis;
            }
            else if(minDis==min) { //최단 거리가 같다면
                if(points[i].st.x<points[num].st.x) { //행 번호가 가장 작은 승객을,
                    num=i;
                    min=minDis;
                }
                else if(points[i].st.x==points[num].st.x) { //행 번호가 같은 승객도 여러 명이면
                    if(points[i].st.y<points[num].st.y) { //열 번호가 가장 작은 승객을 고른다.
                        num=i;
                        min=minDis;
                    }
                }
            }
        }
        minDis=min; //전역변수에 최솟값 할당
        if(!flag) num=-1;
        return num;
    }
    
    static int bfs(int sx, int sy, int ex, int ey) { //시작점 -> 도착점 최단거리 구하기
    	dis=new int[n+1][n+1];
    	Queue<Pair> q=new ArrayDeque<>(); //bfs 사용
    	q.add(new Pair(sx, sy));
    	
    	while(!q.isEmpty()) {
    		Pair p=q.poll();
    		if(p.x==ex && p.y==ey) {
    			return dis[ex][ey];
    		}
    		
    		for(int d=0; d<4; d++) {
    			int nx=p.x+dx[d];
    			int ny=p.y+dy[d];
    			
    			if(nx<1 || nx>n || ny<1 || ny>n) continue; //범위 체크
                if(arr[nx][ny]==1) continue; //벽은 못 지나감
                if(dis[nx][ny]>0) continue; //이미 지나간 곳이면 패스
                dis[nx][ny]=dis[p.x][p.y]+1;  
                q.add(new Pair(nx, ny));
    		}
    	}
    	return -1;
    }

    static boolean work() { //일하자
    	boolean isEnd=false;
        while(fuel>0) { //연료가 바닥나면 끝
            int num=choosePassenger(); //제일 가까운 승객 고르기
            if(num==-1) return false; //승객 못 찾으면 못 감. 업무 끝
            if(fuel-minDis<=0) break; //연료가 바닥나면 이동 실패, 그 날의 업무 끝남
            
            fuel-=minDis; //승객한테 가는 도중 쓴 연료
            loc.x=points[num].st.x;
            loc.y=points[num].st.y; //택시를 승객이 있던 위치로 이동시키기
            
            minDis=bfs(loc.x, loc.y, points[num].en.x, points[num].en.y);
            if(minDis==-1) return false; //목적지까지 갈 수 없을 때 false 반환
            
            if(fuel-minDis<0) break;
            //목적지로 이동시킨 동시에 연료가 바닥나는 경우에는 실패 아님!!
            
            fuel-=minDis; //연료 소모
            fuel+=(minDis*2); //성공적으로 이동하면 두배 충전
            loc.x=points[num].en.x; //택시를 승객의 목적지였던 곳으로 이동시키기
            loc.y=points[num].en.y;
            
            end[num]=true; //승객 이동 성공
            
            isEnd=true;
            for(int i=1; i<=m; i++) {
            	if(!end[i]) {
            		isEnd=false; //승객 남아있으면 계속함
            		break;
            	}
            }
            
            if(isEnd) break; //다 이동시켰으면 끝
        }
        return isEnd; //일 성공적으로 끝남? 반환
    }

	public static void main(String[] args) throws IOException {
	    // TODO Auto-generated method stub
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st=new StringTokenizer(br.readLine());
	    n=Integer.parseInt(st.nextToken());
	    m=Integer.parseInt(st.nextToken());
	    fuel=Integer.parseInt(st.nextToken());
	    
	    arr=new int[n+1][n+1];
	    points=new Point[m+1];
	    end=new boolean[m+1];
	    
	    for(int i=1; i<=n; i++) {
	        st=new StringTokenizer(br.readLine());
	        for(int j=1; j<=n; j++) {
	            arr[i][j]=Integer.parseInt(st.nextToken());
	        }
	    }
	    st=new StringTokenizer(br.readLine());
	    loc=new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    
	    for(int i=1; i<=m; i++) {
	        st=new StringTokenizer(br.readLine());
	        int sx=Integer.parseInt(st.nextToken());
	        int sy=Integer.parseInt(st.nextToken());
	        int ex=Integer.parseInt(st.nextToken());
	        int ey=Integer.parseInt(st.nextToken());
	        
	        points[i]=new Point(new Pair(sx, sy), new Pair(ex, ey));
	    }    
	    
	    if(!work()) System.out.println(-1);
	    else System.out.println(fuel);
	}
}
