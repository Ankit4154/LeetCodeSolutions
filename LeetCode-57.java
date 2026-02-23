// 57. Insert Interval
// https://leetcode.com/problems/insert-interval/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> out = new ArrayList<>(); 
        boolean inserted = false;
		for(int i=0;i<intervals.length;i++){
			if(newInterval[1] < intervals[i][0]){
				out.add(newInterval);
				inserted = true;
				while(i<intervals.length){
					out.add(intervals[i++]);
				}
				break;
			}
			if(newInterval[0] > intervals[i][1]){
				out.add(intervals[i]);
			}else{
				newInterval = new int[]{Math.min(newInterval[0], intervals[i][0]), Math.max(newInterval[1], intervals[i][1])};
			}
		}
        if(!inserted)
		    out.add(newInterval);
		int[][] res = new int[out.size()][2];
		for(int i=0;i<out.size();i++){
			res[i] = out.get(i);
		}
		return res;
    }
}
