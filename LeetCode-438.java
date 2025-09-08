// 438. Find All Anagrams in a String
// https://leetcode.com/problems/find-all-anagrams-in-a-string
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s.length() < p.length()) return result;
        
        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Count frequency of each character in p
        for (char ch : p.toCharArray()) {
            pCount[ch - 'a']++;
        }

        // Sliding window over s
        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;

            // Remove the character left outside the window
            if (i >= p.length()) {
                sCount[s.charAt(i - p.length()) - 'a']--;
            }

            // Compare window with target pattern
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }
}