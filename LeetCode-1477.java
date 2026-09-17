// 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
// https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum
// optim
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        if(n < 2)
            return -1;
		int left = 0, sum = 0;
		int best = Integer.MAX_VALUE;
		int out = Integer.MAX_VALUE;
		int[] minLen = new int[n];
		Arrays.fill(minLen, Integer.MAX_VALUE);
		for(int right = 0; right < n; right++){
			sum += arr[right];
			while(sum > target){
				sum -= arr[left];
				left++;
			}
			
			if(sum == target){
				int len = right - left + 1;
				
				// if we already found valid subarray before left
				if(left > 0 && minLen[left-1] != Integer.MAX_VALUE){
					out = Math.min(out, len + minLen[left-1]);
				}
				best = Math.min(best, len);
			}
			minLen[right] = best;
		}
		return (out == Integer.MAX_VALUE) ? -1 : out;
		
    }
}

// init, naive, errored
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        if(n < 2)
            return -1;
		int left = 0, right = 0, sum = 0;
		List<List<Integer>> out = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		
		while(right < n){
			int num = arr[right];
			sum += num;
			if(sum == target){
				list.add(num);
				out.add(list);
				list = new ArrayList<>();
				sum = 0;
                left = right + 1;
			}else if(sum < target){
				list.add(num);
			}else if(sum > target){
                while(sum > target && !list.isEmpty() && left <= right){
                    int l = arr[left];
                    sum -= l;
                    left++;
                    list.remove(list.get(0));
                }
                if(sum > target){
				    // reset and start again from current num
				    sum = 0;
                    list = new ArrayList<>();
                    left = right+1;
                }
				if(sum == target){
					list.add(num);
					out.add(list);
					list = new ArrayList<>();
                    left = right + 1;
				}
			}
			right++;
		}
		if(out.size() < 2)
			return -1;
		Collections.sort(out, (a,b) -> Integer.compare(a.size(),b.size()));
        System.out.println(out);
		return out.get(0).size() + out.get(1).size();
		
    }
}