// 52. N-Queens II
// https://leetcode.com/problems/n-queens-ii/
class Solution {
    int count = 0;
    boolean[] rows;
    boolean[] posDiag; // row + col
    boolean[] negDiag; // row - col + (n - 1)
    public int totalNQueens(int n) {
        rows = new boolean[n];
        posDiag = new boolean[2 * n - 1];
        negDiag = new boolean[2 * n - 1];

        solve(0, n);
        return count;
    }
	private void solve(int col, int n){
		if(col == n){
			count++;
			return;
		}
		for(int row=0;row<n;row++){
            int posD = row + col;
            int negD = row - col + (n - 1);
			if(rows[row] || posDiag[posD] || negDiag[negD])
                continue;
				
            rows[row] = true;
            posDiag[posD] = true;
            negDiag[negD] = true;
        
            solve(col+1, n);
            
            rows[row] = false;
            posDiag[posD] = false;
            negDiag[negD] = false;
		}
	}
}