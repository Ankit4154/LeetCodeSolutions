// 1658. Minimum Operations to Reduce X to Zero
// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero
// optim
class Solution {
	// the problem can be simplified as find longest contiguous 
	// subarray whose sum is totalSum - x
	// elements we must remove are x, so elements we must keep will be totalSum - x
	// if we find the longest contiguous subarray 
	// then nums.length - maxLength will be the answer
	// Also to minimize operations, we need to maximize the length of the subarray.
	// Thus, maxLength = Math.max(maxLength, right - left + 1);
    public int minOperations(int[] nums, int x) {
        int maxLength = -1, left = 0, right = 0, sum = 0, target = 0;
        for(int i=0;i<nums.length;i++){
            sum+= nums[i];
        }
        target = sum - x;
		if(target == 0)
            return nums.length;
        sum = 0;
        while(right < nums.length){
            sum += nums[right];
            while(sum > target && left<=right){
                sum -= nums[left];
                left++;
            }
            if(sum == target){
                maxLength = Math.max(maxLength,right - left + 1);
            }
            right++;
        }
        if(maxLength != -1)
            return nums.length - maxLength;
        else
            return -1;
    }
}
// naive
class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        // prefixSum -> number of elements in prefix
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);

        int sum = 0;

        // Store all possible prefixes
        for(int i=0;i<n;i++){
            sum += nums[i];
            map.put(sum, i + 1);
        }

        int ans = Integer.MAX_VALUE;

        // Check suffixes
        int suffixSum = 0;

        for(int i=n-1;i>=0;i--){

            suffixSum += nums[i];

            int remaining = x - suffixSum;

            if(map.containsKey(remaining)){

                int prefixSteps = map.get(remaining);
                int suffixSteps = n - i;

                // Prefix and suffix should not overlap
                if(prefixSteps <= i){
                    ans = Math.min(ans, prefixSteps + suffixSteps);
                }
            }
        }

        // Also check taking only a prefix
        if(map.containsKey(x)){
            ans = Math.min(ans, map.get(x));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
