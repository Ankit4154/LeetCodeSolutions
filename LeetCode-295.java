// 295. Find Median from Data Stream
// https://leetcode.com/problems/find-median-from-data-stream/
class MedianFinder{
	
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>((a,b)->b-a);
    }
    
    public void addNum(int num) {
        maxHeap.add(num);
		// check if heap elements are correctly balanced or not
		if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
			if(maxHeap.peek() > minHeap.peek()){
				int n = maxHeap.poll();
				minHeap.add(n);
			}
		}
		// check if any of the heap's size exceeds 1
		if(Math.abs(minHeap.size()-maxHeap.size())>1){
			if(minHeap.size()> maxHeap.size()){
				int n = minHeap.poll();
				maxHeap.add(n);
			}else{
				int n = maxHeap.poll();
				minHeap.add(n);
			}
		}
		
    }
    
    public double findMedian() {
        int minSize = minHeap.size();
		int maxSize = maxHeap.size();
		if(minSize == maxSize){
			return (double)((minHeap.peek() + maxHeap.peek()) / 2.0);
		}else if(minSize > maxSize){
			return (double)minHeap.peek();
		}else{
			return (double)maxHeap.peek();
		}
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */