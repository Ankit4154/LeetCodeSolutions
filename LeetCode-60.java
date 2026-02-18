// 60. Permutation Sequence
// https://leetcode.com/problems/permutation-sequence
class Solution {
	String s = "";
    int k2 = 0;
    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n+1];
        k2 = k;
		solve(used, n, new ArrayList<>());
		return s;
    }
	void solve(boolean[] used, int n, List<Integer>  list){
		if(list.size()==n){
            k2--;
			if(k2 == 0){
				for(int x : list)
					s+=x;
			}
			return;
		}
        if(k2 == 0)
            return;
		for(int i=1;i<=n;i++){
			if(used[i])
				continue;
			used[i] = true;
			list.add(i);
			solve(used, n, list);
			list.remove(list.size()-1);
			used[i] = false;
		}
		
	}
}