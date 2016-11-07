import java.util.Arrays;

public class ArrayQueue {
    private long[] elements = null;
    private int capacity = 0;
    private int tail = 0;
    private int size = 0;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.elements = new long[capacity];
    }

    public ArrayQueue(int capacity, long value) {
        this.capacity = capacity;
        this.elements = new long[capacity];
        Arrays.fill(elements, value);
        size = capacity;
    }

    public boolean add(long value) {
        if (size < capacity) {
            if (tail >= capacity) {
                tail = 0;
            }
            elements[tail] = value;
            ++tail;
            ++size;
            return true;
        }
        return false;
    }

    public long remove() throws Exception {
        long nextEl = peek();
        --size;
        return nextEl;
    }

    public boolean removeAndAdd(long value) {
        if (size == 0) {
            return false;
        }
        if (tail >= capacity) {
            tail = 0;
        }
        elements[tail] = value;
        ++tail;
        return true;
    }

    public long peek() throws Exception {
        if (size == 0) {
            throw new Exception("Element can't be peeked from empty queue");
        }
        int head = tail - size;
        if (head < 0) {
            head += capacity;
        }
        return elements[head];
    }

    public void reset() {
        this.tail = 0;
        this.size = 0;
    }

    public int capacity() {
        return this.capacity;
    }

    public int size() {
        return this.size;
    }
}