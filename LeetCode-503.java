// 503. Next Greater Element II
// https://leetcode.com/problems/next-greater-element-ii


// Optimized, O(n), monotonic stack
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Deque<Integer> stack = new ArrayDeque<>(); // stores indices

        // Traverse array twice for circular behavior
        for(int i = 0; i < 2 * n; i++) {
            int idx = i % n;

            while(!stack.isEmpty() && nums[idx] > nums[stack.peek()]) {
                res[stack.pop()] = nums[idx];
            }

            // Push only in first pass
            if(i < n) {
                stack.push(idx);
            }
        }
        return res;
    }
}


class Solution {
    public int[] nextGreaterElements(int[] nums) {
		if(nums.length == 1)
            return new int[]{-1};
        int left = 0, right = 1;
		int[] out = new int[nums.length];
		int count = 0;
		while(count != nums.length){
			if(nums[left] < nums[right]){
				out[count++] = nums[right];
				left++;
				right = left + 1;
			}else if(right == left){
				out[count++] = -1;
				left++;
				right += 2;          
            }else{
				right++;
			}
			if(right >= nums.length){
				right = 0;
			}
		}
		return out;
    }
}