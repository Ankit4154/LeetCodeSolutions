// 940. Distinct Subsequences II
// https://leetcode.com/problems/distinct-subsequences-ii
// optim
class Solution {
    final int MOD = 1_000_000_007;
    public int distinctSubseqII(String s) {
        long[] end = new long[26];

        for(char ch : s.toCharArray()){
            int c = ch - 'a';
			// calculated / count no. of distinct subsequence ending with c character
            long total = 1;

            for(long x : end){
                total = (total + x) % MOD;
            }
			
            end[c] = total;
        }

        long ans = 0;

        for(long x : end){
            ans = (ans + x) % MOD;
        }

        return (int) ans;
	}
}

// TLE
class Solution {
    final int MOD = 1_000_000_007;
	Set<String> set = new HashSet<>();
    public int distinctSubseqII(String s) {
        int n = s.length();
		for(int i=0;i<n;i++){
			solve(s, s.charAt(i)+"", i);
		}
		return set.size() % MOD;
    }
	
	void solve(String s, String prefix, int ind){
		if(ind == s.length())
			return;

		if(prefix!="")
			set.add(prefix);
		// add single characters to prefix
		for(int i=ind+1;i<s.length();i++){
			char t = s.charAt(i);
			if(set.contains(prefix+""+t))			
				continue;
            set.add(prefix+""+t);
			solve(s, prefix+""+t, i);
		}
		return;
	}
}