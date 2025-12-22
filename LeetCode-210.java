// 210. Course Schedule II
// https://leetcode.com/problems/course-schedule-ii
class Solution {
	Set<Integer> visiting = new HashSet<>();
	Set<Integer> cycle = new HashSet<>();
	List<Integer> out = new ArrayList<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 1)
			return new int[1];
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int i=0;i<numCourses;i++){
			map.put(i, new ArrayList<>());
		}
		for(int i=0;i<prerequisites.length;i++){
			List<Integer> l = map.get(prerequisites[i][0]);
			l.add(prerequisites[i][1]);
			map.put(prerequisites[i][0], l);
		}
		for(int i=0;i<numCourses;i++){
			if(!dfs(i, map))
				return new int[0];
		}
		return out.stream().mapToInt(i -> i).toArray();
    }
	
	public boolean dfs(int n, Map<Integer, List<Integer>> map){
		if(visiting.contains(n))
			return true;
		if(cycle.contains(n))
			return false;
        cycle.add(n);
		List<Integer> l = map.get(n);
		for(int x : l){
			if(!dfs(x, map))
				return false;
		}
		cycle.remove(n);
        visiting.add(n);
        out.add(n);
		return true;
	}
}