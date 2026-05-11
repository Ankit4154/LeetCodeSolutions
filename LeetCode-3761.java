// 3761. Minimum Absolute Distance Between Mirror Pairs
// https://leetcode.com/problems/minimum-absolute-distance-between-mirror-pairs
// slight optim on sb
class Solution {
    public int minMirrorPairDistance(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		// traverse nums and calculate reverse numbers
		int min = Integer.MAX_VALUE;
		for(int n=0;n<nums.length;n++){
            if(map.containsKey(nums[n])){
				int ind = map.get(nums[n]);
				min = Math.min(min, Math.abs(n-ind));
			}
			StringBuilder sb = new StringBuilder(nums[n]+"");
			sb.reverse();
			// remove starting zeroes
			int i=0;
			while(i<sb.length() && sb.charAt(i) == '0'){
				i++;
			}
            String rev = sb.substring(i);
			int revNum = Integer.parseInt(rev);
			map.put(revNum, n);
		}
        if(min == Integer.MAX_VALUE)
            return -1;
		return min;
    }
}
// init, working
class Solution {
    public int minMirrorPairDistance(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		// traverse nums and calculate reverse numbers
		int min = Integer.MAX_VALUE;
		for(int n=0;n<nums.length;n++){
            if(map.containsKey(nums[n])){
				int ind = map.get(nums[n]);
				min = Math.min(min, Math.abs(n-ind));
			}
			StringBuilder sb = new StringBuilder(nums[n]+"");
			sb.reverse();
			// remove starting zeroes
			int i=0;
			while(i<sb.length() && sb.charAt(i) == '0'){
				i++;
			}
            sb.substring(i, sb.length());
			int revNum = Integer.parseInt(sb.toString());
			map.put(revNum, n);
			
		}
        if(min == Integer.MAX_VALUE)
            return -1;
		return min;
    }
}