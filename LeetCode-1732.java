// 1732. Find the Highest Altitude
// https://leetcode.com/problems/find-the-highest-altitude
class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0;
		int sum = 0;
		for(int i=0;i<gain.length;i++){
			sum += gain[i];
			max = Math.max(max, sum);
		}
		return max;
    }
}