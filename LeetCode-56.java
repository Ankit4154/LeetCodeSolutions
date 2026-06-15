// 56. Merge Intervals
// https://leetcode.com/problems/merge-intervals/
// optim, no ArrayList usage
class Solution {
    public int[][] merge(int[][] intervals) {
		int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		int[][] merged = new int[n][2];
        int idx = 0;

        merged[0] = intervals[0];

        for(int i = 1; i < n; i++) {
            if(intervals[i][0] <= merged[idx][1]){
                merged[idx][1] =
                    Math.max(merged[idx][1], intervals[i][1]);
            }else{
                merged[++idx] = intervals[i];
            }
        }
        // rewrite a new array with only used indexes
        return Arrays.copyOf(merged, idx + 1);
    }
}

// init
class Solution {
    public int[][] merge(int[][] intervals) {
		int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		List<int[]> list = new ArrayList<>();
		
		int p1Start = intervals[0][0];
        int p1End = intervals[0][1];

        for(int i = 1; i < n; i++){
            int p2Start = intervals[i][0];
            int p2End = intervals[i][1];

            if(p2Start <= p1End) {
                // overlap
                p1End = Math.max(p1End, p2End);
            }else{
                // no overlap, store current interval
                list.add(new int[]{p1Start, p1End});

                p1Start = p2Start;
                p1End = p2End;
            }
        }

        // add last interval
        list.add(new int[]{p1Start, p1End});
        int[][] out = new int[list.size()][2];
		int i=0;
		while(i<list.size()){
			out[i][0] = list.get(i)[0];
			out[i][1] = list.get(i)[1];
			i++;
		}
        return out;
    }
}