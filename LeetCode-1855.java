// 1855. Maximum Distance Between a Pair of Values
// https://leetcode.com/problems/maximum-distance-between-a-pair-of-values
// Two Pointer approach
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
		int max = 0;
        int right = nums2.length-1;
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] <= nums2[j]){
				max = Math.max(max, j-i);
                j++; //expand distance
			}else{
                i++; //move ahead for nums1
            }
		}
		return max;
    }
}
// TLE
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
		int max = 0;
        for(int i=0;i<nums1.length;i++){
			int right = nums2.length-1;
			while(right > i && nums1[i] > nums2[right]){
				right--;
			}
			max = Math.max(max, right-i);
		}
		return max;
    }
}