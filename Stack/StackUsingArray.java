package Stack;

public class StackUsingArray {

    protected int data[];
    protected int top;

    public static final int DEFAULT_CAPACITY = 10;

    // Default Constructor
    public StackUsingArray() throws Exception {
        this(DEFAULT_CAPACITY);
    }

    // Constructor With Capacity
    public StackUsingArray(int capacity) throws Exception {
        if(capacity < 1) {
            throw new Exception("Invalid Capacity");
        }
        this.data = new int[capacity];
        this.top = -1;
    }

    // Return Size Of Stack
    public int size() {
        return this.top + 1;
    }

    // Check Stack is Empty Or Not
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Add Element On Top Of The Stack
    public void push(int value) throws Exception {
        if(this.size() == this.data.length) {
            throw new Exception("Stack Is Full");
        }
        this.top++;
        this.data[this.top] = value;
    }

    // Remove Element On Top Of The Stack
    public int pop() throws Exception {
        if(this.size() == 0) {
            throw new Exception("Stack Is Empty");
        }
        int value = this.data[this.top];
        this.data[this.top] = 0;
        this.top--;
        return value;
    }

    // Return Element Available On Top Of The Stack
    public int top() throws Exception {
        if(this.size() == 0) {
            throw new Exception("Stack Is Empty");
        }
        int value = this.data[this.top];
        return value;
    }

    // Display Stack Data
    public void display() {
        for(int i=this.top;i>=0;i--) {
            System.out.print(this.data[i] + " ");
        }
        System.out.println("End");
    }
}
