// 787. Cheapest Flights Within K Stops
// https://leetcode.com/problems/cheapest-flights-within-k-stops

// Bellman Ford  , O(k * E)
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i = 0; i <= k; i++) {
            int[] temp = dist.clone();

            for(int[] f : flights) {
                int u = f[0], v = f[1], w = f[2];
                if(dist[u] == Integer.MAX_VALUE)
                    continue;
                temp[v] = Math.min(temp[v], dist[u] + w);
            }
            dist = temp;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}

// optimized/correct Dijkstra,  O(E * k log (n * k))
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // populate adjacency list / graph with neighbours and weight/cost pair
		// perform Djkshtra on graph to find minimum cost path
		Map<Integer, List<int[]>> graph = new HashMap<>();
		for(int i=0;i<flights.length;i++){
			int from = flights[i][0];
			int to = flights[i][1];
			int price = flights[i][2];
			graph.computeIfAbsent(from, a->new ArrayList<>()).add(new int[]{to,price});
		}
		// best[city][stops] = min cost
		int[][] best = new int[n][k + 2];
        for(int i = 0; i < n; i++)
            Arrays.fill(best[i], Integer.MAX_VALUE);
		
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
		// {city, cost, stops}
		minHeap.add(new int[]{src, 0,0});
		best[src][0] = 0;

		while(!minHeap.isEmpty()){
			int[] cur = minHeap.poll();
            int city = cur[0];
            int cost = cur[1];
            int stops = cur[2];
			if(city == dst)
				return cost;
            if(stops > k)
				continue;
			if(!graph.containsKey(city))
				continue;
			for(int[] c : graph.get(city)){
				int nextCity = c[0];
                int nextCost = cost + c[1];
				if(nextCost < best[nextCity][stops+1]){
					best[nextCity][stops + 1] = nextCost;
					minHeap.add(new int[]{nextCity, nextCost, stops+1});
				}
			}
		}
		return -1;
    }
}
// initial
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // populate adjacency list / graph with neighbours and weight/cost pair
		// perform Djkshtra on graph to find minimum cost path
		Map<Integer, List<int[]>> graph = new HashMap<>();
		Set<Integer> visited = new HashSet<>();
		for(int i=0;i<flights.length;i++){
			int from = flights[i][0];
			int to = flights[i][1];
			int price = flights[i][2];
			graph.computeIfAbsent(from, a->new ArrayList<>()).add(new int[]{to,price});
		}
        System.out.println(graph);
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a->a.minPrice));
		minHeap.add(new Pair(src,Integer.MAX_VALUE));
		int finalMinPrice = 0;
        int stops = 0;
		while(visited.size() < n){
			Pair p = minHeap.poll();
            // if(minHeap.isEmpty())
            //     return finalMinPrice;
			stops++;
			finalMinPrice += p.minPrice;
			if(p.city == dst && stops <= k)
				return finalMinPrice;
			if(visited.contains(p.city))
				continue;
			visited.add(p.city);
            if(graph.containsKey(p.city)){
                for(int[] c : graph.get(p.city)){
				    minHeap.add(new Pair(c[0], Math.min(c[1], p.minPrice)));
			    }
            }			
		}
		return 0;
    }
	private class Pair{
		int city;
		int minPrice;
		Pair(int city, int minPrice){
			this.city = city;
			this.minPrice = minPrice;
		}
	}
}