import java.io.*;
import java.util.*;

public class Solution {

    public static class Customer{
        int num;
        int arrivalTime;
        int useReceptionNum;
        int receptionEndTime;

        int useRepairNum;
        int repairEndTime;

        public Customer(int num, int arrivalTime) {
            this.num = num;
            this.arrivalTime = arrivalTime;
        }
    }

    public static int n, m, k, a, b, ans;
    public static int[] receptionTime, repairTime;
    public static Queue<Customer> arrivalCustomer;
    public static PriorityQueue<Integer> emptyReception, emptyRepair;
    public static PriorityQueue<Customer> readyReception, readyRepair, useReception, useRepair;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= t; tc++) {
            ans = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            receptionTime = new int[n+1];  // 접수처 걸리는 시간
            repairTime = new int[m+1];  // 정비소 걸리는 시간
            arrivalCustomer = new ArrayDeque<>();  // 고객 큐
            emptyReception = new PriorityQueue<>();  // 비어있는 접수처
            emptyRepair = new PriorityQueue<>();  // 비어있는 정비소

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                receptionTime[i] = Integer.parseInt(st.nextToken());
                emptyReception.add(i);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= m; i++){
                repairTime[i] = Integer.parseInt(st.nextToken());
                emptyRepair.add(i);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= k; i++){
                arrivalCustomer.add(new Customer(i, Integer.parseInt(st.nextToken())));
            }

            readyReception = new PriorityQueue<>(Comparator.comparingInt(c -> c.num));  // 접수처 대기 우선순위 큐
            readyRepair = new PriorityQueue<>((c1, c2) -> {  // 정비소 대기 우선순위 큐
                if(c1.receptionEndTime == c2.receptionEndTime) return c1.useReceptionNum - c2.useReceptionNum;
                return c1.receptionEndTime - c2.receptionEndTime;
            });
            useReception = new PriorityQueue<>((c1, c2) -> {  // 접수처 사용 우선순위 큐
                if (c1.receptionEndTime == c2.receptionEndTime) return c1.useReceptionNum - c2.useReceptionNum;
                return c1.receptionEndTime - c2.receptionEndTime;
            });
            useRepair = new PriorityQueue<>(Comparator.comparingInt(c -> c.repairEndTime));  // 정비소 사용 우선순위 큐

            goCarCenter();

            sb.append("#").append(tc).append(" ").append(ans == 0 ? -1 : ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void goCarCenter(){
        int remainCustomer = k;  // 남아있는 고객 수
        int time = 0;

        while(remainCustomer > 0){

            // 정비까지 마쳤을 때
            while(!useRepair.isEmpty() && useRepair.peek().repairEndTime <= time){
                Customer customer = useRepair.poll();
                if(customer.useReceptionNum == a && customer.useRepairNum == b) ans += customer.num;
                remainCustomer--;  // 고객 수 -1
                emptyRepair.add(customer.useRepairNum);
            }

            // 정비소를 먼저 기다리고 있는 고객이 우선
            while(!readyRepair.isEmpty() && readyRepair.peek().receptionEndTime <= time){
                if(emptyRepair.isEmpty()) break;

                Customer customer = readyRepair.poll();
                int repairNum = emptyRepair.poll();
                customer.useRepairNum = repairNum;
                customer.repairEndTime = time + repairTime[repairNum];
                useRepair.add(customer);
            }

            // 접수가 끝난 고객
            while(!useReception.isEmpty() && useReception.peek().receptionEndTime <= time){
                if(emptyRepair.isEmpty()){
                    emptyReception.add(useReception.peek().useReceptionNum);
                    readyRepair.add(useReception.poll());
                }
                else{
                    Customer customer = useReception.poll();
                    int repairNum = emptyRepair.poll();
                    customer.useRepairNum = repairNum;
                    customer.repairEndTime = time + repairTime[repairNum];
                    useRepair.add(customer);

                    emptyReception.add(customer.useReceptionNum);
                }
            }

            // 접수를 대기하고 있는 고객
            while(!readyReception.isEmpty()){
                if(emptyReception.isEmpty()) break;

                Customer customer = readyReception.poll();
                int receptionNum = emptyReception.poll();
                customer.useReceptionNum = receptionNum;
                customer.receptionEndTime = time + receptionTime[receptionNum];
                useReception.add(customer);
            }

            // 막 접수처에 도착한 인원
            while(!arrivalCustomer.isEmpty() && arrivalCustomer.peek().arrivalTime <= time){
                if(emptyReception.isEmpty()) {
                    readyReception.add(arrivalCustomer.poll());
                }
                else{
                    Customer customer = arrivalCustomer.poll();
                    int receptionNum = emptyReception.poll();
                    customer.useReceptionNum = receptionNum;
                    customer.receptionEndTime = time + receptionTime[receptionNum];
                    useReception.add(customer);
                }
            }

            time++;
        }
    }

}
