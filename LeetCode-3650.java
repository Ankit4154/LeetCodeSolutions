// 3650. Minimum Cost Path with Edge Reversals
// https://leetcode.com/problems/minimum-cost-path-with-edge-reversals/
class Solution{
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
		for(int i=0;i<n;i++){
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<edges.length;i++){
			graph.get(edges[i][0]).add(new int[]{edges[i][1], edges[i][2]});
			graph.get(edges[i][1]).add(new int[]{edges[i][0], 2*edges[i][2]});
		}
		
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(a->a[1]));
		minHeap.add(new int[]{0,0});
		boolean[] visited = new boolean[n];
		while(!minHeap.isEmpty()){
			int[] p = minHeap.poll();
			int node = p[0];
			int cost = p[1];
            if(visited[node])
                continue;
			visited[node] = true;
			if(node == n-1)
				return cost;
            
			for(int[] k : graph.get(node)){
				int u = k[0];
				int w = k[1];
				if(!visited[u]){
					minHeap.add(new int[]{u, w+cost});
				}
			}
		}
        return -1;
    }
}