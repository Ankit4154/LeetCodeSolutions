// 973. K Closest Points to Origin
// https://leetcode.com/problems/k-closest-points-to-origin/
// mod 2
class Solution {
    public int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b)->b[0]-a[0]);
		int[][] out = new int[k][2];
		for(int i=0;i<points.length;i++){
			int x = points[i][0];
			int y = points[i][1];
			int dist = x*x + y*y;
			maxHeap.add(new int[]{dist, x, y}); 
			if(maxHeap.size()>k)
				maxHeap.poll();
		}
        int count = 0;
		while(!maxHeap.isEmpty()){
            int[] p = maxHeap.poll();
            out[count][0] = p[1];
            out[count][1] = p[2];
            count++;
        }
		return out;
    }
}

// mod 1 
class Solution {
    public int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]-b[0]);
		int[][] out = new int[k][2];
		for(int i=0;i<points.length;i++){
			int x = points[i][0];
			int y = points[i][1];
			int dist = x*x + y*y;
			minHeap.add(new int[]{dist, x, y}); 
		}
		int count = 0;
		for(int i=0;i<k;i++){
            int[] p = minHeap.poll();
            out[i][0] = p[1];
            out[i][1] = p[2];
        }
		return out;

    }
}

// init
class Solution {
    public int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]-b[0]);
		int[][] out = new int[k][2];
		for(int i=0;i<points.length;i++){
			int x = points[i][0];
			int y = points[i][1];
			int dist = x*x + y*y;
			minHeap.add(new int[]{dist, x, y}); 
		}
		int count = 0;
		while(!minHeap.isEmpty()){
			int[] p = minHeap.poll();
			k--;
			out[count][0] = p[1];
			out[count][1] = p[2];
			count++;
			if(k == 0){
				return out;
			}
		}
		return out;

    }
}
