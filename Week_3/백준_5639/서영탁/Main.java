import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }

        public void add(Node node){
            if(this.num > node.num){
                if(this.left == null) this.left = node;
                else this.left.add(node);
            } else{
                if(this.right == null) this.right = node;
                else this.right.add(node);
            }
        }

        public void postOrder(){
            if(this.left != null) this.left.postOrder();
            if(this.right != null) this.right.postOrder();
            System.out.println(this.num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num = Integer.parseInt(br.readLine());
        Node root = new Node(num);

        String str;
        while((str = br.readLine()) != null){
            num = Integer.parseInt(str);
            root.add(new Node(num));
        }

        root.postOrder();
    }

}
