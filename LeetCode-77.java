// 77. Combinations
// https://leetcode.com/problems/combinations/
class Solution {
	List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        boolean used[] = new boolean[n+1];
		solve(1, n, k, used, new ArrayList<>());
		return res;
    }
	private void solve(int start,int n, int k, boolean[] used, List<Integer> list){
		if(list.size() == k){
			res.add(new ArrayList<>(list));
			return;
		}
		if(start == n+1)
			return;
		for(int i=start;i<=n;i++){
			if(used[i])
				continue;
			list.add(i);
			used[i] = true;
			solve(i+1,n, k, used, list);
			list.remove(list.size()-1);
			used[i] = false;
		}
	}
}