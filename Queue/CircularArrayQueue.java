package Queue;

public class CircularArrayQueue {

    private int arr[];
    private int cs;
    private int ms;
    private int front;
    private int rear;

    public static int DEFAULT_CAPACITY = 10;

    // Default Constructor
    CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor With Queue Capacity
    CircularArrayQueue(int Capacity) {
        this.arr = new int[Capacity];
        this.cs = 0;
        this.ms = Capacity;
        this.front = 0;
        this.rear = Capacity-1;
    }

    // Check Queue is Full Or Not
    public boolean isFull() {
        return this.cs == this.ms;
    }

    // Check Queue is Empty Or Not
    public boolean isEmpty() {
        return this.cs == 0;
    }

    // Add Element On Queue
    public void enqueue(int data) {
        if(!this.isFull()) {
            this.rear = (this.rear + 1) % this.arr.length;
            this.arr[this.rear] = data;
            this.cs = this.cs + 1;
        }
    }

    // Remove Element On Queue
    public void dequeue() {
        if(!this.isEmpty()) {
            this.front = (this.front + 1) % this.arr.length;
            this.cs = this.cs -1;
        }
    }

    // Get Front Element Value
    public int getFront() {
        return this.arr[this.front];
    }
}
