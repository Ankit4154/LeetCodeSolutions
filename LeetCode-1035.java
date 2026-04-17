// 1035. Uncrossed Lines
// https://leetcode.com/problems/uncrossed-lines/description/
class Solution {
	Integer dp[][];
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        dp = new Integer[nums1.length][nums2.length];
		return solve(nums1.length-1, nums2.length-1, nums1, nums2);
    }
	int solve(int n, int m, int[] nums1, int[] nums2){
		if(n < 0 || m < 0){
			return 0;
		}
		if(dp[n][m] != null)
			return dp[n][m];
		int max = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		if(nums1[n] == nums2[m]){
			max = 1 + solve(n-1, m-1, nums1, nums2);
		}else{
			int moveNum1 = solve(n-1, m, nums1, nums2);
			int moveNum2 = solve(n, m-1, nums1, nums2);
			max2 = Math.max(moveNum1, moveNum2);
		}
		return dp[n][m] = Math.max(max, max2);
	}
}