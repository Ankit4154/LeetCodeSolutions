// 2333. Minimum Sum of Squared Difference
// https://leetcode.com/problems/minimum-sum-of-squared-difference
// optim
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];

        long total = 0;
        int max = 0;

        for(int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            max = Math.max(max, diff[i]);
        }

        if(total <= k)
            return 0;

        int left = 0, right = max;

        while(left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for(int d : diff) {
                if(d > mid)
                    needed += d - mid;
            }

            if(needed <= k)
                right = mid;
            else
                left = mid + 1;
        }

        int level = left;
        long used = 0;
        long ans = 0;

        for(int d : diff) {
            if(d > level) {
                used += d - level;
                d = level;
            }
            ans += (long) d * d;
        }

        long remaining = k - used;

        for(int i = 0; i < n && remaining > 0; i++) {
            if(diff[i] >= level && level > 0) {
                ans -= (long) level * level;
                ans += (long) (level - 1) * (level - 1);
                remaining--;
            }
        }

        return ans;
    }
}
// naive, TLE
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
		long sum = k1 + k2;
		long total = 0;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> Integer.compare(b,a));
		for(int i=0;i<n;i++){
			int diff = Math.abs(nums1[i] - nums2[i]);
			total += diff;
			maxHeap.add(diff);
		}
		if(total <= sum)
			return 0;
		
		while(sum != 0){
			int max = maxHeap.poll();
            if(max == 0){
				break;
			}
			max--;
            if(max < 0)
                max = 0;
			maxHeap.add(max);
			sum--;
		}
		long minSum = 0;
		while(!maxHeap.isEmpty()){
			int diff = maxHeap.poll();
			minSum += Math.pow(diff, 2);
		}
		return minSum;
    }
}