package Tree;

public class BST {

    // BST Node
    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    private Node root;

    // Constructor With Data Array
    BST(int a[]) {
        this.root = Construct(a,0,a.length-1);
    }

    // Construct BST
    private Node Construct(int[] a, int l, int h) {
        if(l > h) {
            return null;
        }

        int mid = (l + h) / 2;
        Node node = new Node(a[mid]);
        node.left = Construct(a,l,mid-1);
        node.right = Construct(a,mid+1,h);

        return node;
    }

    // Display BST
    public void display() {
        this.display(this.root);
    }

    private void display(Node node) {
        if(node == null)
            return;

        String str = "";

        if(node.left == null) {
            str = str + "End ";
        } else {
            str = str + node.left.data + " " ;
        }

        str += " => " + node.data + " <= ";

        if(node.right == null) {
            str = str + "End ";
        } else {
            str = str + node.right.data + " " ;
        }

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    // Check Element is present or Not in BST
    public boolean find(int i) {
        return this.find(this.root,i);
    }

    private boolean find(Node node,int i) {
        if(node == null)
            return false;

        if(i > node.data) {
            return find(node.right,i);
        } else if(i < node.data) {
            return find(node.left,i);
        } else {
            return true;
        }
    }

    // Find Maximum Value in BST
    public int max() {
        return this.max(this.root);
    }

    private int max(Node node) {
        if(node.right == null)
            return node.data;
        return max(node.right);
    }

    // Add Node In BST
    public void add(int i) {
        this.add(this.root,i);
    }

    private void add(Node node,int i) {
        if(i > node.data) {
            if(node.right == null) {
                Node newNode = new Node(i);
                node.right = newNode;
            } else {
                add(node.right,i);
            }
        } else {
            if(node.left == null) {
                Node newNode = new Node(i);
                node.left = newNode;
            } else {
                add(node.left,i);
            }
        }
    }

    // Remove Node In BST
    public void remove(int i) {
        this.remove(this.root,null,false,i);
    }

    private void remove(Node node,Node parent,boolean isLeftChild,int i) {
        if(node == null)
            return;

        if(i > node.data) {
            remove(node.right,node,false,i);
        } else if(i < node.data) {
            remove(node.left,node,true,i);
        } else {
            if(node.left == null && node.right == null) {
                if(isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if(node.left == null && node.right != null) {
                if(isLeftChild) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
            } else if(node.left != null && node.right == null) {
                if(isLeftChild) {
                    parent.left = node.left;
                } else {
                    parent.right = node.left;
                }
            } else {
                int max = this.max(node.left);
                node.data = max;
                remove(node.left,node,true,max);
            }
        }
    }
}
