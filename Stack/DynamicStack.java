package Stack;

public class DynamicStack extends StackUsingArray {

    // Default Constructor
    DynamicStack() throws Exception {
        this(DEFAULT_CAPACITY);
    }

    // Constructor With Capacity
    DynamicStack(int capacity) throws Exception {
        super(capacity);
    }

    // Dynamic Increase Array Size
    @Override
    public void push(int item) throws Exception {
        if(this.size() == this.data.length) {
            int a[] = new int[2 * this.data.length];
            for(int i=0;i<this.size();i++) {
                a[i] = this.data[i];
            }
            this.data = a;
        }
        super.push(item);
    }
}
