// 131. Palindrome Partitioning
// https://leetcode.com/problems/palindrome-partitioning/
// O(N * 2^N-1)
class Solution {
	List<List<String>> res = new ArrayList<>();;
    public List<List<String>> partition(String s) {
		List<String> list = new ArrayList<>();
		if(s.length() == 1){
			list.add(s);
			res.add(list);
            return res;
		}
		solve(0, s, list);
		return res;
    }
	
	private void solve(int ind, String s, List<String> list){
        if(ind == s.length()){
			res.add(new ArrayList<>(list));
			return;
		}
		for(int i=ind;i<s.length();i++){
            String partA = s.substring(ind, i + 1);
			if(isPalindrome(partA)){
				list.add(partA);
				solve(i+1, s, list);
				list.remove(list.size()-1); // backtrack
			}
		}
	}
	
	private boolean isPalindrome(String s){
		int l = 0, r = s.length()-1;
		while(l < r){
			if(s.charAt(l) != s.charAt(r))
				return false;
			l++;
			r--;
		}
		return true;
	}
}