// 345. Reverse Vowels of a String
// https://leetcode.com/problems/reverse-vowels-of-a-string/
class Solution {
    public String reverseVowels(String s) {
        int l = 0;
		int r = s.length()-1;
		char[] arr = s.toCharArray();
		boolean gotLeft = false;
		boolean gotRight = false;
		while(l < r){
			if(isVowel(arr[l]) && !gotLeft){
				gotLeft = true;
			}
			if(isVowel(arr[r]) && !gotRight){
				gotRight = true;
			}
			if(gotLeft && gotRight){
				char temp = arr[l];
				arr[l] = arr[r];
				arr[r] = temp;
				gotLeft = false;
				gotRight = false;
			}
			if(!gotLeft)
				l++;
			if(!gotRight)
				r--;
		}
		return new String(arr);
    }
	
	boolean isVowel(char c){
		return "AEIOUaeiou".indexOf(c) != -1;
	}
}