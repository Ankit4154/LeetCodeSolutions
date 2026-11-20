// 3041. Maximize Consecutive Elements in an Array After Modification
// https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification
class Solution {
    public int maxSelectedElements(int[] nums) {
		Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for(int num : nums) {
            int a = dp.getOrDefault(num - 1, 0) + 1;
            int b = dp.getOrDefault(num, 0) + 1;

            dp.put(num, Math.max(dp.getOrDefault(num, 0), a));
            dp.put(num + 1, Math.max(dp.getOrDefault(num + 1, 0), b));

            ans = Math.max(ans, Math.max(a, b));
        }

        return ans;
    }
}

// TLE
class Solution {
	int n;
	Map<String, Integer> dp;
    public int maxSelectedElements(int[] nums) {
		n = nums.length;
        dp = new HashMap<>();
		Arrays.sort(nums);
		return solve(0, nums, Integer.MIN_VALUE);
    }
	
	int solve(int ind, int[] nums, int prev){
		if(ind >= n)
			return 0;
		String key = ind +","+ prev;
		if(dp.containsKey(key))
			return dp.get(key);
		// skip
		int ans = solve(ind + 1, nums, prev);
		
        // Select without incrementing
        if(prev == Integer.MIN_VALUE || nums[ind] == prev + 1) {
            ans = Math.max(ans,
                1 + solve(ind + 1, nums, nums[ind]));
        }

        // Select after incrementing
        if(prev == Integer.MIN_VALUE || nums[ind] + 1 == prev + 1) {
            ans = Math.max(ans,
                1 + solve(ind + 1, nums, nums[ind] + 1));
        }

		dp.put(key, ans);
		return ans;
	}
}