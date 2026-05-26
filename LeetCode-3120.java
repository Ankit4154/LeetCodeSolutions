// 3120. Count the Number of Special Characters I
// https://leetcode.com/problems/count-the-number-of-special-characters-i
// optim, instead of hashSet use boolean array
class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];

        for(char ch : word.toCharArray()){
            if(Character.isLowerCase(ch)){
                lower[ch - 'a'] = true;
            }else{
                upper[ch - 'A'] = true;
            }
        }

        int count = 0;

        for(int i = 0; i < 26; i++){
            if(lower[i] && upper[i]){
                count++;
            }
        }

        return count;
    }
}

class Solution {
    public int numberOfSpecialChars(String word) {
        Set<Character> lowSet = new HashSet<>();
        Set<Character> upSet = new HashSet<>();
        int count = 0;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(Character.isUpperCase(c)){
                upSet.add(c);
            }else
                lowSet.add(c);
        }
        for(Character c : lowSet){
            if(upSet.contains(Character.toUpperCase(c)))
                count++;
        }
        return count;
    }
}

class Solution {
    public int numberOfSpecialChars(String word) {
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
			char up = Character.toUpperCase(c);
			char low = Character.toLowerCase(c);
            System.out.println(c +""+ map);
            if(c == up){
                if(map.containsKey(low)){
					map.put(low, 1);						
				}else if(!map.containsKey(up)){
                    map.put(up, 0);
                }
            }else{
                if(map.containsKey(up)){
					map.put(up, 1);						
				}else if(!map.containsKey(low)){
                    map.put(low, 0);
                }
            }
        }
        for(Integer i : map.values()){
            if(i == 1)
				count++;
        }
        return count;
    }
}