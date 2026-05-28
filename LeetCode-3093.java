// 3093. Longest Common Suffix Queries
// https://leetcode.com/problems/longest-common-suffix-queries
class Solution {
	class TrieNode{
		TrieNode[] child = new TrieNode[26];
         // best index for this suffix path
        int index = Integer.MAX_VALUE;

        // shortest length for this suffix path
        int len = Integer.MAX_VALUE;
        TrieNode(){}
        TrieNode(int index, int len){
            this.index = index;
            this.len = len;
        }
	}
	
	TrieNode root = new TrieNode();
	
    void updateNode(TrieNode node, int index, int len){

        // smaller length is better
        if(len < node.len){
            node.index = index;
            node.len = len;
        }else if(len == node.len && index < node.index){
            node.index = index;
        }
    }

	void insert(String s, int index){
		TrieNode node = root;
        updateNode(node, index, s.length());
		for(int i=s.length()-1;i>=0;i--){
			char c = s.charAt(i);
			if(node.child[c - 'a'] == null){
				node.child[c - 'a'] = new TrieNode(index, s.length());
			}
			node = node.child[c - 'a'];
            // update after moving to child node
            updateNode(node, index, s.length());
		}
	}
	
	int search(String s){
		TrieNode node = root;
		for(int i=s.length()-1;i>=0;i--){
			char c = s.charAt(i);
			if(node.child[c - 'a'] == null){
				break;
			}
			node = node.child[c - 'a'];
		}
		return node.index;
	}
	
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        for(int i=0;i<wordsContainer.length;i++){
            insert(wordsContainer[i], i);
        }
        int[] out = new int[wordsQuery.length];
		for(int i=0;i<wordsQuery.length;i++){
			out[i] = search(wordsQuery[i]);
		}
		return out;
		
    }
}