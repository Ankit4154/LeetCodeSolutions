// 743. Network Delay Time
// https://leetcode.com/problems/network-delay-time/
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>(n+1);
		for(int i=0;i<=n;i++){
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<times.length;i++){				
			int[] p = new int[]{times[i][1],times[i][2]};
			graph.get(times[i][0]).add(p);
		}
		int out = 0;
		int visitedCount = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		boolean[] visited = new boolean[n+1];
		pq.add(new int[]{0,k});
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			int dist = cur[0];
			int node = cur[1];
			if(visited[node])
				continue;
			visited[node] = true;
			visitedCount++;
			out = Math.max(out, dist);
			
			List<int[]> pairs = graph.get(node);
			for(int i=0;i<pairs.size();i++){
				if(!visited[pairs.get(i)[0]]){
					pq.add(new int[]{dist + pairs.get(i)[1], pairs.get(i)[0]});
				}
			}
		}
		if(visitedCount != n)
			return -1;
		return out;
		
    }
}