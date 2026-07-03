// 3620. Network Recovery Pathways
// https://leetcode.com/problems/network-recovery-pathways
class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        TreeSet<Integer> values = new TreeSet<>();

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            values.add(e[2]);
        }

        // Topological order
        int[] topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                q.offer(i);

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (int[] e : graph[u]) {
                if (--indegree[e[0]] == 0)
                    q.offer(e[0]);
            }
        }

        if (values.isEmpty())
            return -1;

        int[] costs = new int[values.size()];
        idx = 0;
        for (int x : values)
            costs[idx++] = x;

        int lo = 0, hi = costs.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (canAchievePathScore(costs[mid], graph, topo, online, k, n)) {
                ans = costs[mid];
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
    private boolean canAchievePathScore(int limit,
                        List<int[]>[] graph,
                        int[] topo,
                        boolean[] online,
                        long k,
                        int n) {

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {

            if (dist[u] == INF)
                continue;

            if (u != 0 && u != n - 1 && !online[u])
                continue;

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int cost = edge[1];

                if (cost < limit)
                    continue;

                if (v != n - 1 && !online[v])
                    continue;

                if (dist[v] > dist[u] + cost)
                    dist[v] = dist[u] + cost;
            }
        }

        return dist[n - 1] <= k;
    }
    
}