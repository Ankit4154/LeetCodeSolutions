// 1722. Minimize Hamming Distance After Swap Operations 
// https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations
// optim
class DSU {
    int[] parent;

    public DSU(int n){
        parent = new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i] = i;
        }
    }

    int findParent(int node){
        if(parent[node] == node)
            return node;
        int p = findParent(parent[node]);
        return parent[node] = p;
    }

    void union(int u, int v){
        int pu = findParent(u);
        int pv = findParent(v);
        if(pu != pv)
            parent[pv] = pu;
    }

}


class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int dist = 0;
        int n = source.length;
        for(int i=0;i<source.length;i++){
            if(source[i] != target[i])
                dist++;
        }
        if(allowedSwaps.length == 0){
            return dist;
        }
        dist = 0;
        DSU dsu = new DSU(n);
        for(int[] s : allowedSwaps){
            dsu.union(s[0], s[1]);
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int p = dsu.findParent(i);
            map.putIfAbsent(p, new HashMap<>());
            Map<Integer, Integer> freq = map.get(p);

            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }
        // Step 3: match target
        for(int i = 0; i < n; i++){
            int p = dsu.findParent(i);
            Map<Integer, Integer> freq = map.get(p);

            if(freq.getOrDefault(target[i], 0) > 0){
                freq.put(target[i], freq.get(target[i]) - 1);
            }else {
                dist++;
            }
        }
        return dist;
    }
}

// dfs
class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        // Step 1: Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] swap : allowedSwaps){
            graph.get(swap[0]).add(swap[1]);
            graph.get(swap[1]).add(swap[0]);
        }

        boolean[] visited = new boolean[n];
        int dist = 0;

        // Step 2: Traverse components
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;

            // collect this component
            List<Integer> comp = new ArrayList<>();
            dfs(i, graph, visited, comp);

            // Step 3: frequency matching
            Map<Integer, Integer> freq = new HashMap<>();

            // count source values
            for(int idx : comp){
                freq.put(source[idx], freq.getOrDefault(source[idx], 0) + 1);
            }

            // try to match target
            for(int idx : comp){
                if(freq.getOrDefault(target[idx], 0) > 0){
                    freq.put(target[idx], freq.get(target[idx]) - 1);
                } else {
                    dist++;
                }
            }
        }

        return dist;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> comp){
        visited[node] = true;
        comp.add(node);

        for(int nei : graph.get(node)){
            if(!visited[nei]){
                dfs(nei, graph, visited, comp);
            }
        }
    }
}