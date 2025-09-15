// 3254. Find the Power of K-Size Subarrays I
// https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/
// Brute force Solution
class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            count = 0;
            list.clear();
            for(int j=i;j<nums.length;j++){
                if(j==i){
                    count++;
                }else{
                    int diff = nums[j] - nums[j-1];
                    if(diff == 1 && (nums[j] > nums[j-1]))
                        count++;
                }
                list.add(nums[j]);
                if(count == k){
                    output.add(nums[j]);
                    count = 0;
                    break;
                }else if(list.size() == k){
                    output.add(-1);
                    count = 0;
                    break;
                }
            }
        }
        return output.stream().mapToInt(Integer::intValue).toArray();
    }
}