// 3970. Shortest Path With At Most K Consecutive Identical Characters
// https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {


        // Build adjacency list
        List<int[]>[] adj = new ArrayList[n];

        for(int i=0;i<n;i++)
            adj[i] = new ArrayList<>();

        for(int[] e : edges){
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][count] = minimum cost
        // count represents consecutive occurrences
        long[][] dist = new long[n][k+1];

        for(int i=0;i<n;i++)
            Arrays.fill(dist[i], Long.MAX_VALUE);

        // PriorityQueue: {cost, node, count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a,b) -> Long.compare(a[0], b[0])
        );

        // Starting node
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while(!pq.isEmpty()){

            long[] curr = pq.poll();

            long cost = curr[0];
            int u = (int)curr[1];
            int count = (int)curr[2];

            // Ignore outdated states
            if(cost != dist[u][count])
                continue;

            // Reached destination
            if(u == n-1)
                return (int)cost;

            for(int[] edge : adj[u]){

                int v = edge[0];
                int wt = edge[1];

                int newCount;

                // Same consecutive character
                if(labels.charAt(u) == labels.charAt(v)){
                    newCount = count + 1;
                }
                else{
                    newCount = 1;
                }

                // Cannot exceed k consecutive characters
                if(newCount > k)
                    continue;

                long newCost = cost + wt;

                if(newCost < dist[v][newCount]){

                    dist[v][newCount] = newCost;

                    pq.offer(new long[]{
                        newCost, v, newCount
                    });
                }
            }
        }

        return -1;
    }
}
