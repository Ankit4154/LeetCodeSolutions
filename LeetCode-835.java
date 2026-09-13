// 835. Image Overlap
// https://leetcode.com/problems/image-overlap
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        // count all co-ordinates for 1s from img1 and img2
		List<int[]> countImg1 = new ArrayList<>();
		List<int[]> countImg2 = new ArrayList<>();
		int n = img1.length;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(img1[i][j] == 1){
					countImg1.add(new int[]{i,j});
				}
				if(img2[i][j] == 1){
					countImg2.add(new int[]{i,j});
				}
			}
		}
		// iterate over each combination
		Map<String, Integer> map = new HashMap<>();
		int max = 0;
		for(int[] cell1 : countImg1){
			for(int[] cell2 : countImg2){
				int dr = cell2[0] - cell1[0];
				int dc = cell2[1] - cell1[1];
				String key = dr + "#" + dc;
				map.put(key, map.getOrDefault(key, 0)+1);
				max = Math.max(map.get(key), max);
			}
		}
		return max;
    }
}