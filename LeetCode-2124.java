// 2124. Check if All A's Appears Before All B's
// https://leetcode.com/problems/check-if-all-as-appears-before-all-bs
class Solution {
    public boolean checkString(String s) {
		int n = s.length();
		boolean first = false;
		for(int i=n-1;i>=0;i--){
			char c = s.charAt(i);
			if(c == 'b' && first)
				return false;
			if(c == 'a' && !first){
				first = true;
			}
		}
		return true;
    }
}