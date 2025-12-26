// 323. Number of Connected Components in an Undirected Graph
// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        // init graph
		for(int i=0;i<n;i++){
			graph.add(new ArrayList<>());
		}
		// populate graph
		for(int i=0;i<edges.length;i++){
			int u = edges[i][0];
			int v = edges[i][1];
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		//Set<Integer> visited = new HashSet<>();
		boolean[] visited = new boolean[n];
		int comp = 0;
		// bfs on graph nodes
		for(int i=0;i<n;i++){
			//if(!visited.contains(i)){
			if(!visited[i]){
				comp++;
				bfs(i, graph, visited);
			}
		}
		return comp;
    }
	
	public void bfs(int n, List<List<Integer>> graph, boolean[] visited){
		Queue<Integer> q = new ArrayDeque<>();
		q.add(n);
		while(!q.isEmpty()){
			Integer k = q.poll();
			List<Integer> list = graph.get(k);
			for(Integer x : list){
				if(!visited[x]){
					q.add(x);
					visited[x] = true;
				}
			}
		}
	}
}

