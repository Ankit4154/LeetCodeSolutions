// 139. Word Break
// https://leetcode.com/problems/word-break/
// HashSet + Recursion + Memoization
class Solution {
    Set<String> set;
    Boolean[] memo;   // null = not computed

    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        memo = new Boolean[s.length()];
        return solve(s, 0);
    }
    private boolean solve(String s, int start) {
        if(start == s.length()) {
            return true;
        }

        if(memo[start] != null) {
            return memo[start];
        }

        for(int end = start + 1; end <= s.length(); end++){
            if(set.contains(s.substring(start, end)) 
                && solve(s, end)) {
                memo[start] = true;
                return true;
            }
        }

        memo[start] = false;
        return false;
    }
}

// Trie + DP solution
class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    TrieNode root = new TrieNode();

    public boolean wordBreak(String s, List<String> wordDict) {
        buildTrie(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); i++) {

            if (!dp[i]) continue;  // skip invalid starting points

            TrieNode node = root;

            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);

                if (node.children[c - 'a'] == null) {
                    break;  // no further match possible
                }

                node = node.children[c - 'a'];

                if (node.isWord) {
                    dp[j + 1] = true;
                }
            }
        }

        return dp[s.length()];
    }

    private void buildTrie(List<String> wordDict) {
        for (String word : wordDict) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }
    }
}


// init

class TrieNode{
	TrieNode[] children = new TrieNode[26];
	String word; // non-null means end of word.
}

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>();
        int minLen = Integer.MAX_VALUE;
		for(String word : wordDict){
			set.add(word);
            minLen = Math.min(word.length(),minLen);
		}
		int len = s.length();
		for(int l=0, r=0; r < s.length(); r++){
			String word = s.substring(l, r+1);
            int orig = len;
            int ol = l;
			if(set.contains(word)){
				len -= r-l+1;
				l = r+1;
                if(len != 0 && len <minLen){
                    len = orig;
                    l = ol;
                    continue;
                }
                    
			}
		}
		if(len == 0)
			return true;
		return false;
        // TrieNode root = buildTrie(wordDict);
		// int len = s.length();
		// for(int l=0, r=0; r < s.length(); r++){
		// 	String word = s.substring(l, r+1);
		// 	if(containsWord(word, root)){
		// 		len -= r-l+1;
		// 		l = r+1;
        //     System.out.println("contains : "+word+" "+len+" "+l+" "+r);
		// 	}
		// }
		// if(len == 0)
		// 	return true;
		// return false;
		
    }
	boolean containsWord(String word, TrieNode root){
		TrieNode node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(node.children[c-'a']==null)
				return false;
			node = node.children[c-'a'];
		}
        if(word.equals(node.word))
		    return true;
        return false;
	}
	TrieNode buildTrie(List<String> wordDict){
		TrieNode root = new TrieNode();
		for(String word : wordDict){
			TrieNode curr = root;
			for(int i=0;i<word.length();i++){
				char c = word.charAt(i);
				int ind = c - 'a';
				if(curr.children[ind] == null)
					curr.children[ind] = new TrieNode();
				curr = curr.children[ind];
			}
			curr.word = word;
		}
        return root;
	}
}