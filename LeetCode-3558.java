// 3558. Number of Ways to Assign Edge Weights I
// https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i
class Solution {
    static final long MOD = 1_000_000_007L;
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>();
		for(int i=0;i<=n+1;i++){
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<n;i++){
			int u = edges[i][0];
			int v = edges[i][1];
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// find maxDepth
		Queue<int[]> q = new LinkedList<>();
        boolean[] vis = new boolean[n + 2];
		q.add(new int[]{1,0}); // node,cost
        vis[1] = true;
		int maxDepth = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int[] curr = q.poll();
				int node = curr[0];
				int depth = curr[1];
				maxDepth = Math.max(maxDepth, depth);
				for(Integer nei : graph.get(node)){
                    if(!vis[nei]){
                        vis[nei] = true;
                        q.add(new int[]{nei, depth+1});
                    }
				}
			}
		}
		return (int) modPow(2, maxDepth - 1);
    }
	
	private long modPow(long base, long exp) {
        long res = 1;

        while(exp > 0) {
            if((exp & 1) == 1) {
                res = (res * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

	    return res;
    }
}