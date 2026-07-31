// 3016. Minimum Number of Pushes to Type Word II
// https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii
// optim
class Solution {
    public int minimumPushes(String word) {
		int[] alpha = new int[26];
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			alpha[c-'a']++;
		}
		Arrays.sort(alpha);
		int k = 0, count = 1, sum = 0;
		for(int i=25;i>=0 && alpha[i] > 0;i--){
			sum += alpha[i] * count;
			k++;
			if(k % 8 == 0)
				count++;
		}
		return sum;
    }
}

// init
class Solution {
    public int minimumPushes(String word) {
		Map<Character, Integer> map = new HashMap<>();
		Map<Integer, Set<Character>> treeMap = new TreeMap<>(Collections.reverseOrder());
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			map.put(c, map.getOrDefault(c, 0)+1);
		}
		for(Map.Entry<Character, Integer> entry : map.entrySet()){
			int freq = entry.getValue();
			char ch = entry.getKey();
			if(!treeMap.containsKey(freq)){
				Set<Character> set = new HashSet<>();
				set.add(ch);
				treeMap.put(freq, set);
			}else{
				treeMap.get(freq).add(ch);
			}
		}
        map.clear();
		int k = 0, count = 1;
		for(Set<Character> set : treeMap.values()){
			for(Character c : set){
				if(!map.containsKey(c)){
					map.put(c, count);
					k++;
					if(k % 8 == 0)
						count++;
				}
			}
		}

		int sum = 0;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			sum += map.get(c);
		}
		return sum;
    }
}