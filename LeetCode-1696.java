// 1696. Jump Game VI
// https://leetcode.com/problems/jump-game-vi
// optim, O(n)
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n];
        dp[0] = nums[0];

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(0);

        for(int i=1;i<n;i++){

            // Remove indices outside window
            while(!dq.isEmpty() && dq.peekFirst() < i - k) {
                dq.pollFirst();
            }

            // Best previous dp
            dp[i] = nums[i] + dp[dq.peekFirst()];

            // Maintain decreasing dp values
            while(!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]){
                dq.pollLast();
            }

            dq.offerLast(i);
        }

        return dp[n - 1];
    }
}

// optim 1, O(n*k)
class Solution {
    Integer[] dp;
    public int maxResult(int[] nums, int k) {
        dp = new Integer[nums.length];
        return solve(0, nums, k);
    }
    int solve(int ind, int[] nums, int k){
      
        if(ind == nums.length-1){
            return nums[ind];
        }

        if(dp[ind] != null)
            return dp[ind];

        int currMax = Integer.MIN_VALUE;
        for(int i=1;i<=k && ind+i < nums.length;i++){
            int currSum = solve(ind + i, nums, k);
            currMax = Math.max(currMax, currSum);
        }
        return dp[ind] = nums[ind] + currMax;
    }
}

// init
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxResult(int[] nums, int k) {
        solve(0, nums, k, 0);
        return max;
    }
    int solve(int ind, int[] nums, int k, int sum){
        if(ind >= nums.length)
            return Integer.MIN_VALUE;
        if(ind == nums.length-1){
            sum += nums[ind];
            max = Math.max(max, sum);
            return sum;
        }
        sum += nums[ind];
        for(int i=1;i<=k;i++){
            solve(ind + i, nums, k, sum);
        }
        return sum;
    }
}