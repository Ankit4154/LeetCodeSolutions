// 287. Find the Duplicate Number
// https://leetcode.com/problems/find-the-duplicate-number/
class Solution {
    public int findDuplicate(int[] nums) {
        // [1,3,4,2,2]
		// [1,2,3,2,2] -- can have multiple duplicates not just 1.
		// [1,1,2]
		// [3,3] -- invalid case, valid should be nums.length = n + 1, 3+1=4
		// start with fast and slow pointer at nums[0]
		int slow = nums[0], fast = nums[0];
		// move slow one step and fast two step ahead
		// both will meet at first intersection point
		do{
			slow = nums[slow];
			fast = nums[nums[fast]];
		}while(slow != fast);
		// reset slow and retrace the path from both slow and fast
		// to second intersection point
		slow = nums[0];
		while(slow != fast){
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
    }
}
