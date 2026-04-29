// 1668. Maximum Repeating Substring
// https://leetcode.com/problems/maximum-repeating-substring

class Solution {
    public int maxRepeating(String sequence, String word) {
		String curr = word;
        int count = 0;

        while(sequence.contains(curr)) {
            count++;
            curr += word;
        }
        return count;
    }
}