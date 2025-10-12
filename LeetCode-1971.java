// 1971. Find if Path Exists in Graph
// https://leetcode.com/problems/find-if-path-exists-in-graph
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // BFS implementation using a queue to solve the problem.
        // first initialize the graph with given nodes
        // start traversing the graph from source node
        // while traversing if there exists a path between 
        // source and destination node then we will reach destination
        // node for sure and return a true.
        // else if the queue becomes empty then return false
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();
		int[] visited = new int[n];
        if(edges.length == 0 && source == destination)
            return true;
		for(int i=0;i<n;i++){
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<edges.length;i++){
			graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
		}
		q.add(source);
		while(!q.isEmpty()){
			int i = q.remove();
			if(visited[i] == 0){
				visited[i] = 1;
				for(int x : graph.get(i)){
                    if(x == destination)
                        return true;
                    if(visited[x] == 0){
                        q.add(x);
                    }
				}
			}
		}
        return false;
    }
}