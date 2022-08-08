//package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb=new StringBuilder();
	    
	    char[] str=br.readLine().toCharArray();
	    Stack<Character> left=new Stack<Character>();
	    Stack<Character> right=new Stack<Character>();
	    //커서를 중심으로 커서 왼쪽 요소들 저장 스택,
	    //커서 오른쪽 요소들 저장 스택 생성
	    
	    for(char c:str) {
	    	left.add(c);
	    }
	    //명령어 수행 전 커서는 문장의 맨 뒤에 위치
	    //-> 모든 요소는 커서의 왼쪽에 있음 -> left 스택에 넣어주기
	    
	    int m=Integer.parseInt(br.readLine());
	    for(int M=0; M<m; M++) {
	    	String []input=br.readLine().split(" ");
	    	
	    	if(input[0].equals("P")) {
	    		char c=input[1].charAt(0);
	    		//커서 왼쪽에 추가
	    		left.add(c);
	    	}
	    	else if(input[0].equals("L")) {
	    		if(!left.isEmpty()) {
	    			char c=left.pop();
	    			right.add(c);
	    			//커서를 왼쪽으로 한 칸 옮김
	    			//왼쪽 요소가 커서의 오른쪽으로 감
	    			//left 스택 top빼서 right 스택에 추가
	    		}
	    	}
	    	else if(input[0].equals("D")) {
	    		if(!right.isEmpty()) {
	    			char c=right.pop();
	    			left.add(c);
	    			//커서를 오른쪽으로 한 칸 옮김
	    			//오른쪽 요소가 커서의 왼쪽으로 감
	    			//right 스택 top빼서 left 스택에 추가
	    		}
	    	}
	    	else if(input[0].equals("B")) {
	    		if(!left.isEmpty()) {
	    			left.pop();
	    			//커서의 왼쪽 요소 제거
	    		}
	    	}
	    }
	    
	    //출력
	    while(!left.isEmpty()) {
	    	sb.append(left.pop());
	    }
	    
	    sb=sb.reverse();
	    
	    while(!right.isEmpty()) {
	    	sb.append(right.pop());
	    }
	    
	    System.out.println(sb);
	}

}
