// 2799. Count Complete Subarrays in an Array
// https://leetcode.com/problems/count-complete-subarrays-in-an-array/
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int left = 0, right = 0, complete = 0;
        Map<Integer, Integer> map = new HashMap<>();
		// can use single map also, and clear it off after counting distinct values.
		// but it seems its not too much optimized in case of Java
		// using single map takes 8ms, double map is taking 7ms.
        Map<Integer, Integer> map2 = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            complete += (i+1);
        }  
        if(map.size()==1)
            return complete;
        else
            complete = 0;
        while(right < nums.length){
            map2.put(nums[right], map2.getOrDefault(nums[right], 0)+1);
            while(map2.size() == map.size()){
                complete += nums.length - right;
                if(map2.get(nums[left]) > 1){
                    map2.put(nums[left], map2.get(nums[left])-1);
                }else{
                    map2.remove(nums[left]);
                    left++;
                    break;
                }
                left++;
            }
            right++;
        }
        return complete;
    }
}
// Brute force solution , Time : O(n^2)
// class Solution {
//     public int countCompleteSubarrays(int[] nums) {
//         int complete = 0;
//         Map<Integer, Integer> map = new HashMap<>();
//         Map<Integer, Integer> map2 = new HashMap<>();
//         for(int i=0;i<nums.length;i++){
//             map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
//             complete += (i+1);
//         }  
//         if(map.size()==1)
//             return complete;
//         else
//             complete = 0;
//         for(int i=0;i<nums.length;i++){
//             map2.clear();
//             for(int j=i;j<nums.length;j++){
//                 map2.put(nums[j], map.getOrDefault(nums[j], 0)+1);
//                 if(map2.size() == map.size())
//                     complete++;
//             }
//         }
//         return complete;
//     }
// }