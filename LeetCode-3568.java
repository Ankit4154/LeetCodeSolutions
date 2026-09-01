// 3568. Minimum Moves to Clean the Classroom
// https://leetcode.com/problems/minimum-moves-to-clean-the-classroom
class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        char[][] grid = new char[m][n];

        int sr = 0, sc = 0;
        int litterCount = 0;

        for(int i = 0; i < m; i++){
            grid[i] = classroom[i].toCharArray();

            for(int j = 0; j < n; j++){
                if(grid[i][j] == 'S'){
                    sr = i;
                    sc = j;
                } else if(grid[i][j] == 'L'){
                    litterCount++;
                }
            }
        }

        if(litterCount == 0)
            return 0;

        // Assign a bit to every litter cell.
        int[][] litterId = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                litterId[i][j] = -1;
            }
        }

        int id = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 'L'){
                    litterId[i][j] = id++;
                }
            }
        }

        int totalMasks = 1 << litterCount;

        // maxEnergy[r][c][mask] = maximum energy with which
        // we have reached this state.
        int[][][] maxEnergy = new int[m][n][totalMasks];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int mask = 0; mask < totalMasks; mask++){
                    maxEnergy[i][j][mask] = -1;
                }
            }
        }

        Queue<State> queue = new ArrayDeque<>();

        maxEnergy[sr][sc][0] = energy;
        queue.offer(new State(sr, sc, 0, energy, 0));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int targetMask = totalMasks - 1;

        while(!queue.isEmpty()){
            State curr = queue.poll();

            int r = curr.r;
            int c = curr.c;
            int mask = curr.mask;
            int currEnergy = curr.energy;
            int moves = curr.moves;

            if(mask == targetMask)
                return moves;

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                if(grid[nr][nc] == 'X')
                    continue;

                int newEnergy = currEnergy - 1;

                if(newEnergy < 0)
                    continue;

                int newMask = mask;

                if(grid[nr][nc] == 'L'){
                    newMask |= (1 << litterId[nr][nc]);
                }

                if(grid[nr][nc] == 'R'){
                    newEnergy = energy;
                }

                // Same position + same litter collection,
                // but we already reached it with equal or more energy.
                if(newEnergy <= maxEnergy[nr][nc][newMask])
                    continue;

                maxEnergy[nr][nc][newMask] = newEnergy;

                queue.offer(
                    new State(nr, nc, newMask, newEnergy, moves + 1)
                );
            }
        }

        return -1;
    }

    static class State {
        int r;
        int c;
        int mask;
        int energy;
        int moves;

        State(int r, int c, int mask, int energy, int moves){
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }
}
// TLE
class Solution {
    int min = Integer.MAX_VALUE;
    int baseEnergy = 0;
    int totalLitter = 0;
    int m = 0, n = 0;

    HashMap<String, Integer> visited = new HashMap<>();

