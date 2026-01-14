// 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
// https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/

class DisjointSet{
	int[] rank;
	int[] parent;
    int[] size;
	
	DisjointSet(int n){
		rank = new int[n + 1];
		parent = new int[n + 1];
        size = new int[n + 1];
		for(int i=0;i<=n;i++){
			parent[i] = i;
            size[i] = 1;
		}
	}
	
	int findParent(int n){
		if(parent[n] == n)
			return n;
		return parent[n] = findParent(parent[n]);
	}
	
	boolean unionByRank(int u, int v){
		int uParent = findParent(u);
		int vParent = findParent(v);
		if(uParent == vParent)
			return false;
		if(rank[uParent] < rank[vParent]){
			parent[uParent] = vParent;
		} else if(rank[uParent] > rank[vParent]){
			parent[vParent] = uParent;
		} else {
			parent[vParent] = uParent;
			rank[uParent]++;
		}
		return true;
	}
    public boolean unionBySize(int u, int v) {
        int pu = findParent(u);
        int pv = findParent(v);
        if (pu == pv) return false;

        if (size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
        return true;
    }
	
}

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges){
        
		List<int[]> edgeList = new ArrayList<>();
        for(int i = 0; i < edges.length; i++){
            edgeList.add(new int[] { edges[i][0], edges[i][1], edges[i][2], i });
        }

        edgeList.sort(Comparator.comparingInt(a -> a[2]));
		//Collections.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
		//Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));


        int mstWeight = 0;
        DisjointSet ds = new DisjointSet(n);
        int mstEdges = 0;
        for(int[] edge : edgeList){
            if(ds.unionBySize(edge[0], edge[1])){
                mstWeight += edge[2];
                mstEdges++;
            }
        }

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for(int[] edge : edgeList){
            // Try without current/this edge
            DisjointSet dsWithout = new DisjointSet(n);
            int weight = 0;
            int edgesUsed = 0;
            for(int[] other : edgeList){
                if(other[3] == edge[3])
                    continue;
                if(dsWithout.unionBySize(other[0], other[1])){
                    weight += other[2];
                    edgesUsed++;
                }
            }
            if(edgesUsed != n-1 || weight > mstWeight){
                critical.add(edge[3]);
                continue;
            }

            // Try with current edge
            DisjointSet dsWith = new DisjointSet(n);
            dsWith.unionBySize(edge[0], edge[1]);
            weight = edge[2];
            edgesUsed = 1;
            for(int[] other : edgeList){
                if(dsWith.unionBySize(other[0], other[1])){
                    weight += other[2];
                    edgesUsed++;
                }
            }
            if (edgesUsed == n - 1 && weight == mstWeight) {
                pseudo.add(edge[3]);
            }
        }

        return Arrays.asList(critical, pseudo);
		
    }
}