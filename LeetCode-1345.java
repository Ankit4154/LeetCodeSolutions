// 1345. Jump Game IV
// https://leetcode.com/problems/jump-game-iv
// optim, O(n)
class Solution {
    public int minJumps(int[] arr) {
		int n = arr.length;
		// edge case 1
		if(n == 1)
			return 0;
		// edge case 2
		if(arr[0] == arr[n-1])
			return 1;
		Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
		}
        
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
        q.add(0);
        visited[0] = true;
		int jumps = 0;
		while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int node = q.poll();
                if(node == n-1)
                    return jumps;
                // left
                if(node - 1 >= 0 && !visited[node - 1]){
                    visited[node - 1] = true;
                    q.add(node - 1);
                }
                // right
                if(node + 1 < n && !visited[node + 1]){
                    visited[node + 1] = true;
                    q.add(node + 1);
                }
                // is same value
                if(map.containsKey(arr[node])){
                    for(Integer nei : map.get(arr[node])){
                        if(!visited[nei]){
                            visited[nei] = true;
                            q.add(nei);
                        }
                    }
                    map.remove(arr[node]);
                }
                
            }
            jumps++;
		}
		return jumps;
    }
}

// TLE, O(n^2)
class Solution {
    public int minJumps(int[] arr) {
		int n = arr.length;
		// edge case 1
		if(n == 1)
			return 0;
		// edge case 2
		if(arr[0] == arr[n-1])
			return 1;
		Map<Integer, List<Integer>> map = new HashMap<>();
        // build graph
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<n;i++){
            if(i!=n-1)
			    graph.get(i).add(i+1);
            if(i!=0)
			    graph.get(i).add(i-1);
			for(int nei : map.get(arr[i])) {
                if(nei != i)
                    graph.get(i).add(nei);
            }
		}
        
		Queue<Integer> q = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();
        q.add(0);
        visited.add(0);
		int jumps = 0;
		while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int node = q.poll();
                if(node == n-1)
                    return jumps;
                
                for(Integer nei : graph.get(node)){
                    if(!visited.contains(nei)){
                        visited.add(nei);
                        q.add(nei);
                    }
                }
            }
            jumps++;
			
		}
		return jumps;
    }
}