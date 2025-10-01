// 2537. Count the Number of Good Subarrays
// https://leetcode.com/problems/count-the-number-of-good-subarrays
class Solution {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        long count = 0;
        long pairs = 0;
        int left = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        for (int right = 0; right < n; right++) {
            // add nums[right]
            int f = freq.getOrDefault(nums[right], 0);
            pairs += f; // each existing occurrence makes a new pair
            freq.put(nums[right], f + 1);

            // shrink while condition satisfied
            while (pairs >= k) {
                count += n - right;
                int curFreq = freq.get(nums[left]);
                freq.put(nums[left], curFreq - 1);
                pairs -= (curFreq - 1);
                left++;
            }
        }
        return count;
    }
}