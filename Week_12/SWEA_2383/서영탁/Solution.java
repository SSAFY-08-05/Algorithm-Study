import java.io.*;
import java.util.*;

public class Solution {

    public static class Person implements Comparable<Person>{
        int x, y;
        int stair;
        int dist;

        public Person(int x, int y, int stair) {
            this.x = x;
            this.y = y;
            this.stair = stair;
        }

        public void calDist() {
            this.dist = Math.abs(this.x - stairs[stair].x) + Math.abs(this.y - stairs[stair].y);
        }

        @Override
        public int compareTo(Person o) {
            return this.dist - o.dist;
        }

    }

    public static class Stair{
        int x, y;
        int time;

        public Stair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int n, ans;
    public static int[][] map;

    public static ArrayList<Person> people;
    public static Stair[] stairs;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= t; tc++) {
            ans = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            people = new ArrayList<>();  // 사람 위치 저장
            stairs = new Stair[2];  // 계단 위치 저장

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 0) continue;
                    else if(map[i][j] == 1) people.add(new Person(i, j, 0));
                    else {
                        if(stairs[0] == null) stairs[0] = new Stair(i, j, map[i][j]);
                        else stairs[1] = new Stair(i, j, map[i][j]);
                    }
                }
            }

            dfs(0);

            sb.append("#").append(tc).append(" ").append(ans+1).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx) {
        if(idx == people.size()) {
            calTime();
            return;
        }

        for(int i = 0; i < 2; i++) {
            people.get(idx).stair = i;  // 사람마다 계단 선택
            people.get(idx).calDist();  // 그 계단과의 거리 계산
            dfs(idx+1);
        }
    }

    public static void calTime() {
        PriorityQueue<Person> pq1 = new PriorityQueue<>();  // 0번 계단을 사용할 사람 우선순위큐
        PriorityQueue<Person> pq2 = new PriorityQueue<>();  // 1번 계단을 사용할 사람 우선순위큐

        for(int i = 0; i < people.size(); i++) {  // 우선순위큐에 추가
            Person person = people.get(i);

            if(person.stair == 0) pq1.offer(person);
            else pq2.offer(person);
        }

        Queue<Integer> useStair1 = new ArrayDeque<>();  // 0번 계단을 사용하고 있는 사람 큐
        Queue<Integer> useStair2 = new ArrayDeque<>();  // 1번 계단을 사용하고 있는 사람 큐

        int time = 0;
        while(!pq1.isEmpty() || !pq2.isEmpty()) {  // 우선순위큐가 다 빌때까지

            while(!pq1.isEmpty() && pq1.peek().dist <= time) {  // 0번 계단을 사용할 사람이 계단에 도착했으면
                while(!useStair1.isEmpty() && useStair1.peek() <= time) {  // 먼저 계단사용하고 있던 사람중에 나갈 사람은 빼줌
                    useStair1.poll();
                }

                if(useStair1.size() < 3) {  // 아직 3명이 사용하고 있지 않다면
                    useStair1.offer((stairs[0].time + time));  // 추가
                    pq1.poll();  // 우선순위큐에서는 삭제
                }else break;  // 이미 3명이 사용하고 있다면 break
            }

            while(!pq2.isEmpty() && pq2.peek().dist <= time) {
                while(!useStair2.isEmpty() && useStair2.peek() <= time) {
                    useStair2.poll();
                }

                if(useStair2.size() < 3) {
                    useStair2.offer(stairs[1].time + time);
                    pq2.poll();
                }else break;
            }

            time++;

            if(time >= ans) return;
        }

        // 우선순위큐에서 다 뽑았어도 아직 계단을 사용하고 있는 사람이 있으니
        // 계단을 다 내려온 시간도 계산
        while(!useStair1.isEmpty()) {
            time = Math.max(time, useStair1.poll());
        }

        while(!useStair2.isEmpty()) {
            time = Math.max(time, useStair2.poll());
        }

        ans = Math.min(ans, time);
    }

}
