// 704. Binary Search
// https://leetcode.com/problems/binary-search/description/
// calculate mid as low + (high-low)/2 to support bigger range of numbers
// low <= high
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length-1, mid = 0;
        while(low <= high){
            mid = low + (high - low )/2;
            int n = nums[mid];
            if(target == n){
                return mid;
            }else if(target > n){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return -1;
    }
}