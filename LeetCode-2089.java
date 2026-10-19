// 2089. Find Target Indices After Sorting Array
// https://leetcode.com/problems/find-target-indices-after-sorting-array/

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int minCount = 0;
        int sameCount = 0;
        for(int n : nums){
            if(n < target){
                minCount++;
            }else if(n == target){
                sameCount++;
            }
        }
        List<Integer> out = new ArrayList<>();
        for(int i=1;i<=sameCount;i++){
            out.add(minCount++);
        }
        return out;
    }
  }
