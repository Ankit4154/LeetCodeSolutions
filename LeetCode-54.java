// 54. Spiral Matrix
// https://leetcode.com/problems/spiral-matrix/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
    
		int n = matrix.length;
		int m = matrix[0].length;
		int left = 0, right = m-1;
		int top = 0;
		int bottom = n-1;
		List<Integer> res = new ArrayList<>();
		while(left <= right){
			for(int i=left;i<=right;i++){
				res.add(matrix[top][i]);
			}
			top++;
			if(top > bottom)
				break;
			for(int i=top;i<=bottom;i++){
				res.add(matrix[i][right]);
			}
			right--;
			if(left > right)
				break;
			for(int i=right;i>=left;i--){
				res.add(matrix[bottom][i]);
			}
			bottom--;
			if(top > bottom)
				break;
			for(int i=bottom;i>=top;i--){
				res.add(matrix[i][left]);
			}
			left++;
		}
		return res;
    }
}