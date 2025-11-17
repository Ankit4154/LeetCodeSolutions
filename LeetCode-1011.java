// 1011. Capacity To Ship Packages Within D Days
// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // find max capacity to ship all in 1 day
        // which will be sum of all weights.
        // find min capacity to ship all
        // which will be max weight out of all weights
        // because if the capacity is smaller than max weight
        // then package cannot be shipped.
        // so out range is between max weight and sum of all weight
        // find out the max weight
		// for possible answer between max weight and sum of all weight
		// determine the no. of days required for each possible capacity
		// if the no. of days required are within target days,
		// store that as a possible answer and shrink search space
		// by eliminating the other half.
		// if the no. of days required are outside/greater than target days, 
		// search the other half for a valid capacity.
		int max = Integer.MIN_VALUE, sum = 0;
		for(int x: weights){
			max = Math.max(max, x);
			sum += x;
		}
		int low = max, high = sum, ans = -1;
		while(low <= high){
			int capacity = low + (high-low)/2;
			int daysReq = getDays(weights, capacity);
			if(daysReq <= days){
                ans = capacity;
                high = capacity - 1;
            }else{
                low = capacity + 1;
            }
		}
		return ans;

    }
	public int getDays(int[] weights, int capacity){
		int day = 1, load = 0;
		for(int i=0;i<weights.length;i++){
			if(load + weights[i] > capacity){
				day++;
				load = weights[i];
			}else
				load += weights[i];
		}
		return day;
	}
}