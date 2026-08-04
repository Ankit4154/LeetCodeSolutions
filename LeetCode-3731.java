// 3731. Find Missing Elements
// https://leetcode.com/problems/find-missing-elements
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int smallest = Integer.MAX_VALUE, largest = -1;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            smallest = Math.min(smallest, nums[i]);
            largest = Math.max(largest, nums[i]);
            set.add(nums[i]);
        }
        List<Integer> out = new ArrayList<>();
        for(int i=smallest+1;i<largest;i++){
            if(!set.contains(i))
                out.add(i);
        }
        return out;
        
    }
}