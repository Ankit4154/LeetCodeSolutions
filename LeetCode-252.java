// 252. Meeting Rooms
// https://leetcode.com/problems/meeting-rooms/
/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
		Collections.sort(intervals, (a, b) -> a.start - b.start);
		if(intervals.isEmpty())
			return true;
		int p1Start = intervals.get(0).start;
		int p1End = intervals.get(0).end;
		for(int i=1;i<intervals.size();i++){
			int p2Start = intervals.get(i).start;
			int p2End = intervals.get(i).end;
			if(p2Start < p1End){
				return false;
			}else{
				p1End = Math.max(p1End,p2End);
			}
		}
		return true;
    }
}
