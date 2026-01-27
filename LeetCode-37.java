// 37. Sudoku Solver
// https://leetcode.com/problems/sudoku-solver/
class Solution {
	List<List<String>> res = new ArrayList<>();
	char[][] ans;
    public void solveSudoku(char[][] board) {
		ans = new char[board.length][board[0].length];
        solve(0, 0, board);
		for(char[] c : ans){
			System.out.println(c);
		}
    }
	private boolean solve(int row, int col, char[][] board){
		if(row == board.length){
			return true;
		}
		
		if(col == board.length)
			return solve(row+1, 0, board);
		
		if(board[row][col] != '.')
			return solve(row, col+1, board);
		for(char n='1';n<='9';n++){
			if(checkValid(row, col, n, board)){
				board[row][col] = n;
				boolean res = solve(row, col+1, board);
				if(res)
					return true;
			}
		}
		board[row][col] = '.';
		return false;
	}
	private boolean checkValid(int row, int col, char ch, char[][] board){
		int n = board.length;
		int m = board[col].length;

		int r = 0;
		int c = col;
		while(r < n){
			if(board[r][c] == ch)
				return false;
			r++;
		}
		r = row;
		c = 0;
		while(c < m){
			if(board[r][c] == ch)
				return false;
			c++;
		}
		int startRowGrid = (row/3) * 3;
		int startColGrid = (col/3) * 3;
		for(int i=startRowGrid;i<startRowGrid+3;i++){
			for(int j=startColGrid;j<startColGrid+3;j++){
				if(board[i][j] == ch)
					return false;
			}
		}
		return true;
	}
}