//package week3.bj_5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 백준 5639 이진 검색 트리
public class Main {
	//static Map<Integer,Node> map;
	static int root;
	static StringBuilder sb;
	static class Node{
		int data;
		Node left,right;

		public Node(int data) {
			super();
			this.data = data;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		sb = new StringBuilder();
		//map = new HashMap<>();
		root = Integer.parseInt(br.readLine());
		Node rootNode = new Node(root);
		//map.put(rootNode.data, rootNode);
		
		while(true) {
			input = br.readLine();
			if(input==null || input.equals("")) break;
			Node node = new Node(Integer.parseInt(input));
			//map.put(node.data, node);
			addNode(rootNode,node);
		}
		
		postOrder(rootNode);
		System.out.println(sb);
		br.close();
	}
	static void addNode(Node current, Node target) {
		if(current.data > target.data) {
			if(current.left == null) {
				current.left = target;
			}else {
				addNode(current.left,target);
			}
		}else {
			if(current.right == null) {
				current.right = target;
			}else {
				addNode(current.right,target);
			}
		}
	}
	static void postOrder(Node node) {		
		if(node.left != null) postOrder(node.left);
		if(node.right != null) postOrder(node.right);
		sb.append(node.data + "\n");
		
	}
}
