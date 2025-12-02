// 547. Number of Provinces
// https://leetcode.com/problems/number-of-provinces/
class Solution {
    public int findCircleNum(int[][] isConnected) {
        // List<List<Integer>> adj = new ArrayList<>();
		// for(int i=0;i<isConnected.length;i++)
		// 	adj.add(new ArrayList<Integer>());
		// for(int i=0;i<isConnected.length;i++){
		// 	for(int j=0;j<isConnected[i].length;j++){
		// 		if(isConnected[i][j] == 1 && i!=j){
		// 			adj.get(i).add(j);
		// 			adj.get(j).add(i);
		// 		}
		// 	}
		// }
		int[] visited = new int[isConnected.length];
		int count = 0;
		for(int i=0;i<visited.length;i++){
			if(visited[i] == 0){
				count++;
				//visited = dfs1(i, adj, visited);
				//dfs2(i, adj, visited);
				dfs(i, isConnected, visited);
			}
		}
		return count;
    }
	
	public int[] dfs1(int node, List<List<Integer>> adj, int[] visited){
		
		if(visited[node] != 0)
			return visited;
		
		visited[node] = 1;
		
		for(int x : adj.get(node)){
			if(visited[x] == 0)
				visited = dfs1(x, adj, visited);
		}
		return visited;
	}
	// optimize 1, don't return modified array
	public void dfs2(int node, List<List<Integer>> adj, int[] visited){
		
		if(visited[node] != 0)
			return;
		
		visited[node] = 1;
		
		for(int x : adj.get(node)){
			if(visited[x] == 0)
				dfs2(x, adj, visited);
		}
	}
	// optimize 2, directly run dfs on matrix
	private void dfs(int node, int[][] graph, int[] visited) {
        visited[node] = 1;

        for(int j = 0; j < graph.length; j++){
            if(graph[node][j] == 1 && visited[j] == 0){
                dfs(j, graph, visited);
            }
        }
    }
}