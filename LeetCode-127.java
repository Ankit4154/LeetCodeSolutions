// 127. Word Ladder
// https://leetcode.com/problems/word-ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //if(!wordList.contains(endWord))
		//	return 0;
		// Optimization for O(1) lookup
		Set<String> set = new HashSet<>(wordList);
		if(!set.contains(endWord))
			return 0;
		Map<String, List<String>> patternMap = new HashMap<>();
        set.add(beginWord);
		for(String word : set){
			for(int i=0;i<word.length();i++){
				String pattern = word.substring(0,i) + "*" + word.substring(i+1);
				patternMap.putIfAbsent(pattern, new ArrayList<>());
                patternMap.get(pattern).add(word);
			}
		}
		Queue<String> q = new ArrayDeque<>();
		q.add(beginWord);
		set.remove(beginWord); // if contains, remove (little optimization)
        int count = 1;
		while(!q.isEmpty()){
			int size = q.size();
			for(int s=0;s<size;s++){
				String currentWord = q.poll();
				if(currentWord.equals(endWord))
					return count;
				for(int i=0;i<currentWord.length();i++){
					String pattern = currentWord.substring(0,i) + "*" + currentWord.substring(i+1);
					List<String> neighbours = patternMap.getOrDefault(pattern, new ArrayList<>());
					for(String next : neighbours){
						if(set.contains(next)){
							q.add(next);
							set.remove(next);
						}
					}
                    patternMap.put(pattern, new ArrayList<>());
				}
			}
			count++;
		}
		return 0;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //if(!wordList.contains(endWord))
		//	return 0;
		// Optimization for O(1) lookup
		Set<String> set = new HashSet<>(wordList);
		if(!set.contains(endWord))
			return 0;
		Queue<String> q = new ArrayDeque<>();
		q.add(beginWord);
		set.remove(beginWord); // if contains, remove (little optimization)
        int count = 1;
		while(!q.isEmpty()){
			int size = q.size();
			for(int s=0;s<size;s++){
				String currentWord = q.poll();
				if(currentWord.equals(endWord))
					return count;
				char[] currWord = currentWord.toCharArray();
				for(int i=0;i<currWord.length;i++){
					char original = currWord[i];
					for(char c='a';c<='z';c++){
						if(c != original){
							currWord[i] = c;
							String newWord = new String(currWord);
							// if(wordList.contains(newWord)){
							//	q.add(newWord);
							//	wordList.remove(newWord);
							// }
							// Optimization for O(1) lookup
							if(set.contains(newWord)){
								q.add(newWord);
								set.remove(newWord);
							}
							currWord[i] = original;
						}
					}
				}
			}
			count++;
		}
		return 0;
    }
}