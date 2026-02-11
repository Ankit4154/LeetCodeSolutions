// 215. Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/
class Solution {
    public int findKthLargest(int[] nums, int k) {
	    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int x : nums){
			minHeap.add(x);
			if(minHeap.size() > k){
				minHeap.poll();
			}
		}
		return minHeap.peek();
    }
}
// init
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
		for(int x : nums){
			maxHeap.add(x);
		}
		while(!maxHeap.isEmpty()){
			int n = maxHeap.poll();
			k--;
			if(k == 0)
				return n;
		}
		return 0;
    }
}