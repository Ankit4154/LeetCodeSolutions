// 1482. Minimum Number of Days to Make m Bouquets
// https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if(m*k > bloomDay.length)
			return -1;
		int minDays = Integer.MAX_VALUE;
		int maxDays = Integer.MIN_VALUE;
		for(int x : bloomDay){
			minDays = Math.min(minDays, x);
			maxDays = Math.max(maxDays, x);
		}
		int low = minDays, high = maxDays, ans = -1;
		while(low <= high){
			int mid = low + (high-low)/2;
            if(possible(bloomDay, m,k,mid)){
                ans = mid;
                high = mid - 1;
            }else {
				low = mid + 1;
			}
		}
        return ans;
    }

    private boolean possible(int[] bloomDay, int m, int k, int days) {
        int bouquets = 0;
        int flowers = 0;

        for(int d : bloomDay) {
            if(d <= days) {
                flowers++;
                if(flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            }else{
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}