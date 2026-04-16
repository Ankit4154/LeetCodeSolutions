// 3488. Closest Equal Element Queries
// https://leetcode.com/problems/closest-equal-element-queries
// optim
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Precompute answers
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (List<Integer> list : map.values()) {
            int size = list.size();

            if (size == 1) continue;

            for (int i = 0; i < size; i++) {
                int curr = list.get(i);
                int prev = list.get((i - 1 + size) % size);
                int next = list.get((i + 1) % size);

                int d1 = Math.min(Math.abs(curr - prev), n - Math.abs(curr - prev));
                int d2 = Math.min(Math.abs(curr - next), n - Math.abs(curr - next));

                res[curr] = Math.min(d1, d2);
            }
        }

        // Answer queries in O(1)
        List<Integer> ans = new ArrayList<>();
        for (int q : queries) {
            ans.add(res[q]);
        }

        return ans;
    }
}

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
		List<Integer> ans = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int i=0;i<n;i++){
			// if(map.containsKey(nums[i])){
			// 	map.get(nums[i]).add(i);
			// }else{
			// 	List<Integer> list = new ArrayList<>();
			// 	list.add(i);
			// 	map.put(nums[i], list);
			// }
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
		}
		for(int q=0;q<queries.length;q++){
			int ind = queries[q];
			int num = nums[ind];
            List<Integer> list = map.get(num);
            if(list.size()<2){
                ans.add(-1);
                continue;
            }
            // find index from queries in the list 
            int i = Collections.binarySearch(list, ind);
            int size = list.size();
            int prev = list.get((i - 1 + list.size()) % list.size());
            int next = list.get((i + 1) % list.size());
            
            int d1 = Math.min(Math.abs(prev - ind), n - Math.abs(prev - ind));
            int d2 = Math.min(Math.abs(next - ind), n - Math.abs(next - ind));
            ans.add(Math.min(d1, d2));				
			
		}
		return ans;
			
    }
}
// init
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
		List<Integer> ans = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int i=0;i<n;i++){
			if(map.containsKey(nums[i])){
				map.get(nums[i]).add(i);
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(nums[i], list);
			}
		}
		for(int q=0;q<queries.length;q++){
			int ind = queries[q];
			int num = nums[ind];
			if(map.containsKey(num)){
				List<Integer> list = map.get(num);
				if(list.size()<2){
					ans.add(-1);
					continue;
				}
				// find index from queries in the list 
				int i = Collections.binarySearch(list, ind);
				int size = list.size();
				int prev = list.get((i - 1 + list.size()) % list.size());
                int next = list.get((i + 1) % list.size());
				
				int d1 = Math.min(Math.abs(prev - ind), n - Math.abs(prev - ind));
				int d2 = Math.min(Math.abs(next - ind), n - Math.abs(next - ind));
				ans.add(Math.min(d1, d2));				
			}
		}
		return ans;
			
    }
}