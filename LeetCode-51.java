// 51. N-Queens
// https://leetcode.com/problems/n-queens
// optimized, O(n!)
class Solution {
	List<List<String>> res = new ArrayList<>();
    boolean[] rows;
    boolean[] posDiag; // row + col
    boolean[] negDiag; // row - col + (n - 1)
    public List<List<String>> solveNQueens(int n) {
        rows = new boolean[n];
        posDiag = new boolean[2 * n - 1];
        negDiag = new boolean[2 * n - 1];
		char[][] board = new char[n][n];
		for(char[] ch : board){
			Arrays.fill(ch, '.');
		}

        solve(0, n, board);
        return res;
    }
	private void solve(int col, int n, char[][] board){
		if(col == n){
			List<String> list = new ArrayList<>();
			for(int i=0;i<board.length;i++){
				list.add(new String(board[i]));
			}
			res.add(list);
			return;
		}
		for(int row=0;row<board.length;row++){
            int posD = row + col;
            int negD = row - col + (n - 1);
			if(rows[row] || posDiag[posD] || negDiag[negD])
                continue;
				
            rows[row] = true;
            posDiag[posD] = true;
            negDiag[negD] = true;
        
            board[row][col] = 'Q';
            solve(col+1, n, board);
            board[row][col] = '.';
            
            rows[row] = false;
            posDiag[posD] = false;
            negDiag[negD] = false;
		}
	}
}

//initial , O(n! * n)
class Solution {
	List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
		char[][] board = new char[n][n];
		for(char[] ch : board){
			Arrays.fill(ch, '.');
		}
        solve(0, n, board);
        return res;
    }
	private void solve(int col, int n, char[][] board){
		if(col == n){
			List<String> list = new ArrayList<>();
			for(int i=0;i<board.length;i++){
				String s = "";
				for(int j=0;j<board[i].length;j++){
					s += board[i][j];
				}
				list.add(s);
			}
			res.add(list);
			return;
		}
		for(int row=0;row<board.length;row++){
			if(isValid(row, col, board)){
				board[row][col] = 'Q';
				solve(col+1, n, board);
				board[row][col] = '.';
			}
		}
	}
	private boolean isValid(int r, int c, char[][] board){
		if(r >= board.length || c >= board.length)
            return false;
        int row = r;
		int col = c;
        // upper diagonal
		while(row >= 0 && col >= 0){
			if(board[row][col] == 'Q')
				return false;
			row--;
			col--;
		}
		row = r;
        col = c;
        // lower diagonal
		while(row < board.length && col >= 0){
			if(board[row][col] == 'Q')
				return false;
			row++;
			col--;
		}
        row = r;
		col = c;
        // left 
		while(col >= 0){
			if(board[row][col] == 'Q')
				return false;
			col--;
		}
		
		return true;
		
	}
	
}