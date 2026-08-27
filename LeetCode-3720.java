// 3720. Lexicographically Smallest Permutation Greater Than Target
// https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target
class Solution {
    public String lexGreaterPermutation(String s, String target) {
		int n = s.length();
		int[] alpha = new int[26];
		for(int i=0;i<n;i++){
			int pos = s.charAt(i) - 'a';
			alpha[pos]++;
		}
		StringBuilder prefix = new StringBuilder();
		int bestPos = -1;
		int bestChar = -1;
		for(int i=0;i<n;i++){
			char tc = target.charAt(i);
			int pos = tc-'a';
			// can find best next character for this position
			for(int j=pos+1;j<26;j++){
				if(alpha[j] > 0){
					bestPos = i;
					bestChar = j;
					break;
				}
			}
			// if can't match with the current target character, exit
			if(alpha[pos] == 0)
				break;
			
			// keep the character and reduce frequency
			prefix.append(tc);
			alpha[pos]--;
		}
		// no greater character found
		if(bestPos == -1)
			return "";
		
		// We found a greater character so now restore
		// any used up frequency from best position onwards
		for(int i=bestPos;i<prefix.length();i++){
			alpha[prefix.charAt(i)-'a']++;
		}
		
		StringBuilder out = new StringBuilder();
		
		// keep prefix before best position
		out.append(prefix.substring(0,bestPos));
		
		// add best character
		out.append((char)('a'+bestChar));
		alpha[bestChar]--;
		
		// put all remaining characters in sorted order
		for(int j=0;j<26;j++){
			while(alpha[j] > 0){
				out.append((char)(j + 'a'));
				alpha[j]--;
			}
		}
		
        return out.toString();
    }
}