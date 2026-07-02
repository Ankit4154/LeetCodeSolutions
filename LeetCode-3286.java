// 3286. Find a Safe Walk Through a Grid
// https://leetcode.com/problems/find-a-safe-walk-through-a-grid
// init
class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
		int n = grid.get(0).size();
		// init maxHeap to store and process max distances first
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b)-> Integer.compare(a[2],b[2]));
		
		maxHeap.add(new int[]{0,0, grid.get(0).get(0)});
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		while(!maxHeap.isEmpty()){
				int[] p = maxHeap.poll();
				int row = p[0];
				int col = p[1];
				int currHealth = p[2];
                if(currHealth >= health)
					return false;
				if(row == m-1 && col == n-1){
					return true;
				}
				for(int i=0;i<dr.length;i++){
					int nr = row + dr[i];
					int nc = col + dc[i];
					if(nr >= m || nr < 0 || nc >= n || nc < 0)
						continue;
					if(grid.get(nr).get(nc) != -1){
						int nextHealth = currHealth + grid.get(nr).get(nc);
						grid.get(nr).set(nc, -1);
						maxHeap.add(new int[]{nr, nc, nextHealth});
					}
				}
		}
		return false;
    }
}
// another
class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        dist[0][0] = grid.get(0).get(0);
        pq.offer(new int[]{0, 0, dist[0][0]});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int row = curr[0];
            int col = curr[1];
            int loss = curr[2];

            // Ignore outdated entries
            if (loss > dist[row][col]) {
                continue;
            }

            // Destination reached
            if (row == m - 1 && col == n - 1) {
                return loss < health;
            }

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                int newLoss = loss + grid.get(nr).get(nc);

                if (newLoss < dist[nr][nc]) {
                    dist[nr][nc] = newLoss;
                    pq.offer(new int[]{nr, nc, newLoss});
                }
            }
        }

        return false;
    }
}