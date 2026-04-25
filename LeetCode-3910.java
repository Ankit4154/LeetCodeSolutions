// 3910. Count Connected Subgraphs with Even Node Sum
// https://leetcode.com/problems/count-connected-subgraphs-with-even-node-sum/
class Solution {
    int ans = 0;
    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for(int edge[] : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        bt(0, graph, nums, new ArrayList<>(), 0);
        return ans;
    }
    void bt(int ind, List<List<Integer>> graph, int[] nums, List<Integer> subset, int sum){
        // if reached leaf node
        if(ind == nums.length){
            if(subset.isEmpty())
                return;
            if(sum % 2 != 0)
                return;
            if(isConnected(subset, graph))
                ans++;
            return;
        }
        //include current node in subset
        subset.add(ind);
        bt(ind+1, graph, nums, subset, sum + nums[ind]);
        // skip current node
        subset.remove(subset.size()-1);
        bt(ind+1, graph, nums, subset, sum);
    }

    boolean isConnected(List<Integer> subset, List<List<Integer>> graph){
        Set<Integer> set = new HashSet<>(subset);
        Set<Integer> visited = new HashSet<>();
        dfs(subset.get(0), graph, set, visited);
        return visited.size() == subset.size();
    }
    void dfs(int node, List<List<Integer>> graph, Set<Integer> set, Set<Integer> visited){
        visited.add(node);
        for(int nei : graph.get(node)){
            if(set.contains(nei) && !visited.contains(nei)){
                dfs(nei, graph, set, visited);
            }
        }
        return;
    }
}