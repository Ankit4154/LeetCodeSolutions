// 567. Permutation in String
// https://leetcode.com/problems/permutation-in-string/description/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Map = new int[26];
        int[] s2Map = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Map[s1.charAt(i) - 'a']++;
            s2Map[s2.charAt(i) - 'a']++;
        }

        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Map[i] == s2Map[i]) matches++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches == 26) return true;

            int left = s2.charAt(i) - 'a';
            int right = s2.charAt(i + s1.length()) - 'a';

            s2Map[right]++;
            if (s2Map[right] == s1Map[right]) {
                matches++;
            } else if (s2Map[right] == s1Map[right] + 1) {
                matches--;
            }

            s2Map[left]--;
            if (s2Map[left] == s1Map[left]) {
                matches++;
            } else if (s2Map[left] == s1Map[left] - 1) {
                matches--;
            }
        }
        return matches == 26;
    }
}


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) 
            return false;

        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();
        for (char c : s1.toCharArray()) {
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            windowMap = new HashMap<>();
            for (int j = 0; j < s1.length(); j++) {
                char ch = s2.charAt(i + j);
                windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
            }
            if (windowMap.equals(s1Map)) return true;
        }

        return false;
    }

}

// Other solutions for creating permutations of strings
// Will result in TLE for this problem though
class Solution {
    List<String> res = new ArrayList<>();
    public boolean checkInclusion(String s1, String s2) {
        //findPermutations1(s1, "");
        findPermutations2(0,s1);
        for(String s : res){
            if(s2.contains(s))
                return true;
        }
        return false;
    }

    void findPermutations1(String a, String out){
		if(a.length() == 0){
			res.add(out);
			return;
		}
        Set<Character> set = new HashSet<>();
		for(int i=0;i<a.length();i++){
            if(!set.contains(a.charAt(i))){
                String input = a.substring(0, i) + a.substring(i+1);
			    String newOut = out + a.charAt(i);
                findPermutations1(input, newOut);
            }
		}
	}

    void findPermutations2(int start, String a){
		if(start == a.length() - 1){
			res.add(a);
			return;
		}
        Set<Character> set = new HashSet<>();
		for(int i=start;i<a.length();i++){
            if(!set.contains(a.charAt(i))){
			    a = swap(start, i, a.toCharArray());
			    findPermutations2(start+1, a);
			    a = swap(i, start, a.toCharArray());
            }
		}
	}

    String swap(int a, int b, char[] ch){
        char temp = ch[a];
        ch[a] = ch[b];
        ch[b] = temp;
        return String.valueOf(ch); 
    }
}
