// 3838. Weighted Word Mapping
// https://leetcode.com/problems/weighted-word-mapping
// optim
class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
		char[] arr = new char[words.length];
		int count = 0;
		for(String word : words){
            int sum = 0;
			for(int i=0;i<word.length();i++){
				char c = word.charAt(i);
				sum += weights[c - 'a'];
			}
            arr[count++] = (char)('z' - (sum % 26));
		}
		return new String(arr);
    }
}
// init
class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
		char[] arr = new char[words.length];
		char[] revAlpha = new char[26];
		char ch = 'z';
		for(int i=0;i<26;i++){
			revAlpha[i] = ch--;
		}
		int count = 0;
		for(String word : words){
            int sum = 0;
			for(int i=0;i<word.length();i++){
				char c = word.charAt(i);
				sum += weights[c - 'a'];
			}
            arr[count++] = revAlpha[sum % 26];
		}
		return new String(arr);
    }
}