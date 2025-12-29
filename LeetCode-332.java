// 332. Reconstruct Itinerary
// https://leetcode.com/problems/reconstruct-itinerary/description/
// Optimized only DFS
class Solution {
	Map<String, PriorityQueue<String>> graph = new HashMap<>();
	List<String> out = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
		for(int i=0;i<tickets.size();i++){
			graph.computeIfAbsent(tickets.get(i).get(0), 
				k -> new PriorityQueue<>()).add(tickets.get(i).get(1));
		}
		dfs("JFK");
		Collections.reverse(out);
		return out;
    }
	private void dfs(String airport){
		PriorityQueue<String> destinations = graph.get(airport);
		while(destinations != null && !destinations.isEmpty()){
			String next = destinations.poll();
			dfs(next);
		}
		out.add(airport);
	}
}


// backtracking , TLE
class Solution {
	Map<String, List<String>> graph = new HashMap<>();
	List<String> out = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.sort(
					Comparator.comparing((List<String> t) -> t.get(0))
					.thenComparing(t -> t.get(1))
					);
		for(int i=0;i<tickets.size();i++){
			graph.computeIfAbsent(tickets.get(i).get(0), 
				k -> new ArrayList<>()).add(tickets.get(i).get(1));
		}
		out.add("JFK");
		dfs("JFK", tickets);
		return out;
    }
	private boolean dfs(String node, List<List<String>> tickets){
		if(out.size() == tickets.size() + 1)
			return true;
		if(!graph.containsKey(node))
			return false;
		
		List<String> tempNeighbours = graph.get(node);
        for (int i = 0; i < tempNeighbours.size(); i++) {
            String next = tempNeighbours.get(i);
            tempNeighbours.remove(i);
			out.add(next);
			if(dfs(next, tickets))
				return true;
			tempNeighbours.add(i, next);
			out.remove(out.size()-1);
		}
		return false;
	}
}
