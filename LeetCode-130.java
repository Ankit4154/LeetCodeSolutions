// 130. Surrounded Regions
// https://leetcode.com/problems/surrounded-regions
class Solution {
    public void solve(char[][] board) {
        int row = board.length;
		int col = board[0].length;
		// traverse 1st and last row
		for(int i=0;i<col;i++){
            bfs(0, i, board);
            bfs(row-1, i, board);
		}
		// traverse 1st and last column
		for(int i=0;i<row;i++){
            bfs(i, 0, board);
            bfs(i, col-1, board);
		}
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
                if(board[i][j] == 'O'){
					board[i][j] = 'X';
				}else if(board[i][j] == 'T'){
                    board[i][j] = 'O';
                }
			}
		}
		
    }
	
	public void bfs(int i, int j, char[][] board){
		if(board[i][j] == 'X')
			return;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{i,j});
        board[i][j] = 'T';
		while(!q.isEmpty()){
			int[] p = q.poll();
			int row = p[0];
			int col = p[1];
			int[] dRow = {-1,1,0,0};
			int[] dCol = {0,0,-1,1};
			for(int d=0;d<4;d++){
				int cRow = row + dRow[d];
				int cCol = col + dCol[d];
				if(cRow >=0 && cRow < board.length
				   && cCol >= 0 && cCol < board[0].length
				   && board[cRow][cCol] == 'O'
				){
					q.add(new int[]{cRow,cCol});
                    board[cRow][cCol] = 'T';
				}
			}
		}
	}
}


// class Solution {
//     public void solve(char[][] board) {
//         if(board == null || board.length == 0) 
//             return;

//         int m = board.length, n = board[0].length;

//         // Step 1: Mark border-connected 'O's
//         for(int i = 0; i < m; i++) {
//             bfs(board, i, 0);
//             bfs(board, i, n - 1);
//         }
//         for(int j = 0; j < n; j++) {
//             bfs(board, 0, j);
//             bfs(board, m - 1, j);
//         }

//         // Step 2: Flip remaining O's and restore marked ones
//         for(int i = 0; i < m; i++) {
//             for(int j = 0; j < n; j++) {
//                 if(board[i][j] == 'O')
//                     board[i][j] = 'X';
//                 else if(board[i][j] == '#')
//                     board[i][j] = 'O';
//             }
//         }
//     }

//     private void bfs(char[][] board, int r, int c) {
//         if(board[r][c] != 'O') 
//             return;

//         int m = board.length, n = board[0].length;
//         Queue<int[]> q = new ArrayDeque<>();
//         q.offer(new int[]{r, c});
//         board[r][c] = '#';

//         int[] dr = {1, -1, 0, 0};
//         int[] dc = {0, 0, 1, -1};

//         while(!q.isEmpty()) {
//             int[] cur = q.poll();
//             for(int k = 0; k < 4; k++) {
//                 int nr = cur[0] + dr[k];
//                 int nc = cur[1] + dc[k];

//                 if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'O') {
//                     board[nr][nc] = '#';
//                     q.offer(new int[]{nr, nc});
//                 }
//             }
//         }
//     }

// }
