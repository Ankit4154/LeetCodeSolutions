// 239. Sliding Window Maximum
// https://leetcode.com/problems/sliding-window-maximum/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 1)
            return nums;
        int[] output = new int[nums.length - k + 1];
        Deque<Integer> deq = new LinkedList<>();
        int left = 0, right = 0;

        while (right < nums.length) {
            while(!deq.isEmpty() && nums[deq.getLast()] < nums[right]){
                deq.removeLast();
            }
            deq.addLast(right);
            if(left > deq.getFirst()){
                deq.removeFirst();
            }
            if((right + 1) >= k){
                output[left] = nums[deq.getFirst()];
                left++;
            }
            right++;
        }
        return output;
    }
}