// 744. Find Smallest Letter Greater Than Target
// https://leetcode.com/problems/find-smallest-letter-greater-than-target/
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        char ans = 'z';
        if(target == ans)
            return letters[0];
        boolean updated = false;
		for(char c : letters){
			if(c > target){
				if(c <= ans){
                    updated = true;
					ans = c;
				}
			}
		}
        if(!updated)
            return letters[0];
		return ans;
    }
}