package Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class GenericTree {

    // Generic Tree Node
    private class Node {

        int data;
        ArrayList<Node> children;

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    private Node root;
    private int size;

    // Default Constructor
    GenericTree() {
        Scanner sc = new Scanner(System.in);
        this.root = takeInput(sc,null,0);
    }

    // Take Generic Tree Data
    private Node takeInput(Scanner sc,Node parent,int ithchild) {

        if(parent == null) {
            System.out.println("Enter The Data For Root Node : ");
        } else {
            System.out.println("Enter The Data For " + ithchild + "th Child Of " + parent.data + " : ");
        }

        int nodeData = sc.nextInt();
        Node node = new Node(nodeData);
        this.size++;

        System.out.println("Enter The Number Of Children for " + nodeData + " : ");
        int children = sc.nextInt();

        for(int i=0;i<children;i++) {
            Node child = this.takeInput(sc,node,i);
            node.children.add(child);
        }
        return node;
    }

    // Display Generic Tree Data
    public void display() {
        this.display(this.root);
    }

    private void display(Node node) {

        // Display Root Node
        String str = node.data + " => ";

        // Display Current Node Children
        for(int i=0;i<node.children.size();i++) {
            str = str + node.children.get(i).data + " , ";
        }

        str = str + "End ";
        System.out.println(str);

        // Display For All Nodes
        for(int i=0;i<node.children.size();i++) {
            this.display(node.children.get(i));
        }
    }
}
