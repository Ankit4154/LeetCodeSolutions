// 207. Course Schedule
// https://leetcode.com/problems/course-schedule/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // loop over number of nodes and map
		// their dependents/required courses
		Map<Integer, List<Integer>> map = new HashMap<>();
		Set<Integer> visiting = new HashSet<>();
		for(int i=0;i<prerequisites.length;i++){
			int node = prerequisites[i][0];
			int nNode = prerequisites[i][1];
			if(!map.containsKey(node)){
				map.put(node, new ArrayList<>());
			}
			if(!map.containsKey(nNode)){
				map.put(nNode, new ArrayList<>());
			}
			List<Integer> l = map.get(nNode);
			l.add(node);
			map.put(nNode, l);
		}
		for(int i=0;i<numCourses;i++){
			if(!dfs(i, map, visiting))
				return false;
		}
		return true;
    }
	public boolean dfs(int n, Map<Integer, List<Integer>> map, Set<Integer> visiting){
		if(visiting.contains(n))
			return false;
		List<Integer> l = map.get(n);
		if(l == null || l.isEmpty()){
			return true;
		}
		visiting.add(n);
		for(int x : l){
			if(!dfs(x, map, visiting)){
				return false;
			}
		}
		visiting.remove(n);
		map.put(n, new ArrayList<>());
		return true;
	}
}
