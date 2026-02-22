// 212. Word Search II
// https://leetcode.com/problems/word-search-ii/

// optimized
class TrieNode{
    TrieNode[] children = new TrieNode[26];
    boolean flag = false; // true means end of word
	String word; // non-null means end of word
}
class Solution {
	List<String> res = new ArrayList<>();
	int[] dRow = new int[]{-1,1,0,0};
	int[] dCol = new int[]{0,0,-1,1};
	public List<String> findWords(char[][] board, String[] words) {
		TrieNode root = buildTrie(words);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                solve(i, j, board, root);                
            }
        }
		
		return res;
    }
	TrieNode buildTrie(String[] words){
		TrieNode root = new TrieNode();
		for(String word : words){
			TrieNode node = root;
			for(int i=0;i<word.length();i++){
				char c = word.charAt(i);
				if(node.children[c - 'a'] == null){
					node.children[c - 'a'] = new TrieNode();
				}
				node = node.children[c - 'a'];
			}
			node.flag = true;
			node.word = word;
		}
		return root;
	}
	
	public void solve(int r, int c, char[][] board, TrieNode node){
		char ch = board[r][c];
		// if already visited or if character is not present in Trie
		if(ch == '0' || node.children[ch - 'a'] == null)
			return;
		
		node = node.children[ch - 'a'];
		if(node.word != null){
			res.add(node.word);
			node.word = null;  // prevent duplicates
		}
        
        board[r][c] = '0'; // mark visited
		
		for(int d=0;d<4;d++){
			int row = r + dRow[d];
			int col = c + dCol[d];
			if(row < 0 || row >= board.length
				|| col < 0 || col >= board[0].length
                || board[row][col] == '0'
			)
				continue;
			solve(row, col, board, node);
		}
		// backtrack
        board[r][c] = ch;
	}
}


// init
class TrieNode{
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
class Trie{
	
	Node root;
	Trie(){
		root = new Node();
	}
	void insert(String word){
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
	boolean searchWord(String word){
		Node node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				return false;
			}
			node = node.get(c);
		}
		return true;
	}
	
}

class Solution {
	List<String> res = new ArrayList<>();
	Trie trie = new Trie();
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> wordsSet = new HashSet<>();
		for(String word : words){
			trie.insert(word);
			wordsSet.add(word);
		}
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                solve(i, j, board, wordsSet, board[i][j]+"");                
            }
        }
		
		return res;
    }
	public void solve(int r, int c, char[][] board, Set<String> words, String word){
		if(words.contains(word)){
			res.add(new String(word));
            words.remove(word);
		}
        char temp = board[r][c];
        board[r][c] = '0'; // mark visited
		int[] dRow = new int[]{-1,1,0,0};
		int[] dCol = new int[]{0,0,-1,1};
		for(int d=0;d<4;d++){
			int row = r + dRow[d];
			int col = c + dCol[d];
			if(row < 0 || row >= board.length
				|| col < 0 || col >= board[0].length
                || board[row][col] == '0'
			)
				continue;
            word += board[row][col];
			if(trie.searchWord(word)){
				solve(row, col, board, words, word);
			}
            // backtrack
            word = word.substring(0,word.length()-1);
		}
        board[r][c] = temp;
	}
}


class Solution {
	List<String> res = new ArrayList<>();
	int[] dRow = new int[]{-1,1,0,0};
	int[] dCol = new int[]{0,0,-1,1};
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word; // non-null means end of word
    }
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);

        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                solve(board, i, j, root);
            }
        }
        return res;
    }
	public void solve(char[][] board, int r, int c, TrieNode node){
		char ch = board[r][c];

        if (ch == '#' || node.children[ch - 'a'] == null)
            return;

        node = node.children[ch - 'a'];

        if (node.word != null) {
            res.add(node.word);
            node.word = null; // prevent duplicates
        }

        board[r][c] = '#'; // mark visited

        for (int d = 0; d < 4; d++) {
            int row = r + dRow[d];
            int col = c + dCol[d];

            if (row < 0 || row >= board.length ||
                col < 0 || col >= board[0].length) {
                continue;
            }

            solve(board, row, col, node);
        }

        board[r][c] = ch; // backtrack
	}
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String w : words) {
            TrieNode cur = root;
            for (char ch : w.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    cur.children[idx] = new TrieNode();
                cur = cur.children[idx];
            }
            cur.word = w;
        }
        return root;
    }
}


// init mod working
class Solution {
	List<String> res = new ArrayList<>();
	int[] dRow = new int[]{-1,1,0,0};
	int[] dCol = new int[]{0,0,-1,1};
    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length;
		int m = board[0].length;
		for(String w : words) {
            boolean found = false;
			char[] word = w.toCharArray();
			for(int i=0;i<n && !found;i++){
				for(int j=0;j<m && !found;j++){
                    char orig = board[i][j];
					if(board[i][j] == word[0]){
					    if(solve(board, i, j, word, 0)){
                            found = true;
                        }
					}
				}
			}
		}
		return res;
    }
	public boolean solve(char[][] board, int r, int c, char[] word, int ind){
		if (board[r][c] != word[ind]) 
            return false;
        if(ind == word.length-1){
			res.add(new String(word));
			return true;
		}
		if(r < 0 || r >= board.length
		|| c < 0 || c >= board[r].length
		|| board[r][c] == '#'
		){
			return false;
		}
		
		char orig = board[r][c];
		board[r][c] = '#'; // mark visited
		
		for(int d=0;d<4;d++){
			int row = r + dRow[d];
			int col = c + dCol[d];
			if(row < 0 || row >= board.length
			|| col < 0 || col >= board[row].length
            || board[row][col] == '#'
			){
				continue;
			}
            if(solve(board, row, col, word, ind+1)){
                board[r][c] = orig;
                return true;
            }
		}
		board[r][c] = orig;
		return false;
	}
}

// init failed
class Solution {
	List<String> res = new ArrayList<>();
	int[] dRow = new int[]{-1,1,0,0};
	int[] dCol = new int[]{0,0,-1,1};
    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length;
		int m = board[0].length;
		for(String w : words) {
			char[] word = w.toCharArray();
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(board[i][j] == word[0]){
						if(solve(board, i, j, word, 1)){
							break;
						}
					}
				}
			}
		}
		return res;
    }
	public boolean solve(char[][] board, int r, int c, char[] word, int ind){
		if(ind == board.length){
			res.add(new String(word));
			return true;
		}
		if(r < 0 || r >= board.length
		|| c < 0 || c >= board[r].length
		|| board[r][c] == '#'
		){
			return false;
		}
		
		char orig = board[r][c];
		board[r][c] = '#'; // mark visited
		
		for(int d=0;d<4;d++){
			int row = r + dRow[d];
			int col = c + dCol[d];
			if(row < 0 || row >= board.length
			|| col < 0 || col >= board[r].length
			){
				continue;
			}
			if(board[row][col] == word[ind] && board[row][col] != '#'){
				if(solve(board, row, col, word, ind+1)){
					return true;
				}
			}
		}
		board[r][c] = orig;
		return false;
	}

}
