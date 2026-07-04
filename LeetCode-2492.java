// 2492. Minimum Score of a Path Between Two Cities
// https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities
class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
		for(int i=0;i<=n;i++){
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<roads.length;i++){
			graph.get(roads[i][0]).add(new int[]{roads[i][1], roads[i][2]});
			graph.get(roads[i][1]).add(new int[]{roads[i][0], roads[i][2]});
		}
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];
        int min = Integer.MAX_VALUE;
        q.add(1);
		visited[1] = true;
		while(!q.isEmpty()){
			int node = q.poll();
			for(int[] nei : graph.get(node)){
                min = Math.min(min, nei[1]);
				if(!visited[nei[0]]){
					visited[nei[0]] = true;
					q.add(nei[0]);
				}
			}
		}
		return min;
    }
}