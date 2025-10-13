// 2273. Find Resultant Array After Removing Anagrams
// https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/
// add first word to the list
// compare each next word with the stored first word
// if it's an anagram, then move ahead for next word
// else add that word to result list,i++
// and compare next elements with that word
// for Anagram check take character map of a-z(len 26)
// increment each character count for first word
// decrement each character count for second word
// traverse the each character count
// if any character count != 0  return false
class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        result.add(words[0]);
        for(int i=1; i<words.length; i++){
            if(!isAnagram(words[i], words[i - 1])) {
                result.add(words[i]);
            }
        }
        return result;
    }
    private boolean isAnagram(String s1, String s2) {
        if (s1.length()!=s2.length())
        return false;

        int[] freq = new int[26];
        for(char c : s1.toCharArray()) freq[c - 'a']++;
        for(char c : s2.toCharArray()) freq[c - 'a']--;

        for(int count : freq) {
            if(count != 0)
                return false;
        }
        return true;
    }
}

class Solution {
    public List<String> removeAnagrams(String[] words) {
        int left = 0, right = 1;
        List<String> list = new ArrayList<>();
		if(words.length == 1){
            list.add(words[0]);
            return list;
        }
        while(right < words.length){
			if(words[right].length() != words[left].length()){
				if(!list.contains(words[left]))
                    list.add(words[left]);
				list.add(words[right]);
				left = right;
			}else{
				Map<Character, Integer> map = new HashMap<>();
				String s1 = words[left];
				for(int i=0;i<s1.length();i++){
					map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0)+1);
				}
				Map<Character, Integer> map2 = new HashMap<>();
				String s2 = words[right];
				for(int i=0;i<s2.length();i++){
					map2.put(s2.charAt(i), map2.getOrDefault(s2.charAt(i), 0)+1);
				}
				if(map.equals(map2)){
                   if (!list.contains(words[left]))
                        list.add(words[left]);                    
                }else{
					if(!list.contains(words[left]))
                        list.add(words[left]);
					list.add(words[right]);
					left = right;
				}
			}
			right++;
		}
		return list;
    }
}