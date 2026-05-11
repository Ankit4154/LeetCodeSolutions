// 403. Frog Jump
// https://leetcode.com/problems/frog-jump/description
class Solution {
    int last = 0;
    Set<Integer> set;
    Map<String, Boolean> dp;
    public boolean canCross(int[] stones) {
        if(stones.length == 1)
			return true;
        set = new HashSet<>();
        for(int n : stones)
            set.add(n);
        dp = new HashMap<>();
        last = stones[stones.length-1];
		return solve(1, stones, 1);
    }
	boolean solve(int pos, int[] stones, int k){
        if(!set.contains(pos))
            return false;
        if(pos == last)
            return true;
        if(dp.containsKey(pos+","+k))
            return dp.get(pos+","+k);
        int d[] = {-1, 0, 1};
        for(int i=0;i<3;i++){
            int nextJump = k + d[i];
            if(nextJump > 0){
                int nextPos = pos + nextJump;
                if(solve(nextPos, stones, nextJump)){
                    dp.put(pos+","+k, true);
                    return true;
                }
            }
        }
        dp.put(pos+","+k, false);
        return false;
	}
}