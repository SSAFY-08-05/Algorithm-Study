//package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

class Pair {
	int first;
	int second;
	
	public Pair(int first, int second) {
		this.first=first;
		this.second=second;
	}
	
	public int getFirst() {
		return this.first;
	}
	
	public int getSecond() {
		return this.second;
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb=new StringBuilder();
	    
	    int t=Integer.parseInt(br.readLine());
	    for(int T=0; T<t; T++) {
	    	List<Integer> list=new ArrayList<>();
	    	List<Pair> res=new ArrayList<>();
	    	String []str=br.readLine().split(" ");
	    	int n=Integer.parseInt(str[0]);
	    	int m=Integer.parseInt(str[1]);
	    	
	    	Queue<Pair> q=new ArrayDeque<>();
	    	
	    	str=br.readLine().split(" ");
	    	for(int i=0; i<n; i++) {
	    		int num=Integer.parseInt(str[i]);
	    		Pair pair=new Pair(i, num);
	    		list.add(num);
	    		q.add(pair);
	    	}
	    	
	    	Collections.sort(list); //중요도만 있는 list 오름차순 정렬
	    	int index=n-1;
	    	int max=list.get(index); //최댓값 저장
	    	
	    	while(!q.isEmpty()) {
	    		Pair p=q.peek();
	    		if(p.getSecond()>=max) { //맨 앞 문서가 중요도가 제일 크다면
	    			res.add(p); //인쇄 리스트에 추가
	    			index--; //최댓값 인쇄했으므로 전 index의 문서의 중요도가 최댓값이 됨
	    			if(index>=0) { //범위 체크
	    				max=list.get(index);
	    			}
	    			q.remove(); //출력 후 큐에서 삭제
	    		}
	    		
	    		else { //맨 앞 문서보다 중요도가 큰 문서가 큐에 있다면
	    			q.remove(); //앞에서 삭제 후
	    			q.add(p); //맨 뒤에 추가
	    		}
	    	}
	    	for(int i=0; i<n; i++) {
	    		Pair p=res.get(i);
	    		if(p.getFirst()==m) { //m번째 문서 찾기
	    			sb.append((i+1)+"\n"); //출력
	    			break;
	    		}
	    	}
	    }
	    System.out.println(sb.toString());
	}

}
