// 167. Two Sum II - Input Array Is Sorted
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1, sum = 0;
        int[] output = new int[2];
        // since the array is already sorted
        // start with left and right pointers
        // if the sum exceeds target, we know that we must reduce
        // reducing should happen from largest element i.e. right pointer
        // similarly if sum is less, we must increase via left pointer 
        while(left < right){
            sum = numbers[left] + numbers[right];
            if(sum == target){
                output[0] = left+1;
                output[1] = right+1;
                return output;
            }
            if(sum < target){
                left++;
            }
            if(sum > target){
                right--;
            }
        }
        return output;
    }
}