// 1840. Maximum Building Height
// https://leetcode.com/problems/maximum-building-height
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
		List<int[]> list = new ArrayList<>();
		list.add(new int[]{1, 0});
		for(int res[] : restrictions){
			list.add(new int[]{res[0], res[1]});
		}
		list.add(new int[]{n, n-1});
		Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
		int m = list.size();
		// left to right 
		for(int i=1;i<m;i++){
			int dist = list.get(i)[0] - list.get(i - 1)[0];
			
			list.get(i)[1] = Math.min(list.get(i)[1], list.get(i-1)[1] + dist);
		}
		// right to left 
		for(int i=m-2;i>=0;i--){
			int dist = list.get(i + 1)[0] - list.get(i)[0];
			
			list.get(i)[1] = Math.min(list.get(i)[1], list.get(i+1)[1] + dist);
		}
		
		int out = 0;
		for(int i=1;i<m;i++){
			long h1 = list.get(i - 1)[1];
            long h2 = list.get(i)[1];

            long dist = list.get(i)[0] - list.get(i - 1)[0];

            long peak = (h1 + h2 + dist) / 2;

            out = (int)Math.max(out, peak);
		}
		return out;
    }
}