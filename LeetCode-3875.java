// 3875. Construct Uniform Parity Array I
// https://leetcode.com/problems/construct-uniform-parity-array-i/
// optim, always true 
// Case 1: nums1 contains both odd and even numbers , then 
// because every element can become odd
// For every odd nums1[i], simply keep it --> odd.
// For every even nums1[i], subtract an odd number --> even − odd = odd.
class Solution {
    public boolean uniformArray(int[] nums1) {
        return true;
    }
}

// init
class Solution {
    public boolean uniformArray(int[] nums1) {
        int odd = 0, even = 0;
        for(int n : nums1){
            if(n%2==0)
                even++;
            else
                odd++;
        }
        // if all even or all odd , return true;
		if(odd == 0 || even == 0)
            return true;
        return true;
    }
}