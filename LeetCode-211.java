// 211. Design Add and Search Words Data Structure
// https://leetcode.com/problems/design-add-and-search-words-data-structure
class Node{
    Node[] links = new Node[26];
    boolean flag = false;
    public Node(){}
    public boolean containsKey(char c){
        return links[c - 'a'] != null;
    }
    public Node get(char c){
        return links[c - 'a'];
    }
    public void put(char c, Node n){
        links[c - 'a'] = n;
    }
	public void setEnd(){
		this.flag = true;
	}
	public boolean getEnd(){
		return this.flag;
	}
}

class WordDictionary {

    Node root;
    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new Node());
			}
			node = node.get(c);
		}
		node.setEnd();
    }
    
    public boolean search(String word) {
        return dfs(0, word, root);
    }
	
	public boolean dfs(int start, String word, Node node){
		if(start == word.length())
			return node.getEnd();
		for(int i=start;i<word.length();i++){
			char c = word.charAt(i);
            if(c == '.'){
				for(int j=0;j<26;j++){
					if(node.links[j] != null){
						if(dfs(i + 1, word, node.links[j]))
                            return true;
					}
				}
                return false;
			}else{
				if(!node.containsKey(c)){
					return false;
				}
				node = node.get(c);
			}
		}
		return node.getEnd();
    }
	// optimized
	public boolean dfs(int index, String word, Node node){
		if(index == word.length()){
			return node.getEnd();
		}

		char c = word.charAt(index);

		// Wildcard case
		if(c == '.'){
			for(int i = 0; i < 26; i++){
				if(node.links[i] != null){
					if(dfs(index + 1, word, node.links[i])){
						return true;
					}
				}
			}
			return false;
		}

		// Normal character case
		if(!node.containsKey(c)){
			return false;
		}

		return dfs(index + 1, word, node.get(c));
	}

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */