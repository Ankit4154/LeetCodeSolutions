// 1627. Graph Connectivity With Threshold
// https://leetcode.com/problems/graph-connectivity-with-threshold/
class UnionFind{
	
	int[] parent;
	int[] size;
	int count;
	
	UnionFind(int nodes){
		count = nodes;
		parent = new int[nodes + 1];
		size = new int[nodes + 1];
		for(int i=0;i<=nodes;i++){
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	int findParent(int node){
		if(parent[node] == node)
			return node;
		return parent[node] = findParent(parent[node]);
	}
	
	public boolean unionBySize(int u, int v){
		int uParent = findParent(u);
		int vParent = findParent(v);
		if(uParent == vParent)
			return false;
        count--;
		if(size[uParent] > size[vParent]){
			parent[vParent] = uParent;
			size[uParent] += size[vParent];
		}else{
			parent[uParent] = vParent;
			size[vParent] += size[uParent];
		}
		return true;
	}
}

class Solution {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n);

        //union by divisors > threshold
        for(int d = threshold + 1; d <= n; d++) {
            for(int multiple = 2 * d; multiple <= n; multiple += d) {
                uf.unionBySize(d, multiple);
            }
        }

        List<Boolean> ans = new ArrayList<>();
        for(int[] q : queries) {
            ans.add(uf.findParent(q[0]) == uf.findParent(q[1]));
        }
        return ans;
    }
}