// 1248. Count Number of Nice Subarrays
// https://leetcode.com/problems/count-number-of-nice-subarrays/
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int nice1 = niceSubArray(nums, k);
        int nice2 = niceSubArray(nums, k-1);
        return nice1-nice2;
    }
    public int niceSubArray(int[] nums, int k) {
        if(k < 0)
            return 0;
        int left = 0, right = 0, count = 0, nice = 0;
        while(right < nums.length){
            count += nums[right]%2; 
            while(count > k){
                count -= nums[left]%2;
                left++;
            }
            nice += right - left + 1;
            right++;
        }
        return nice;
    }
}