//package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb=new StringBuilder();
	    
	    int n=Integer.parseInt(br.readLine());
	    List<Queue<String>> list=new ArrayList<>();
	    
	    for(int i=0; i<n; i++) { //앵무새 수만큼 Queue 생성
	    	Queue<String> q=new ArrayDeque<String>();
	    	String []str=br.readLine().split(" ");
	    	for(String s:str) {
	    		q.add(s); //앵무새 Queue에 문장의 단어들 넣어주기
	    	}
	    	list.add(q);
	    }
	    
	    String []arr=br.readLine().split(" ");
	    boolean flag=false;
	    for(String s:arr) { //받아 적은 문장의 왼쪽 단어들부터 비교
	    	flag=false;
	    	for(Queue<String> q:list) {
	    		if(!q.isEmpty()) {
	    			//q들의 제일 앞 원소 중 같은 단어가 있으면 제거
	    			//없으면 불가능한 문장임
	    			if(q.peek().equals(s)) { 
	    				q.poll();
	    				flag=true;
	    				break;
	    			}
	    		}
	    	}
	    	if(!flag) break;
	    }
	    
	    for(Queue<String> q:list) { //비어있지 않은 큐가 있으면 잘못된 문장
	    	if(!q.isEmpty()) {
	    		flag=false;
	    		break;
	    	}
	    }
	    
	    if(!flag) System.out.println("Impossible");
	    else System.out.println("Possible");
	}

}
