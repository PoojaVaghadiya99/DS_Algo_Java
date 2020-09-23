package Tree;

import java.util.LinkedList;
import java.util.Scanner;

public class BinaryTree {

    // Binary Tree Node
    private  class  Node {
        int data;
        Node left;
        Node right;

        Node(int data,Node left,Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    // Default Constructor
    BinaryTree() {
        Scanner sc = new Scanner(System.in);
        this.root = takeInput(sc,null,false);
    }

    // Take Binary Tree Data
    private Node takeInput(Scanner sc, Node parent, boolean isRightorLeft) {
        if(parent == null) {
            System.out.println("Enter the Data For Root Node : ");
        } else {
            if(isRightorLeft) {
                System.out.println("Enter the Data for Left Child Of " + parent.data + " : ");
            } else {
                System.out.println("Enter the Data for Right Child Of " + parent.data + " : ");
            }
        }

        int nodeData = sc.nextInt();
        Node node = new Node(nodeData,null,null);
        this.size++;

        boolean choice = false;
        System.out.println("Do You have Left Child of " + node.data);
        choice = sc.nextBoolean();

        if(choice) {
            node.left = takeInput(sc,node,true);
        }

        choice = false;
        System.out.println("Do You have Right Child of " + node.data);
        choice = sc.nextBoolean();

        if(choice) {
            node.right = takeInput(sc,node,false);
        }

        return node;
    }

    // Display Binary Tree Data
    public void display() {
        this.display(this.root);
    }

    private void display(Node node) {
        String str = "";

        if(node.left != null) {
            str = str + node.left.data + " => ";
        } else {
            str = str + "End => ";
        }

        str = str + node.data;

        if(node.right != null) {
            str = str + " <= " + node.right.data;
        } else {
            str = str + " <= End ";
        }

        System.out.println(str);

        if(node.left != null) {
            this.display(node.left);
        }

        if(node.right != null) {
            this.display(node.right);
        }
    }

    // Return Binary Tree Height
    public int height() {
        return this.height(this.root);
    }

    private int height(Node node) {

        if(node == null)
            return -1;

        int lHeight = this.height(node.left);
        int rHeight = this.height(node.right);

        int height = Math.max(lHeight,rHeight) + 1;

        return height;
    }

    // Pre-Order Binary Tree Traversal
    public void preOrder() {
        this.preOrder(this.root);
        System.out.println(" End");
    }

    private void preOrder(Node node) {
        if(node == null) {
            return;
        }

        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // In-Order Binary Tree Traversal
    public void inOrder() {
        this.inOrder(this.root);
        System.out.println(" End");
    }

    private void inOrder(Node node) {
        if(node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    // Post-Order Binary Tree Traversal
    public void postOrder() {
        this.postOrder(this.root);
        System.out.println(" End");
    }

    private void postOrder(Node node) {
        if(node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    // Level Order Binary Tree Traversal
    public  void  levelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(this.root);

        while(!queue.isEmpty()) {
            Node remove = queue.removeFirst();
            System.out.print(remove.data + " ");

            if(remove.left != null) {
                queue.addLast(remove.left);
            }

            if(remove.right != null) {
                queue.addLast(remove.right);
            }
        }
        System.out.println("End");
    }

    // Check Binary Tree is BST
    public boolean isBST() {
        return this.isBST(this.root,Integer.MIN_VALUE,Integer.MIN_VALUE);
    }

    private boolean isBST(Node node,int min,int max) {
        if(node == null)
            return true;

        if(node.data > max || node.data < min) {
            return false;
        } else if(!this.isBST(node.left,min, node.data)) {
            return false;
        } else if(!this.isBST(node.right,node.data,max)) {
            return false;
        } else {
            return true;
        }
    }

    // Sum Of All Leaf Node In Binary Tree
    public int sumLeafNode() {
        return this.sumLeafNode(this.root);
    }

    private int sumLeafNode(Node node) {
        if(node == null) {
            return 0;
        }

        if(node.left == null && node.right == null) {
            return node.data;
        }

        int lSum = sumLeafNode(node.left);
        int rSum = sumLeafNode(node.right);

        return lSum + rSum;
    }

    // Diameter Of Binary Tree
    public int diameter() {
        return this.diameter(this.root);
    }

    private int diameter(Node node) {
        if(node == null)
            return 0;

        int case1 = this.height(node.left) + this.height(node.right) + 2;
        int case2 = this.height(node.left);
        int case3 = this.diameter(node.right);

        int ans = Math.max(case1,Math.max(case2,case2));

        return ans;
    }

    public int diameterBtr() {
        return this.diameterBtr(this.root).diameter;
    }

    private DiaPair diameterBtr(Node node) {

        if(node == null) {
            DiaPair bp = new DiaPair(-1,0);
            return bp;
        }

        DiaPair lp = this.diameterBtr(node.left);
        DiaPair rp = this.diameterBtr(node.right);

        DiaPair mp = new DiaPair();
        mp.height = Math.max(lp.height , rp.height) + 1;
        mp.diameter = Math.max(lp.height + rp.height + 2 , Math.max(lp.diameter , rp.diameter));

        return mp;
    }

    public class DiaPair {
       int height;
       int diameter;

       DiaPair() { }

       DiaPair(int height,int diameter) {
           this.height = height;
           this.diameter = diameter;
       }
    }
}
