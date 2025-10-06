// 622. Design Circular Queue
// https://leetcode.com/problems/design-circular-queue
class MyCircularQueue {

    int currentSize, front, rear;
    int[] arr;

    public MyCircularQueue(int k) {
        currentSize = 0;
        arr = new int[k];
        front = -1;
        rear = -1;
    }
    
    public boolean enQueue(int value) {
        if(isFull())
            return false;
        if(isEmpty() && arr != null){
            front = 0;
            rear = 0;
        }else{
            rear = (rear+1)%arr.length;
        }
        arr[rear] = value;
        currentSize++;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty())
            return false;
        if(currentSize == 1){
            rear = -1;
            front = -1;
        }else{
            arr[front] = -1;
            front = (front + 1)%arr.length;
        }
        currentSize--;
        return true;
    }
    
    public int Front() {
        if(isEmpty())
            return -1;
        return arr[front];
    }
    
    public int Rear() {
        if(isEmpty())
            return -1;
        return arr[rear];
    }
    
    public boolean isEmpty() {
        if(currentSize == 0)
            return true;
        return false;
    }
    
    public boolean isFull() {
        if(currentSize > 0 && arr.length == currentSize)
            return true;
        return false;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */