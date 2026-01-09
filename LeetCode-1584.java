// 1584. Min Cost to Connect All Points
// https://leetcode.com/problems/min-cost-to-connect-all-points
// optimized solution O(n^2)
class Solution {	
    public int minCostConnectPoints(int[][] points) {
		int n = points.length;
		int res = 0;
		int[] minCost = new int[n];
		Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[0] = 0;
        boolean[] visited = new boolean[n];  // optimized to boolean array
		for(int i=0;i<n;i++){
			int u = -1;
            for(int j=0;j<n;j++) {
                if(!visited[j] && (u == -1 || minCost[j] < minCost[u])) {
                    u = j;
                }
            }

            // 2. add it to MST
            visited[u] = true;
            res += minCost[u];

            // 3. update costs of remaining nodes
            for(int v=0; v<n;v++) {
                if(!visited[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) +
                               Math.abs(points[u][1] - points[v][1]);
                    minCost[v] = Math.min(minCost[v], dist);
                }
            }
		}
		return res;
    }
}

// first sol O(n^2 logn)
class Solution {	
    public int minCostConnectPoints(int[][] points) {
        List<List<Pair>> graph = new ArrayList<>();
		int len = points.length;
		for(int i=0;i<len;i++){
			graph.add(new ArrayList<Pair>());
		}
		// Populate graph with cost and neighbor nodes
		for(int i=0;i<len;i++){
			int x1 = points[i][0];
			int y1 = points[i][1];
			for(int j=i+1;j<len;j++){
				int x2 = points[j][0];
				int y2 = points[j][1];
				int dist = Math.abs(x1-x2) + Math.abs(y1-y2);
				graph.get(i).add(new Pair(dist, j));
				graph.get(j).add(new Pair(dist, i));
			}
		}
		// Prim's algo
		int minCost = 0;
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparing(Pair::getCost));
		minHeap.add(new Pair(0, 0));
		while(visited.size() < len){
			Pair p = minHeap.poll();
            if(visited.contains(p.node))
                continue;
            minCost += p.cost;
			visited.add(p.node);
			for(Pair neighbor : graph.get(p.node)){
                if(!visited.contains(neighbor.node))
				    minHeap.add(new Pair(neighbor.cost, neighbor.node));
			}
		}
		return minCost;
    }
	
	private class Pair{
		int cost;
		int node;
		Pair(int cost, int node){
			this.cost = cost;
			this.node = node;
		}
		public int getCost(){
			return this.cost;
		}
	}
}
