// 2392. Build a Matrix With Conditions
// https://leetcode.com/problems/build-a-matrix-with-conditions/
public class Solution{
    private Set<Integer> visit;
    private Set<Integer> path;
    private List<Integer> order;

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions){
        int[] rowOrder = topoSort(k, rowConditions);
        if(rowOrder == null) return new int[0][0];
        int[] colOrder = topoSort(k, colConditions);
        if(colOrder == null) return new int[0][0];

        Map<Integer, Integer> valToRow = new HashMap<>();
        for(int i = 0; i < rowOrder.length; i++){
            valToRow.put(rowOrder[i], i);
        }
        Map<Integer, Integer> valToCol = new HashMap<>();
        for(int i = 0; i < colOrder.length; i++){
            valToCol.put(colOrder[i], i);
        }

        int[][] res = new int[k][k];
        for(int num = 1; num <= k; num++){
            int r = valToRow.get(num);
            int c = valToCol.get(num);
            res[r][c] = num;
        }
        return res;
    }

    private int[] topoSort(int k, int[][] edges){
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i = 1; i <= k; i++){
            adj.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
        }

        visit = new HashSet<>();
        path = new HashSet<>();
        order = new ArrayList<>();

        for(int i = 1; i <= k; i++){
            if(!visit.contains(i)){
                if(!dfs(i, adj)){
                    return null;
                }
            }
        }

        Collections.reverse(order);
        return order.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int src, Map<Integer, List<Integer>> adj){
        if(path.contains(src)) return false;
        if(visit.contains(src)) return true;

        visit.add(src);
        path.add(src);
        for(int nei : adj.get(src)){
            if(!dfs(nei, adj)){
                return false;
            }
        }
        path.remove(src);
        order.add(src);
        return true;
    }
}