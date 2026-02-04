// 40. Combination Sum II
// https://leetcode.com/problems/combination-sum-ii/
// Time : O(2^n) 
// Maximum depth = n (each element used once), Space : O(n)
class Solution {
    List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        solve(0, candidates, target, new ArrayList<>());
		return res;
    }
	public void solve(int i, int[] candidates, int target, List<Integer> list){
		if(target == 0){
			res.add(new ArrayList<>(list));
			return;
		}
        if(target < 0 || i == candidates.length)
			return;
		if (candidates[i] > target)
            return;
        list.add(candidates[i]);
        // pick current element and move forward
		solve(i+1, candidates, target-candidates[i], list);
		// backtrack
		list.remove(list.size()-1);
		// SKIP all duplicates of current element
        int j = i+1;
        while(j < candidates.length && candidates[i] == candidates[j]){
            j++;
        }
        // move to next non duplicate index, and pick that number
		solve(j, candidates, target, list);
	}
}


// init
class Solution {
    List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        solve(0, candidates, target, 0, new ArrayList<>());
		return res;
    }
	public void solve(int i, int[] candidates, int target, int sum, List<Integer> list){
		if(sum == target){
			res.add(new ArrayList<>(list));
			return;
		}
        if(sum > target || i == candidates.length)
			return;
		list.add(candidates[i]);
        // remain at same index to pick the current number
		solve(i+1, candidates, target, sum+candidates[i], list);
		// backtrack
		list.remove(list.size()-1);
		// SKIP all duplicates of current element
        int j = i+1;
        while(j < candidates.length && candidates[i] == candidates[j]){
            j++;
        }
        // move to next non duplicate index, and pick that number
		solve(j, candidates, target, sum, list);
	}
}