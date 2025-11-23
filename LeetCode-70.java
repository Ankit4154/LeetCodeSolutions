// 70. Climbing Stairs
// https://leetcode.com/problems/climbing-stairs
class Solution {
    public int climbStairs(int n) {
        // constraint
		// 1 <= n <= 45
		// eligible for 2 ^ n ? Optimize more?
		// combinations of numbers 2 and 1 steps to achieve sum n
		// example : n = 1, output = 1, only 1 distinct way to reach sum 1
		// n = 2, output = 2, 1+1 and 2 , 2 distinct way to reach sum 2
		// n = 3, output = 3, 1+1+1 and 1+2 and 2+1, 3 distinct way to reach sum 3
		// have a recursive method, start with i=0
		// base condition 1) : check if i > sum return 0;
		// base condition 2) : check if i == sum (0==3), if yes, take that in count and return 1
		// if no, then increment i with 1 (1 step) and increment i with 2(2 step)
		// call method i+1 and i+2 and count / sum the total they will return i.e.
		// return the total returned by i+1 and i+2
		
		//			            n = 3
		//						  0->3
		//			0+1=1->2	   	  	 0+2=2 -> 1
		//	1+1=2->1	 1+2=3->1	   2+1=3->1  2+2=4 ->0
		//2+1=3->1  2+2=4->0
		int[] dp = new int[n+1];
		for(int i=0;i<=n;i++)
			dp[i] = -1;
		return climb3(n, dp);
    }
	public int climb(int i, int n){
		// base condition 1
		if(i > n)
			return 0;
		// base condition 2
		if(i == n)
			return 1;
		return climb(i+1, n) + climb(i+2, n);
	}
    // O(2^n)
	public int climb2(int n){
		// base condition 1
		if(n < 0)
			return 0;
		// base condition 2
		if(n == 0)
			return 1;
		return climb2(n-1) + climb2(n-2);
	}
    // Optimized with dp/ memoization to O(n)
	public int climb3(int n, int[] dp){
		// base condition 1
		if(n < 0)
			return 0;
		// base condition 2
		if(n == 0){
            dp[0] = 1;
            return dp[0];
        }
		if(dp[n] != -1){
            return dp[n];
        }
        dp[n] = climb3(n-1, dp) + climb3(n-2, dp);
		return dp[n];
	}
}