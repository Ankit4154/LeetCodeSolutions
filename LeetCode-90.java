// 90. Subsets II
// https://leetcode.com/problems/subsets-ii/
class Solution {
	List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        getSubSets(nums,0, new ArrayList<Integer>());
		return res;
    }
	public void getSubSets(int[] nums, int ind, List<Integer> list){
		res.add(new ArrayList<>(list));
		for(int i=ind;i<nums.length;i++){
			if(i > ind && nums[i] == nums[i-1])
				continue;
			list.add(nums[i]);
			getSubSets(nums, i+1, list);
			list.remove(list.size()-1);
		}
	}
}