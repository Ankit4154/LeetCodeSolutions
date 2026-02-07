// 132. Palindrome Partitioning II
// https://leetcode.com/problems/palindrome-partitioning-ii/
// Time : O(N^2), Space : O(N^2)
class Solution {
	int[] dp;
	boolean[][] pal;
    public int minCut(String s){
		int n = s.length();
		pal = new boolean[n][n];
		for(int i = n - 1; i >= 0; i--){
			for(int j = i; j < n; j++){
				if(s.charAt(i) == s.charAt(j)){
					if(j - i <= 2 || pal[i + 1][j - 1]){
						pal[i][j] = true;
					}
				}
			}
		}
		dp = new int[n];
		Arrays.fill(dp,-1);
        return solve(0, s);
    }
	public int solve(int ind, String s){
		if(ind == s.length()){
			return -1; // reached end of the string, no cut after last partition
		}
		if(dp[ind] != -1)
			return dp[ind];
		int min = Integer.MAX_VALUE;
		for(int i=ind;i<s.length();i++){
			if(pal[ind][i]){
                min = Math.min(min, 1+solve(i+1, s));
			}
		}
		return dp[ind] = min;
	}
	private boolean isPalindrome(String s){
		if(s.length() == 1)
			return true;
		int left = 0, right = s.length()-1;
		while(left < right){
			if(s.charAt(left)!=s.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}
}

// dp memoization, Time : O(N^3), Space : O(N)
class Solution {
	int[] dp;
    public int minCut(String s){
		dp = new int[s.length()];
		Arrays.fill(dp,-1);
        return solve(0, s);
    }
	public int solve(int ind, String s){
		if(ind == s.length()){
			return -1; // reached end of the string, no cut after last partition
		}
		if(dp[ind] != -1)
			return dp[ind];
		int min = Integer.MAX_VALUE;
		for(int i=ind;i<s.length();i++){
			String part = s.substring(ind, i+1);
			if(isPalindrome(part)){
                min = Math.min(min, 1+solve(i+1, s));
			}
		}
		return dp[ind] = min;
	}
	private boolean isPalindrome(String s){
		if(s.length() == 1)
			return true;
		int left = 0, right = s.length()-1;
		while(left < right){
			if(s.charAt(left)!=s.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}
}


//init , TLE
class Solution {
    int min = Integer.MAX_VALUE;
    public int minCut(String s) {
        solve(0, s, new ArrayList<>());
		return min;
    }
	public void solve(int ind, String s, List<String> list){
		if(ind == s.length()){
            min = Math.min(min, list.size()-1);
			return;
		}
		for(int i=ind;i<s.length();i++){
			String part = s.substring(ind, i+1);
			if(isPalindrome(part)){
				list.add(part);
                solve(i+1, s, list);
				list.remove(list.size()-1);
			}
		}
	}
	private boolean isPalindrome(String s){
		if(s.length() == 1)
			return true;
		int left = 0, right = s.length()-1;
		while(left < right){
			if(s.charAt(left)!=s.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}
}
