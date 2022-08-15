
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * BJ_5639_이진검색트리_김윤미
 */
class Tree {
	public Node root;
	StringBuilder sb=new StringBuilder();
	
	public class Node {
		int data;
		Node left;
		Node right;
		
		Node(int data) {
			this.data=data;
		}
	}
	
	Node searchNode(Node node, int key) {
		if(node==null) {
			return null;
		}
		
		if(key==node.data) return node;
		else if(key<node.data) return searchNode(node.left, key);
		else return searchNode(node.right, key);
	}
	
	Node addNode(Node node, int key) { //node에 key 삽입
		if(node==null) {
			return new Node(key);
		}
		
		if(key<node.data) {
			node.left=addNode(node.left, key);
		}
		else if(key>node.data) {
			node.right=addNode(node.right, key);
		}
		return node;
	}
	
	void postOrder(Node node) { //후위순회
		if(node!=null) {
			if(node.left!=null) postOrder(node.left);
			if(node.right!=null) postOrder(node.right);
			sb.append(node.data).append("\n");
		}
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        String input="";
        Tree tree=new Tree();
        input=br.readLine();
        
        int num=Integer.parseInt(input);
        tree.root=tree.addNode(tree.root, num);
        
        while((input=br.readLine())!=null && !input.equals("")) {
        	num=Integer.parseInt(input);
        	if(tree.searchNode(tree.root, num)==null) {
        		tree.addNode(tree.root, num);
        	}
        }
        
        tree.postOrder(tree.root);
        System.out.println(tree.sb.toString());
	}

}
