// 46. Permutations
// https://leetcode.com/problems/permutations/description/
// optimized O(n * n!)
class Solution {
    public List<List<Integer>> permute(int[] nums) {
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
		for(int i=start;i<nums.length;i++){
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
// initial
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
		findPermutations(0, nums, out, new ArrayList<>());
        return out;
    }
    void findPermutations(int start, int[] nums, List<List<Integer>> out, List<Integer> list){
		if(start == nums.length){
			return;
		}
		for(int i=start;i<nums.length;i++){
			swap(start, i, nums);
            list = Arrays.stream(nums)
                 .boxed().collect(Collectors.toList());
            if(!out.contains(list))
                out.add(list);
			findPermutations(start + 1, nums, out, list);
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