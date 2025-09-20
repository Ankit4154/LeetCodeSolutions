// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0)
            return 0;
        int count = 0, maxLength = 0;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++)
            set.add(nums[i]);

        if(set.size()==1)
            return 1;

        for(Integer x : set){
            // check if the number is start of the sequence
            // if it is then check for next elements of sequence
            if(!set.contains(x-1)){
                count = 1;
                while(set.contains(x+count)){
                    count++;
                }
                maxLength = Math.max(maxLength, count);
            }else{
                count = 0;
            }
        }
        return maxLength;
    }
}