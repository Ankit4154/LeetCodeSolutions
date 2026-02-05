// 79. Word Search
// https://leetcode.com/problems/word-search
// init
class Solution {
    public boolean exist(char[][] board, String word) {
        for(int r=0;r<board.length;r++){
            for(int c=0;c<board[r].length;c++){
                char orig = board[r][c];
                if(word.charAt(0) == orig){
                    board[r][c] = '1';
                    if(solve(r,c, board, word, 1))
                        return true;
                    board[r][c] = orig;
                }
            }
        }
        return false;
    }
	private boolean solve(int row, int col, char[][] board, String word, int used){
		if(used == word.length()){
			return true;
		}
		int[] dRow = new int[]{-1, 1, 0, 0};
		int[] dCol = new int[]{0, 0, -1, 1};
		for(int d=0;d<4;d++){
			int r = row + dRow[d];
			int c = col + dCol[d];
			if(r >= 0 && r < board.length
			&& c >= 0 && c < board[r].length){
                char orig = board[r][c];
                if(orig != '1' && word.charAt(used) == orig){
                    board[r][c] = '1';
                    if(solve(r,c, board, word, used+1))
                        return true;
                    board[r][c] = orig;
                }
            }
		}
		return false;
	}
}

// other solution multi source dfs
class Solution {
    int[] dRow = new int[]{-1, 1, 0, 0};
	int[] dCol = new int[]{0, 0, -1, 1};
    public boolean exist(char[][] board, String word) {
        for(int r=0;r<board.length;r++){
            for(int c=0;c<board[r].length;c++){
                if(word.charAt(0) == board[r][c]){
                    if(solve(r,c,board,0, word))
                        return true;
                }
            }
        }
        return false;
    }
	private boolean solve(int row, int col, char[][] board, int ind, String word){
		if(ind == word.length()){
			return true;
		}
        if(row < 0 || row >= board.length
			|| col < 0 || col >= board[row].length
            || board[row][col] != word.charAt(ind)
            )
            return false;
        char orig = board[row][col];
        board[row][col] = '#'; // mark visited
		for(int d=0;d<4;d++){
            if(solve(row+dRow[d],col+dCol[d], board,ind+1, word))
                return true;
		}
        board[row][col] = orig; // backtrack
		return false;
	}
}


