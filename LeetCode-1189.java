// 1189. Maximum Number of Balloons
// https://leetcode.com/problems/maximum-number-of-balloons
// optim 2
class Solution {
    public int maxNumberOfBalloons(String text) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;

        for (char c : text.toCharArray()) {
            if (c == 'b') b++;
            else if (c == 'a') a++;
            else if (c == 'l') l++;
            else if (c == 'o') o++;
            else if (c == 'n') n++;
        }

        return Math.min(
            Math.min(b, a),
            Math.min(l / 2, Math.min(o / 2, n))
        );
    }
}
//optim 1
class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];

        for (char c : text.toCharArray()) {
            freq[c - 'a']++;
        }

        return Math.min(
            Math.min(freq['b' - 'a'], freq['a' - 'a']),
            Math.min(
                freq['l' - 'a'] / 2,
                Math.min(freq['o' - 'a'] / 2, freq['n' - 'a'])
            )
        );
    }
}
// init
class Solution {
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> map = new HashMap<>(5);
		int min = Integer.MAX_VALUE;
		boolean oneFound = false;
		for(int i=0;i<text.length();i++){
			char c = text.charAt(i);
			if(c == 'b' || c == 'a' || c == 'l' || c == 'o' || c == 'n'){
				oneFound = true;
				if(map.containsKey(c)){
					map.put(c, map.get(c)+1);
				}else{
					map.put(c, 1);
				}
			}
		}
		if(!oneFound || map.size()!=5)
			return 0;
		for(Map.Entry<Character, Integer> entry : map.entrySet()){
			char c = entry.getKey();
			int count = entry.getValue();
			if(c == 'o' || c == 'l'){
				if(count < 2){
					return 0;
				}
				count = count / 2;
			}
			min = Math.min(min, count);
		}
        return min;
    }
}