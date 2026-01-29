// 1046. Last Stone Weight
// https://leetcode.com/problems/last-stone-weight/
class Solution {
    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1)
            return stones[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> b-a);
		for(int x : stones)
			maxHeap.add(x);
		while(maxHeap.size() > 1){
			int first = maxHeap.poll();
			int second = maxHeap.poll();
			int diff = first - second;
			maxHeap.add(diff);
		}
		if(maxHeap.size() == 1)
			return maxHeap.poll();
		return 0;
    }
}
