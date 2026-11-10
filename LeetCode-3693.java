// 3693. Climbing Stairs II
// https://leetcode.com/problems/climbing-stairs-ii
// optim, Space O(1), Time O(n)
class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[4];

        for(int i=1;i<=n;i++) {
            int min = Integer.MAX_VALUE;

            for(int j=1;j<=3;j++) {
                if(i-j >= 0) {
                    min = Math.min(min, dp[(i-j)%4] + costs[i-1] + j*j);
                }
            }

            dp[i%4] = min;
        }

        return dp[n%4];
    }
}

// optim, Space O(n), Time O(n)
class Solution {
	int[] dp;
    public int climbStairs(int n, int[] costs) {
        dp = new int[n+1];
        Arrays.fill(dp, -1);

        return solve(n, costs);
    }

    int solve(int ind, int[] costs) {
        if(ind == 0)
            return 0;

        if(dp[ind] != -1)
            return dp[ind];

        int min = Integer.MAX_VALUE;

        for(int i=1;i<=3;i++) {
            if(ind-i >= 0) {
                int cost = solve(ind-i, costs)
                         + costs[ind-1] + i*i;

                min = Math.min(min, cost);
            }
        }

        return dp[ind] = min;
    }
}

// passed, Space O(n), Time O(n)
class Solution {
	int[][] dp;
    public int climbStairs(int n, int[] costs) {
        int l = costs.length;
		dp = new int[l+1][4];
		return solve(0, n, costs, 0, 0);
    }
	
	int solve(int ind, int n, int[] costs, int cost, int prev){
		if(ind > n)
			return Integer.MAX_VALUE;
		int diff = ind - prev;
        int currCost = 0;
        if(ind > 0){
			currCost = cost + costs[ind-1] + diff * diff;
		}
		if(ind == n){
			return currCost;
		}
		if(dp[ind][diff] != 0){
			return dp[ind][diff];
		}
		int min = Integer.MAX_VALUE;
		for(int i=1;i<=3;i++){
			min = Math.min(min, solve(ind+i, n, costs, cost, ind));
		}
		if(min == Integer.MAX_VALUE)
			min = 0;
		return dp[ind][diff] = currCost + min;
		
	}
}

// MLE, Time O(n^2), Space O(n^2)
class Solution {
	int[][] dp;
    public int climbStairs(int n, int[] costs) {
        int l = costs.length;
		dp = new int[l+1][l+1];
		return solve(0, n, costs, 0, 0);
    }
	
	int solve(int ind, int n, int[] costs, int cost, int prev){
		if(ind > n)
			return Integer.MAX_VALUE;
		int currCost = 0;
        if(ind > 0){
			int diff = ind - prev;
			currCost = cost + costs[ind-1] + diff * diff;
		}
		if(ind == n){
			return currCost;
		}
		if(dp[ind][prev] != 0){
			return dp[ind][prev];
		}
		int min = Integer.MAX_VALUE;
		for(int i=1;i<=3;i++){
			min = Math.min(min, solve(ind+i, n, costs, cost, ind));
		}
		if(min == Integer.MAX_VALUE)
			min = 0;
		return dp[ind][prev] = currCost + min;
		
	}
}
// naive, TLE
class Solution {
    public int climbStairs(int n, int[] costs) {
		return solve(0, n, costs, 0, 0);
    }
	
	int solve(int ind, int n, int[] costs, int cost, int prev){
		if(ind > n)
			return Integer.MAX_VALUE;
		int currCost = 0;
        if(ind > 0){
			int diff = ind - prev;
			currCost = cost + costs[ind-1] + diff * diff;
		}
		if(ind == n){
			return currCost;
		}
		int min = Integer.MAX_VALUE;
		for(int i=1;i<=3;i++){
			min = Math.min(min, solve(ind+i, n, costs, cost, ind));
		}
		if(min == Integer.MAX_VALUE)
			min = 0;
		return currCost + min;
		
	}
}