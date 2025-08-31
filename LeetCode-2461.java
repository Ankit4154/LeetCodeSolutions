//2461. Maximum Sum of Distinct Subarrays With Length K
//https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int left = 0, right = 0;
        long sum = 0;
        long maxSum = 0L;
        Map<Integer, Integer> map = new HashMap<>();
        while(right < nums.length){
            sum += nums[right];
            map.put(nums[right], map.getOrDefault(nums[right], 0)+1);
            if((right-left) == k-1){
                if(map.size() == k){
                    maxSum = Math.max(maxSum,sum);
                    map.remove(nums[left]);
                }else if(map.size() < k){
                    if(map.containsKey(nums[left])){
                        if(map.get(nums[left]) > 1)
                            map.put(nums[left], map.get(nums[left])-1);
                        else
                            map.remove(nums[left]);
                    }
                }
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return maxSum;
    }
}