// 435. Non-overlapping Intervals
// https://leetcode.com/problems/non-overlapping-intervals/
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
		Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		int p1Start = intervals[0][0];
		int p1End = intervals[0][1];
		int count = 0;
		for(int i=1; i<n; i++){
			int p2Start = intervals[i][0];
			int p2End = intervals[i][1];
			if(p2Start < p1End){
				// count and skip this interval
				count++;
				// remove the one which has greater end
				p1End = Math.min(p1End, p2End);
			}else{
				p1End = p2End;
			}
		}
		return count;
    }
}