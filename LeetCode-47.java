// 47. Permutations II
// https://leetcode.com/problems/permutations-ii/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
		findPermutations(0, nums, out);
        return out;
    }
    void findPermutations(int start, int[] nums, List<List<Integer>> out){
		// Store the answer to output list which is found at leaf node/base case
        if(start == nums.length){
            List<Integer> list = new ArrayList<>();
            for(int n : nums){
                list.add(n);
            }
            out.add(list);
            return;
        }
        Set<Integer> used = new HashSet<>();
		for(int i=start;i<nums.length;i++){
            if(used.contains(nums[i]))
                continue;
            used.add(nums[i]);

			swap(start, i, nums);
			findPermutations(start + 1, nums, out);
			// backtrack
            swap(i, start, nums);
		}
	}
    void swap(int a, int b, int[] nums){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}