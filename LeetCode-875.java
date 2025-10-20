// 875. Koko Eating Bananas
// https://leetcode.com/problems/koko-eating-bananas/
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
		// start with possible options such as starting with 
		// minimum k as 1 all upto the max piles[i]
		// perform binary search to find k value
		// by validating each k value within the alloted piles
		// if we find a possible k, try finding minimum k and continue
		// the search till we find one.
		// Note : Given that h will always be >= piles.length
		// so if h == piles.length return maximum piles[i]
        int l = 1, r  = 1, max = piles[0], res = 0;
        for(int x : piles)
            max = Math.max(max, x);
        if(h == piles.length)
            return max;
        r = max;
        res = max;
        while(l <= r){
            int mid = l + (r-l)/2;
            int k = 0;
            for(int x : piles)
                k += Math.ceil((double) x / mid);

            if(k <= h){
                res = mid;
                r = mid - 1;
            }else
                l = mid + 1;
        }
        return res;
    }
}