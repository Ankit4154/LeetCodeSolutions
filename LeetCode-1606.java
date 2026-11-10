// 1606. Find Servers That Handled Most Number of Requests
// https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        var available = new TreeSet<Integer>();
		
		var busy = new PriorityQueue<int[]>(
			(a,b) -> Integer.compare(a[0],b[0])
		);
		
		int[] count = new int[k];
		
		for(int i=0;i<k;i++){
			available.add(i);
		}
		
		for(int i=0;i<arrival.length;i++){
			//Release all servers whose requests have completed
			while(!busy.isEmpty() && busy.peek()[0] <= arrival[i]){
				int[] curr = busy.poll();
				available.add(curr[1]);
			}
			
			// All servers are busy, drop the requests
			if(available.isEmpty())
				continue;
			
			int server = i % k;
			
			//Find the next available server
			Integer next = available.ceiling(server);
			
			// Wrap around if no server >= server is available
			if(next == null)
				next = available.first();
			
			//Assign request to selected server
			available.remove(next);
			count[next]++;
			
			
			// Mark the server as busy until completion
			busy.add(new int[]{arrival[i] + load[i], next});
			
		}
		
		// find the maximum number of requests handled
		int max = 0;
		for(int c : count)
			max = Math.max(max, c);
		
		// collect all busisest servers
		var out = new ArrayList<Integer>();
		for(int i=0;i<k;i++){
			if(max == count[i]){
				out.add(i);
			}
		}
		
		return out;
		
    }
}