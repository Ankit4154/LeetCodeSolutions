// 3637. Trionic Array I
// https://leetcode.com/problems/trionic-array-i
class Solution {
    public boolean isTrionic(int[] nums) {
		int n = nums.length;
        for(int i=1;i<n;i++){
			while(i < n && nums[i-1] < nums[i] && count == 0){
				count = 1;
				i++;
			}
			while(i < n && nums[i-1] > nums[i] && count == 1){
				count = 2;
				i++;
			}
			while(i < n && nums[i-1] < nums[i] && count == 2){
				count = 3;
				i++;
			}
		}
		if(count == 3)class Solution {
    public boolean isTrionic(int[] nums) {
		int n = nums.length;
        if (n < 3) 
            return false;

        int i = 1;

        // Phase 1: strictly increasing
        while(i < n && nums[i] > nums[i - 1]){
            i++;
        }
        if(i == 1 || i == n)
            return false;

        // Phase 2: strictly decreasing
        while(i < n && nums[i] < nums[i - 1]){
            i++;
        }
        if(i == n) 
            return false;

        // Phase 3: strictly increasing
        while(i < n && nums[i] > nums[i - 1]){
            i++;
        }

        return i == n;
    }
}
			return true;
		return false;
    }
}