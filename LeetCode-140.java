// 140. Word Break II
// https://leetcode.com/problems/word-break-ii/
class Solution {
    List<String> res = new ArrayList<>();;
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(String x : wordDict)
            set.add(x);
		solve(0, s, list, set);
		return res;
    }
    private void solve(int ind, String s, List<String> list, Set<String> set){
        if(ind == s.length()){
            String out = "";
            for(String k : list)
                out += k +" ";
            out = out.substring(0, out.length()-1);
            res.add(out);
			return;
		}
		for(int i=ind;i<s.length();i++){
            String partA = s.substring(ind, i + 1);
			if(set.contains(partA)){
				list.add(partA);
				solve(i+1, s, list, set);
				list.remove(list.size()-1); // backtrack
			}
		}
	}
}