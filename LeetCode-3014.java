// 3014. Minimum Number of Pushes to Type Word I
// https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/
// optim, no map needed
class Solution {
    public int minimumPushes(String word) {
		int count = 1, sum = 0;
		for(int i=0;i<word.length();i++){
            sum += count;
			if((i+1) % 8 == 0)
				count++;
		}
		return sum;
    }
}

// optim, removed HashMap
class Solution {
    public int minimumPushes(String word) {
        int[] alpha = new int[26];
		int count = 1, sum = 0;
		for(int i=0;i<word.length();i++){
			char letter = word.charAt(i);
			int pos = (int)letter - 'a';
			if(alpha[pos] == 0){
				alpha[pos] = count;
				sum += count;
			}else{
				sum += alpha[pos];
			}
			if((i+1) % 8 == 0)
				count++;
		}
		return sum;
    }
}

class Solution {
    public int minimumPushes(String word) {
        Map<Character, Integer> map = new HashMap<>(26);
		int count = 1, sum = 0;
		for(int i=0;i<word.length();i++){
			char letter = word.charAt(i);
			if(!map.containsKey(letter)){
				map.put(letter, count);
				sum += count;
			}else{
				sum += map.get(letter);
			}
			if((i+1) % 8 == 0)
				count++;
		}
		return sum;
    }
}
