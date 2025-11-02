// 34. Find First and Last Position of Element in Sorted Array
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = firstOccurrence(nums, target);
        if(first == -1)
            return new int[]{-1,-1};
        int second = secondOccurrence(nums, target);
        return new int[]{first,second};
    }
    public int firstOccurrence(int[] nums, int target){
        int low = 0, high = nums.length-1;
        int ans = -1;
        while(low <= high){
            int mid = low + (high-low)/2;
            if(nums[mid] == target){
                ans = mid;
                high = mid - 1;
            }else if(nums[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }
    public int secondOccurrence(int[] nums, int target){
        int low = 0, high = nums.length-1;
        int ans = -1;
        while(low <= high){
            int mid = low + (high-low)/2;
            if(nums[mid] == target){
                ans = mid;
                low = mid + 1;
            }else if(nums[mid] > target){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
}