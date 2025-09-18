// 1. Two Sum
// https://leetcode.com/problems/two-sum/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // fix the size of hashmap to improve on memory consumption/complexity
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        // if output size is known use arrays instead of ArrayList
        int[] pairs = new int[2];
        for(int i=0;i<nums.length;i++){
            int x = target - nums[i];
            if(map.containsKey(x) && map.get(x) != i){
                pairs[0] = map.get(x);
                pairs[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return pairs;
    }
}