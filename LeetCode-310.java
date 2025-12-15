// 310. Minimum Height Trees
// https://leetcode.com/problems/minimum-height-trees
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1){
            return List.of(0);
        }
		List<List<Integer>> graph = new ArrayList<>(n);
        int[] degree = new int[n];
		for(int i=0;i<n;i++){
			graph.add(new ArrayList<Integer>());
		}
        for(int i=0;i<edges.length;i++){
			int u = edges[i][0];
			int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            degree[u]++;
            degree[v]++;
		}

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(degree[i] == 1){
                queue.offer(i);
            }
        }
        int remainingNodes = n;

        while(remainingNodes > 2){
            int size = queue.size();
            remainingNodes -= size;

            for(int i = 0; i < size; i++){
                int leaf = queue.poll();

                for(int neighbor : graph.get(leaf)){
                    degree[neighbor]--;
                    if(degree[neighbor] == 1){
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return new ArrayList<>(queue);
    }
}