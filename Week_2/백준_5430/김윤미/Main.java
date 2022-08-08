//package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb=new StringBuilder();
	    
	    int t=Integer.parseInt(br.readLine());
	    for(int T=0; T<t; T++) { //테스트 케이스
	    	
		    char [] p=br.readLine().toCharArray(); //수행 함수 p
		    int n=Integer.parseInt(br.readLine());
		    String str=br.readLine();
		    str=str.substring(1, str.length()-1);
		    String []input=str.split(",");
		    Deque<Integer> deq=new ArrayDeque<Integer>();
		    for(int i=0; i<n; i++) {
		    	deq.add(Integer.parseInt(input[i]));
		    }
		    
		    boolean flag=true;
		    boolean removeFirst=true; //R이 나올 때마다 바꿔주는 boolean 변수
		    for(int i=0; i<p.length; i++) {
		    	char c=p[i];
		    	if(c=='R') removeFirst=!(removeFirst);
		    	
		    	if(c=='D') {
		    		if(deq.isEmpty()) {
		    			flag=false; //배열이 비어있는데 D가 입력되면 종료하고 error 출력
		    			break;
		    		}
		    		
		    		if(removeFirst) { //removeFirst=true면 앞에 원소 제거
		    			deq.removeFirst();
		    		}
		    		else deq.removeLast(); //아니면 뒤 원소부터 제거
		    	}
		    }
		    if(!flag) sb.append("error\n");
		    else {
		    	int num=0;
		    	sb.append("[");
		    	while(!deq.isEmpty()) {
		    		if(removeFirst) { //removeFirst=true면 앞 원소부터 출력
		    			num=deq.removeFirst();
		    		}
		    		else num=deq.removeLast(); //아니면 뒤 원소부터 출력
		    		
		    		if(!deq.isEmpty()) {
		    			sb.append(num+",");
		    		}
		    		else {
		    			sb.append(num); //출력 양식에 맞춰 출력
		    			break;
		    		}
		    	}
		    	sb.append("]\n");
		    }
	    }
	    System.out.println(sb.toString());
	}

}
