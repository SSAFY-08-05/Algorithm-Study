//package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb=new StringBuilder();
	    
	    LinkedList<Integer> d=new LinkedList<>();
		int mid;
	    
	    String []input=br.readLine().split(" ");
	    int n=Integer.parseInt(input[0]); //큐 크기
	    int m=Integer.parseInt(input[1]); //뽑아내려는 수 개수
	    for(int i=1; i<=n; i++) {
	    	d.add(i);
	    }
	    
	    mid=n/2;
	    
	    input=br.readLine().split(" ");
	    int sum=0;
	    for(int M=0; M<m; M++) {
	    	int num=Integer.parseInt(input[M]);
	    	int idx=d.indexOf(num); //num이 있는 index 찾기
	    	while(true) {
		    	if(idx<=mid) { //왼쪽에 있을 때
		    		if(idx==0) { //num이 맨 앞에 있으면 제거
		    			d.removeFirst();
		    			n--; //덱에 있는 원소 개수 조절
		    			mid=n/2; //중간 index 조절
		    			break;
		    		}
		    		d.addLast(d.removeFirst()); //앞에서 빼고 뒤에 넣기
		    		idx--; //num의 인덱스 조절
		    		if(idx<0) idx+=n; //index 범위 체크
		    		sum++; //연산 횟수 증가시켜주기
		    	}
		    	else { //오른쪽에 있을 때
		    		d.addFirst(d.removeLast()); //뒤에서 빼고 앞에 넣기
		    		idx++; //num의 인덱스 조절
		    		if(idx>=n) idx-=n; //index 범위 체크
		    		sum++; //연산 횟수 증가시켜주기
		    	}
	    	}
	    }
	    System.out.println(sum);
	    
	}

}
