// 1340. Jump Game V
// https://leetcode.com/problems/jump-game-v
class Solution {
    int[] dp;
    public int maxJumps(int[] arr, int d) {
        // jump to each index and calculate the max jumps
        dp = new int[arr.length];
        Arrays.fill(dp, -1);
        int max = 0;
        for(int i=arr.length-1;i>=0;i--){
            max = Math.max(max,solve(i, arr, d));
        }
        return max;
    }
    int solve(int ind, int[] arr, int d){
        if(ind >= arr.length || ind < 0)
            return 0;
		if(dp[ind]!= -1)
			return dp[ind];
        int currMax = 1;
        int addD = 0, subD = 0;
        for(int i=1;i<=d;i++){
            int nextInd = ind + i;
            if(nextInd < arr.length && arr[nextInd] >= arr[ind]){
                break;
            }
            addD = Math.max(addD,1 + solve(nextInd, arr, d));
        }
        for(int i=1;i<=d;i++){
            int prevInd = ind - i;
            if(prevInd >= 0 && arr[prevInd] >= arr[ind]){
                break;
            }
            subD = Math.max(subD, 1 + solve(prevInd, arr, d));
        }
        currMax = Math.max(currMax, Math.max(addD,subD));
        return dp[ind] = currMax;
    }
}