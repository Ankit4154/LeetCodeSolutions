// 1306. Jump Game III
// https://leetcode.com/problems/jump-game-iii/

// optim, DFS
class Solution {
    public boolean canReach(int[] arr, int start) {
        return dfs(start, arr);
    }
	boolean dfs(int ind, int[] arr){
		if(ind < 0 || ind >= arr.length)
			return false;
        if(arr[ind] < 0)
            return false;
		if(arr[ind] == 0)
			return true;
        // store the current value for later use
        int jump = arr[ind];
        // mark visited by negating
        arr[ind] = -arr[ind];
		boolean right = dfs(ind + jump, arr);
		boolean left = dfs(ind - jump, arr);
		return left || right;
	}
}

//optim, BFS
class Solution {
    public boolean canReach(int[] arr, int start) {
        // find if 0 valued element is even present or not
		// and early exit
		int n = arr.length;
		boolean found = false;
		for(int i=0;i<n;i++){
			if(arr[i] == 0){
				found = true;
				break;
			}
		}
		if(!found)
			return false;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		boolean[] visited = new boolean[n];
		while(!q.isEmpty()){
			int ind = q.poll();
			if(visited[ind])
				continue;
			visited[ind] = true;
			int left = ind + arr[ind];
			int right = ind - arr[ind];
			// out of bounds
			if(left < n){
				if(arr[left] == 0)
					return true;
				q.add(left);
			}
			if(right >= 0){
				if(arr[right] == 0)
					return true;
				q.add(right);
			}
	}
		return false;
    }
}

// init
class Solution {
    public boolean canReach(int[] arr, int start) {
        // find if 0 valued element is even present or not
		int n = arr.length;
		boolean found = false;
		for(int i=0;i<n;i++){
			if(arr[i] == 0){
				found = true;
				break;
			}
		}
		if(!found)
			return false;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		boolean[] visited = new boolean[n+1];
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				int ind = q.poll();
				if(visited[ind])
					continue;
				int left = ind + arr[ind];
				int right = ind - arr[ind];
				// out of bounds
				if(left < n){
					if(arr[left] == 0)
						return true;
					q.add(left);
				}
				if(right >= 0){
					if(arr[right] == 0)
						return true;
					q.add(right);
				}
				visited[ind] = true;
			}
		}
		return false;
    }
}