    public int minMoves(String[] classroom, int energy) {
        min = Integer.MAX_VALUE;
        totalLitter = 0;
        visited.clear();

        m = classroom.length;
        n = classroom[0].length();

        char[][] croom = new char[m][n];

        int startRow = -1;
        int startCol = -1;

        for (int i = 0; i < m; i++) {
            croom[i] = classroom[i].toCharArray();

            for (int j = 0; j < n; j++) {
                if (croom[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (croom[i][j] == 'L') {
                    totalLitter++;
                }
            }
        }

        if (totalLitter == 0)
            return 0;

        baseEnergy = energy;

        // Give every litter cell an ID.
        int[][] litterId = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                litterId[i][j] = -1;
            }
        }

        int id = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (croom[i][j] == 'L') {
                    litterId[i][j] = id++;
                }
            }
        }

        boolean[] collected = new boolean[totalLitter];

        solve(
            croom,
            litterId,
            energy,
            0,
            0,
            startRow,
            startCol,
            collected
        );

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    void solve(
        char[][] croom,
        int[][] litterId,
        int energy,
        int cost,
        int litter,
        int r,
        int c,
        boolean[] collected
    ) {
        if (litter == totalLitter) {
            min = Math.min(min, cost);
            return;
        }

        if (cost >= min)
            return;

        if (energy == 0)
            return;

        String key = createKey(r, c, energy, collected);

        Integer previousCost = visited.get(key);

        if (previousCost != null && previousCost <= cost)
            return;

        visited.put(key, cost);

        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};

        for (int d = 0; d < 4; d++) {
            int row = r + drow[d];
            int col = c + dcol[d];

            if (row < 0 || row >= m ||
                col < 0 || col >= n)
                continue;

            if (croom[row][col] == 'X')
                continue;

            int newEnergy = energy - 1;

            if (newEnergy < 0)
                continue;

            // R restores energy AFTER making the move.
            if (croom[row][col] == 'R') {
                newEnergy = baseEnergy;
            }

            boolean picked = false;
            int litterIdValue = -1;

            if (croom[row][col] == 'L') {
                litterIdValue = litterId[row][col];

                if (!collected[litterIdValue]) {
                    collected[litterIdValue] = true;
                    litter++;
                    picked = true;
                }
            }

            solve(
                croom,
                litterId,
                newEnergy,
                cost + 1,
                litter,
                row,
                col,
                collected
            );

            // Backtrack.
            if (picked) {
                collected[litterIdValue] = false;
                litter--;
            }
        }
    }

    String createKey(
        int r,
        int c,
        int energy,
        boolean[] collected
    ) {
        StringBuilder sb = new StringBuilder();

        sb.append(r)
          .append(',')
          .append(c)
          .append(',')
          .append(energy)
          .append(':');

        for (int i = 0; i < collected.length; i++) {
            sb.append(collected[i] ? '1' : '0');
        }

        return sb.toString();
    }
}
// init
class Solution {
	int min = Integer.MAX_VALUE;
	int baseEnergy = 0;
	int totalLitter = 0;
    int m = 0, n = 0;
    public int minMoves(String[] classroom, int energy) {
        m = classroom.length;
        char[][] croom = new char[m][];
		baseEnergy = energy;
		int k = 0;
		for(String room : classroom){
			croom[k++] = room.toCharArray();
		}
		int row = -1, col = -1;
        n = croom[0].length;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(croom[i][j] == 'L'){
					totalLitter++;
				}
				if(croom[i][j] == 'S'){
					row = i;
					col = j;
				}
			}
		}
        if(totalLitter == 0)
            return 0;
        boolean[][] collected = new boolean[m][n];
		solve(croom, energy, 0, 0, row, col, collected);
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
	
	void solve(char[][] croom, int energy, int cost, int litter, int r, int c, boolean[][] collected){
        if(litter == totalLitter){
            min = Math.min(cost, min);
            return; // one possible solution
        }
		
		if(cost >= min)
			return;  // uneeded long exploration path
		
        if(energy == 0)
			return; // not possible
        
		int[] drow = {-1, 1, 0, 0};
		int[] dcol = {0, 0, -1, 1};
		for(int d=0;d<4;d++){
			int row = r + drow[d];
			int col = c + dcol[d];
            System.out.println("bef : "+row +" "+ col+" lit : "+ litter +" energy : "+energy +" cost: "+cost);
			if(row < 0 || row >= croom.length || col < 0 || col >= croom[row].length)
				continue;
			if(croom[row][col] == 'X')
				continue;
			
			int newEnergy = energy - 1;
			if(newEnergy < 0)
				continue; // not possible
			if(croom[row][col] == 'R'){
				newEnergy = baseEnergy;
			}
			boolean picked = false;
			
			if(croom[row][col] == 'L' && !collected[row][col]){
				litter++;
				collected[row][col] = true;
				picked = true;
			}
			
			solve(croom, newEnergy, cost+1, litter, row, col, collected);
			// Backtrack
			if(picked)
				collected[row][col] = false;
		}
		return;
	}
}