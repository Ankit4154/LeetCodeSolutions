// 3020. Find the Maximum Number of Elements in Subset
// https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset
class Solution {
    public int maximumLength(int[] nums) {
        int max = 1;
        if(nums.length < 3)
            return max;
        Map<Long, Integer> map = new HashMap<>();
		for(int n : nums){
			map.put((long)n, map.getOrDefault((long)n, 0)+1);
		}
        
        // Special case for 1, pre-process
        if (map.containsKey(1L)) {
            int count = map.get(1L);
			if(count % 2 != 0)
				max = Math.max(max, count);
			else
				max = Math.max(max, count - 1);
        }
		
		for(long n : map.keySet()){
			if(n == 1L)
				continue;
			long curr = n;
			int len = 0;
			while(map.containsKey(curr)){
				int freq = map.get(curr);
				if(freq == 1){
					len++;
					break;
				}
                // If curr > 1e9, then curr^2 cannot exist in the map because
                // every original number is <= 1e9.
                if (curr > 1_000_000_000L) {
                    len++;
                    break;
                }
				
				long squared = curr * curr;
				if(!map.containsKey(squared)){
					len++;
					break;
				}
				
				len+=2;
				curr = squared;
			}
			max = Math.max(max, len);
		}
		return max;
    }
}