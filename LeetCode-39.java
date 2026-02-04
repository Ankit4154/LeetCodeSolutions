// 39. Combination Sum
// https://leetcode.com/problems/combination-sum/
// Time : O(2^target) 
// Maximum depth happens when we keep picking the smallest number. Space : O(T / min(candidates))

class Solution {
    List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        solve(0, candidates, target, new ArrayList<>());
		return res;
    }
	public void solve(int i, int[] candidates, int target, List<Integer> list){
		if(i == candidates.length)
			return;
		if(target == 0){
			res.add(new ArrayList<>(list));
			return;
		}
        if(candidates[i] > target)
            return;
		// add number to possible solution list
		list.add(candidates[i]);
		//sum += candidates[i];
        // remain at same index to pick the current number
		solve(i, candidates, target-candidates[i], list);
		// backtrack
		list.remove(list.size()-1);
		//sum -= candidates[i];
        // move to next index, not picking the current number
		solve(i+1, candidates, target, list);
	}
}

// initial
class Solution {
    List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        solve(0, candidates, target, 0, new ArrayList<>());
		return res;
    }
	public void solve(int i, int[] candidates, int target, int sum, List<Integer> list){
		if(sum > target || i == candidates.length)
			return;
		if(sum == target){
            System.out.println(list);
			res.add(new ArrayList<>(list));
			return;
		}
		// add number to possible solution list
		list.add(candidates[i]);
		sum += candidates[i];
        // remain at same index to pick the current number
		solve(i, candidates, target, sum, list);
		// backtrack
		list.remove(list.size()-1);
		sum -= candidates[i];
        // move to next index, not picking the current number
		solve(i+1, candidates, target, sum, list);
	}
}