//package week3.bj_5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 백준 5639 이진 검색 트리
public class Main {
	static Map<Integer,Node> map;
	static int root;
	static StringBuilder sb;
	static class Node{
		int data,left,right;

		public Node(int data) {
			super();
			this.data = data;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		sb = new StringBuilder();
		map = new HashMap<>();
		root = Integer.parseInt(br.readLine());
		Node rootNode = new Node(root);
		map.put(rootNode.data, rootNode);
		
		while(true) {
			input = br.readLine();
			if(input==null || input.equals("")) break;
			Node node = new Node(Integer.parseInt(input));
			map.put(node.data, node);
			addNode(rootNode,node);
		}
		
		postOrder(map.get(root));
		System.out.println(sb);
		br.close();
	}
	static void addNode(Node current, Node target) {
		if(current.data > target.data) {
			if(current.left == 0) {
				current.left = target.data;
			}else {
				addNode(map.get(current.left),target);
			}
		}else {
			if(current.right == 0) {
				current.right = target.data;
			}else {
				addNode(map.get(current.right),target);
			}
		}
	}
	static void postOrder(Node node) {		
		if(node.left != 0) postOrder(map.get(node.left));
		if(node.right != 0) postOrder(map.get(node.right));
		sb.append(node.data + "\n");
		
	}
}
