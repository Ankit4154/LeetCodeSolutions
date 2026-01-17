// 2392. Build a Matrix With Conditions
// https://leetcode.com/problems/build-a-matrix-with-conditions/
// optimize time using primitive data structures 
// Tip : Can use same visited array for path check marking 2 in visited array for every path traversal
class Solution {
	
    public int[][] buildMatrix(int k, int[][] rowedges, int[][] coledges){
		List<Integer> rowList = topoSort(k, rowedges);
		List<Integer> colList = topoSort(k, coledges);
		if(rowList.isEmpty() || colList.isEmpty())
			return new int[][]{};
		Map<Integer, Integer> valToRow = new HashMap<>();
		Map<Integer, Integer> valToCol = new HashMap<>();
		for(int i=0;i<rowList.size();i++){
			valToRow.put(rowList.get(i), i);
		}
		for(int i=0;i<colList.size();i++){
			valToCol.put(colList.get(i), i);
		}
		int[][] out = new int[k][k];
		for(int n=1;n<=k;n++){
			int row =  valToRow.get(n);
			int col =  valToCol.get(n);
			out[row][col] = n;
		}
		return out;
		
    }
	
	public List<Integer> topoSort(int k, int[][] edges){
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i=0;i<=k;i++){
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<edges.length;i++){
			int u = edges[i][0];
			int v = edges[i][1];
			graph.get(u).add(v);
		}
		boolean[] visited = new boolean[k+1];
        int[] path = new int[k+1];
		List<Integer> list = new ArrayList<>();
		for(int i=1;i<=k;i++){
            if(!visited[i]){
				if(!dfs(i, list, graph, visited, path)){
					return new ArrayList<>();
				}
			}
		}
		Collections.reverse(list);
		return list;
	}
	
	public boolean dfs(int n, List<Integer> list, List<List<Integer>> graph, boolean[] visited, int[] path){
		if(path[n] == 1)
            return false;
        if(visited[n])
            return true;
        visited[n] = true;
        path[n] = 1;
		for(int nei : graph.get(n)){
			if(!dfs(nei, list, graph, visited, path))
                return false;
		}
        path[n] = 0;
		list.add(n);
        return true;
	}
}


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
