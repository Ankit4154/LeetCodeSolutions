// 1791. Find Center of Star Graph
// https://leetcode.com/problems/find-center-of-star-graph/description

// Optimized solution, given that the graph is valid star graph.
// hence there will be 1 center node connected to all the edges.
// take a map and fill first 2 nodes
// compare the nodes from the next edges array element.
// return the common node value found.
class Solution {
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(edges[0][0], 1);
        map.put(edges[0][1], 1);
		for(int i=1;i<edges.length;i++){
            if(map.containsKey(edges[i][0]))
                return edges[i][0];
            else
                return edges[i][1];
		}
        return 0;
    }
}

// intialize indegree and outdegree arrays with edges.length * 2
// as for each edge there can be 2 nodes
// Calculate the indegree and outdegree of each nodes.
// also count the unique number of nodes. take set
// Iterate over unique no. of nodes from set and return the node value
// where indegree + outdegree == nodes - 1(star graph, center will have n-1 edges connected/degree )
class Solution {
    public int findCenter(int[][] edges) {
        int[] inwardDegree = new int[edges.length*2];
		int[] outwardDegree = new int[edges.length*2];
        Set<Integer> set = new HashSet<>();
		for(int i=0;i<edges.length;i++){
			inwardDegree[edges[i][0]]++;
			outwardDegree[edges[i][1]]++;
            set.add(edges[i][0]);
            set.add(edges[i][1]);
		}
		for(int i=1;i<=set.size();i++){
			if(inwardDegree[i]+outwardDegree[i] == set.size()-1)
				return i;
		}
		return 0;
    }
}

