// 269. Alien Dictionary
// https://leetcode.com/problems/alien-dictionary/description/
class Solution {
	Map<Character, Set<Character>> graph = new HashMap<>();
	List<Character> result = new ArrayList<>();
	Map<Character, Boolean> visited = new HashMap<>();
    public String foreignDictionary(String[] words) {
		// store all characters in a set
		// iterate over each characters and populate the graph
		// run dfs over graph to traverse and get the unique string
		
		for(String word : words){
			for(char c : word.toCharArray())
				graph.putIfAbsent(c, new HashSet<>());
		}
		
		for(int i=0;i<words.length-1;i++){
			String w1 = words[i];
			String w2 = words[i+1];
			if(w1.length() > w2.length() && w1.contains(w2)){
				return "";
			}
			int minLen = Math.min(w1.length(), w2.length());
			for(int j=0;j<minLen;j++){
				if(w1.charAt(j) != w2.charAt(j)){
					graph.get(w1.charAt(j)).add(w2.charAt(j));
					break;
				}
			}
		}
		
		for(char c : graph.keySet()){
			if(dfs(c))
				return "";
		}
		Collections.reverse(result);
		StringBuilder sb = new StringBuilder();
        for(char c : result) {
            sb.append(c);
        }
        return sb.toString();
		
    }
	
	public boolean dfs(char ch){
		if(visited.containsKey(ch)) {
            return visited.get(ch);
        }
		
		visited.put(ch, true);
		for(char next : graph.get(ch)){
			if(dfs(next))
				return true;
		}
		visited.put(ch, false);
		result.add(ch);
		return false;
	}
}
