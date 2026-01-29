// 703. Kth Largest Element in a Stream
// https://leetcode.com/problems/kth-largest-element-in-a-stream/
class KthLargest {

	int[] n;
	int capacity;
	int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        n = new int[k];
		capacity = 0;
		for(int x : nums){
			add(x);
		}
    }
    
    public int add(int val) {
        if(capacity < k){
			n[capacity] = val;
			heapUp(capacity);
			capacity++;
		}else if(val > n[0]){
            n[0] = val;
            heapDown(0);
        }
		return n[0];
    }
	
	public void heapUp(int ind){
		while(ind > 0) {
            int parent = (ind - 1) / 2;   // correct parent for 0-based heap
            // if(n[parent] >= n[ind]) {  // max-heap
            //     break;
            // }
            if(n[parent] <= n[ind]) {  // min-heap
                break;
            }
            swap(parent, ind);
            ind = parent;
        }
	}

    public void heapDown(int ind){
		// check both child for the current ind
        // whichever is minimum, store as smallest and
        // swap ind with that and 
        // assign ind to next node/smallest child for next swap
        // if no change then break
		while(true){
            int leftChild = 2 * ind+1;
            int rightChild = 2 * ind+2;
            int smallest = ind;
            if(leftChild < capacity && n[leftChild] < n[smallest]){
                smallest = leftChild;
            }
            if(rightChild < capacity && n[rightChild] < n[smallest]){
                smallest = rightChild;
            }
            if(smallest == ind)
                break;
            swap(ind, smallest);
            ind = smallest;
        }
	}
	
	public void swap(int a, int b){
		int temp = n[a];
		n[a] = n[b];
		n[b] = temp;
	}
	
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */