// 3718. Smallest Missing Multiple of K
// https://leetcode.com/problems/smallest-missing-multiple-of-k
class Solution {
    public int missingMultiple(int[] nums, int k) {
		int n = nums.length;
		boolean[] present = new boolean[n+2];
		for(int num : nums){
			if((num % k == 0) && (num/k) <= n + 1){
				present[num / k] = true;
			}
		}
		for(int i=1;i<=n+1;i++){
			if(!present[i]){
				return i*k;
			}
		}
		return 0;
    }
}
// init
class Solution {
    public int missingMultiple(int[] nums, int k) {
		int n = nums.length;
        Set<Integer> set = new HashSet<>(n);
		for(int i : nums){
			set.add(i);
		}
		for(int i=k;i<=100;i+=k){
			if(!set.contains(i))
				return i;
		}
		return 0;
    }
}