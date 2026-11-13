// 3420. Count Non-Decreasing Subarrays After K Operations
// https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations
class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        int log = 1;
        while((1 << log) <= n)
            log++;

        int[][] up = new int[log][n+1];
        long[][] sum = new long[log][n+1];

        // Important: initialize sentinel
        up[0][n] = n;

        // Next Greater Element
        Deque<Integer> st = new ArrayDeque<>();

        for(int i=n-1;i>=0;i--) {
            while(!st.isEmpty() && nums[st.peek()] <= nums[i])
                st.pop();

            int next = st.isEmpty() ? n : st.peek();

            up[0][i] = next;
            sum[0][i] = (long)nums[i] * (next-i);

            st.push(i);
        }

        // Binary lifting preprocessing
        for(int j=1;j<log;j++) {
            for(int i=0;i<=n;i++) {
                int mid = up[j-1][i];

                up[j][i] = up[j-1][mid];
                sum[j][i] = sum[j-1][i] + sum[j-1][mid];
            }
        }

        // Prefix sum
        long[] prefix = new long[n+1];

        for(int i=0;i<n;i++)
            prefix[i+1] = prefix[i] + nums[i];

        int left = 0;
        long ans = 0;

        for(int right=0;right<n;right++) {

            while(left <= right &&
                  getCost(left, right, nums, up, sum, prefix, log) > k) {
                left++;
            }

            ans += right-left+1;
        }

        return ans;
    }

    long getCost(int l, int r, int[] nums,
                 int[][] up, long[][] sum,
                 long[] prefix, int log) {

        int curr = l;
        long maxSum = 0;

        // Sum of prefix maximums
        for(int j=log-1;j>=0;j--) {
            int next = up[j][curr];

            if(next <= r) {
                maxSum += sum[j][curr];
                curr = next;
            }
        }

        // Remaining block
        maxSum += (long)nums[curr] * (r-curr+1);

        long originalSum = prefix[r+1] - prefix[l];

        return maxSum-originalSum;
    }
}

// naive, TLE
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long n = nums.length;
		long subCount = (n*(n+1))/2;
		long count = 0;
		for(int i=0;i<n;i++){
			long k1 = k;
			long left = nums[i];
			for(int j=i+1;j<n;j++){
				if(left > nums[j]){
					long diff = left-nums[j];
					k1 -= diff;
					if(k1 < 0){
						count += (n-j);
						break;
					}
				}else{
					left = nums[j];
				}
			}
		}
		return subCount - count;
    }
}