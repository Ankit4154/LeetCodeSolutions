// 2615. Sum of Distances
// https://leetcode.com/problems/sum-of-distances
// optim
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];

        // Group indices by value
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Process each group
        for (List<Integer> list : map.values()) {
            int m = list.size();
            long[] prefix = new long[m];

            // prefix sum
            prefix[0] = list.get(0);
            for (int i = 1; i < m; i++) {
                prefix[i] = prefix[i - 1] + list.get(i);
            }

            long total = prefix[m - 1];

            // Compute result for each index
            for (int k = 0; k < m; k++) {
                int idx = list.get(k);

                // left contribution
                long left = 0;
                if (k > 0) {
                    left = (long) k * idx - prefix[k - 1];
                }

                // right contribution
                long right = 0;
                if (k < m - 1) {
                    right = (total - prefix[k]) - (long)(m - k - 1) * idx;
                }

                res[idx] = left + right;
            }
        }
        return res;
    }
}

// TLE
class Solution {
    public long[] distance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indMap = new HashMap<>();
		for(int i=0;i<nums.length;i++){
			int n = nums[i];
            indMap.put(i)
			if(map.containsKey(n)){
				map.get(n).add(i);
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(n, list);
			}
		}
		long[] ans = new long[nums.length];
		int c = 0;
		for(int i=0;i<nums.length;i++){
			int n = nums[i];
			List<Integer> list = map.get(n);
			if(list.size() == 1)
				ans[c] = 0;
			else{
				int sum = 0;
				for(int l : list){
					sum += Math.abs(l - i);
				}
				ans[c] = sum;
			}
			c++;
		}
		return ans;
    }
}