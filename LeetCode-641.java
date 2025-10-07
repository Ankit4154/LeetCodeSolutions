// 641. Design Circular Deque
// https://leetcode.com/problems/design-circular-deque/
class MyCircularDeque {

    int currentSize, front, rear;
    int[] arr;

    public MyCircularDeque(int k) {
        arr = new int[k];
        currentSize = 0;
        front = -1;
        rear = -1;
    }
    
    public boolean insertFront(int value) {
        if(isFull()){
            return false;
        }
        if(isEmpty()){
            front = 0;
            rear = 0;
        }else{
            if((front - 1) < 0)
                front = (arr.length + (front - 1))%arr.length;
            else
                front = (front - 1)%arr.length;
        }
        arr[front] = value;
        currentSize++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if(isFull()){
            return false;
        }
        if(isEmpty()){
            front = 0;
            rear = 0;
        }else{
            rear = (rear + 1)%arr.length;
        }
        arr[rear] = value;
        currentSize++;
        return true;
    }
    
    public boolean deleteFront() {
        if(isEmpty()){
            return false;
        }
        if(currentSize == 1){
            front = -1;
            rear = -1;
        }else{
            front = (front + 1)%arr.length;
        }
        currentSize--;
        return true;
    }
    
    public boolean deleteLast() {
        if(isEmpty()){
            return false;
        }
        if(currentSize == 1){
            front = -1;
            rear = -1;
        }else{
            if((rear - 1)<0)
                rear = (arr.length + (rear - 1))%arr.length;
            else
                rear = (rear - 1) % arr.length;
        }
        currentSize--;
        return true;
    }
    
    public int getFront() {
        if(isEmpty())
            return -1;
        return arr[front];
    }
    
    public int getRear() {
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
        if(currentSize == arr.length)
            return true;
        return false;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */