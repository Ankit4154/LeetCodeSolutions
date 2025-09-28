// 992. Subarrays with K Different Integers
// https://leetcode.com/problems/subarrays-with-k-different-integers/description/
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
		// whenever the problem has a confusion where whether 
		// to move right pointer (if we do this, we may miss out on the subarrays)
		// or to move left pointer to shrink and calculate the subarrays
		// always go with finding a solution for lesser than k elements
		// Once we have a solution for lesser than k, we can then
		// substract k-1 solution from all lesser than k solution to get answer for k.
		// k solution(k + k-1 + k-2 +...) - (k-1) solution(k-1 + k-2 +...) = k
        return solveK(nums, k) - solveK(nums, k-1);
    }
    public int solveK(int[] nums, int k) {
        if(k == 0)
            return 0;
        int left = 0, right = 0, sum=0;
        Map<Integer, Integer> map = new HashMap<>();
        while(right < nums.length){
            int n = nums[right];
            map.put(n, map.getOrDefault(n, 0)+1);
            if(map.size() <= k){
                sum += right - left + 1;
            }else if(map.size() > k){
                int l = nums[left];
                while(map.size() !=k){
                    if(map.get(l) > 1)
                        map.put(l, map.get(l)-1);
                    else
                        map.remove(l);
                    left++;
                    l = nums[left];
                }
                sum += right - left + 1;
            }
            right++;
        }
        return sum;
		
		
		// brute force solution O(n^2) time limit exceed
        // int count = 0;
        // Map<Integer, Integer> map = new HashMap<>();
        // for(int i=0;i<nums.length;i++){
        //     map.clear();
        //     for(int j=i;j<nums.length;j++){
        //         map.put(nums[j], map.getOrDefault(nums[j], 0)+1);
        //         if(map.size() == k)
        //             count++;
        //     }
        // }
        // return count;
    }
}