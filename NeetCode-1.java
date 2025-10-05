// https://neetcode.io/problems/dynamicArray
// Design Dynamic Array (Resizable Array)
class DynamicArray {

    int capacity = 1;
    int nonEmptyElements = 0;
    Object[] arr = new Object[capacity];

    public DynamicArray(int capacity) {
        if(capacity == 0)
            capacity = 1;
        this.capacity = capacity;
        this.arr = new Object[this.capacity];
    }

    public Object get(int i) {
        return arr[i];
    }

    public void set(int i, int n) {
        if(i > capacity){
            resize();
        }
        arr[i] = n;
    }

    public void pushback(int n) {
        if(nonEmptyElements == capacity)
            resize();
        arr[nonEmptyElements] = n;
        nonEmptyElements++;
    }

    public Object popback() {
        nonEmptyElements--;
        return arr[nonEmptyElements];
    }

    private void resize() {
        capacity += capacity;
        Object[] tmp = new Object[capacity];
        for(int l=0;l<arr.length;l++){
            tmp[l] = arr[l];
        }
        arr = tmp;
    }

    public int getSize() {
        return nonEmptyElements;
    }

    public int getCapacity() {
        return capacity;
    }
}
