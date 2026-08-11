// 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum
// https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum
class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i] - nums[i-1] == 1){
                sum+=nums[i];
            }else{
                break;
            }
        }
        while(set.contains(sum)){
            sum++;
        }
        return sum;
    }
}