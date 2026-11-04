// 3505. Minimum Operations to Make Elements Within K Subarrays Equal
// https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal
class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long leftSum = 0, rightSum = 0;
    int leftCount = 0, rightCount = 0;

    long[][] dp;

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;
        int m = n - x + 1;

        long[] cost = new long[m];

        // Build first window
        for(int i = 0; i < x; i++)
            add(nums[i]);

        rebalance();
        cost[0] = getCost();

        // Sliding window
        for(int i = x; i < n; i++) {

            int outgoing = nums[i - x];

            if(left.containsKey(outgoing))
                removeLeft(outgoing);
            else
                removeRight(outgoing);

            add(nums[i]);
            rebalance();

            cost[i - x + 1] = getCost();
        }

        // Recursive DP with memoization
        dp = new long[m][k + 1];

        for(long[] row : dp)
            Arrays.fill(row, -1);

        return solve(0, k, x, cost);
    }

    // Insert into appropriate partition
    void add(int val) {

        if(left.isEmpty()) {
            if(!right.isEmpty() && val > right.firstKey())
                addRight(val);
            else
                addLeft(val);
        }
        else if(val <= left.lastKey()) {
            addLeft(val);
        }
        else {
            addRight(val);
        }
    }

    void addLeft(int val) {
        left.put(val, left.getOrDefault(val, 0) + 1);
        leftSum += val;
        leftCount++;
    }

    void addRight(int val) {
        right.put(val, right.getOrDefault(val, 0) + 1);
        rightSum += val;
        rightCount++;
    }

    void removeLeft(int val) {

        int freq = left.get(val);

        if(freq == 1)
            left.remove(val);
        else
            left.put(val, freq - 1);

        leftSum -= val;
        leftCount--;
    }

    void removeRight(int val) {

        int freq = right.get(val);

        if(freq == 1)
            right.remove(val);
        else
            right.put(val, freq - 1);

        rightSum -= val;
        rightCount--;
    }

    // Maintain leftCount == rightCount
    // or leftCount == rightCount + 1
    void rebalance() {

        while(leftCount > rightCount + 1) {

            int val = left.lastKey();

            removeLeft(val);
            addRight(val);
        }

        while(leftCount < rightCount) {

            int val = right.firstKey();

            removeRight(val);
            addLeft(val);
        }
    }

    // Median is the largest element in left
    long getCost() {

        int median = left.lastKey();

        return (long) median * leftCount - leftSum
             + rightSum - (long) median * rightCount;
    }

    // DP: minimum cost to select 'remaining'
    // non-overlapping windows starting from idx
    long solve(int idx, int remaining, int x, long[] cost) {

        if(remaining == 0)
            return 0;

        if(idx >= cost.length ||
           cost.length - idx < remaining)
            return Long.MAX_VALUE / 2;

        if(dp[idx][remaining] != -1)
            return dp[idx][remaining];

        // Choice 1: Skip current window
        long skip = solve(idx + 1, remaining, x, cost);

        // Choice 2: Take current window
        long take = cost[idx] +
                    solve(idx + x, remaining - 1, x, cost);

        return dp[idx][remaining] = Math.min(skip, take);
    }
}
