// 2709. Greatest Common Divisor Traversal
// https://leetcode.com/problems/greatest-common-divisor-traversal/
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

// optimized
class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
		int len = nums.length;
        
		UnionFind uf = new UnionFind(len);
		Map<Integer, Integer> factorIndex = new HashMap<>();
		for(int i=0;i<len;i++){
			int n = nums[i];
            if(len > 1 && nums[i] == 1)
                return false;
			// find prime factors
			for(int f=2; f * f <= n; f++){
				if(n % f == 0){
					if(factorIndex.containsKey(f)){
						uf.unionBySize(i, factorIndex.get(f));
					}else{
						factorIndex.put(f, i);
					}
					while(n % f == 0){
						n = n / f;
					}
				}
			}
			if(n > 1){
				if(factorIndex.containsKey(n)){
					uf.unionBySize(i, factorIndex.get(n));
				}else{
					factorIndex.put(n, i);
				}
			}
		}
		return uf.count == 1;
		
    }
}

// initial
class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
		List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        for(int i=0;i<nums.length;i++){
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<nums.length;i++){
			for(int j=i+1;j<nums.length;j++){
				if(gcd(nums[i], nums[j]) > 1){
					graph.get(i).add(j);
					graph.get(j).add(i);
				}
			}
		}
        // start dfs from 1st/0th node
        dfs(0, graph, visited);
        // check if all nodes are visited or not
        // if all visited, it means its single connected component
        // and return true
        // if all not visited, it means multiple connected components
        // are present, hence return false
        for(boolean visit : visited){
            if(!visit){
                 return false;
            }
        }
        return true;
		
    }
	
	private void dfs(int n, List<List<Integer>> graph, boolean[] visited){
		visited[n] = true;
		for(int nei : graph.get(n)){
			if(!visited[nei]){
                dfs(nei, graph, visited);
            }
		}
	}
	
	private int gcd(int a, int b){
		if(b == 0)
			return a;
		return gcd(b, a % b);
	}
}