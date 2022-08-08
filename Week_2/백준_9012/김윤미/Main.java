//package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb=new StringBuilder();
	    
	    int n=Integer.parseInt(br.readLine());
	    
	    for(int N=0; N<n; N++) {
	    	char []arr=br.readLine().toCharArray();
	    	Stack<Character> st=new Stack<>();
	    	boolean flag=true;
	    	for(char c:arr) {
	    		if(c=='(') st.add(c); //여는 괄호면 스택에 추가
	    		else { //닫는 괄호일 때
	    			//스택이 비어있거나 맨 위가 여는 괄호가 아니면 VPS(올바른 괄호 문자열)가 아니다.
	    			if(st.empty() || st.peek()!='(') { 
	    				flag=false;
	    				break;
	    			}
	    			else st.pop(); //스택이 비어있지 않고 맨 위가 닫는 괄호면 pop
	    		}
	    	}
	    	
	    	if(!st.empty()) flag=false; //스택이 비어있지 않으면 VPS가 아니다.
	    	
	    	if(!flag) { //출력
	    		sb.append("NO\n");
	    	}
	    	else {
	    		sb.append("YES\n");
	    	}
	    }
	    System.out.println(sb.toString());
	}

}
