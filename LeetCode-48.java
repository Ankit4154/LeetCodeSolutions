// 48. Rotate Image
// https://leetcode.com/problems/rotate-image/
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
		int m = matrix[0].length;
		int left = 0, right = m-1, top = 0, bottom = n-1;
		while(left < right){
			for(int i=0;i<right-left;i++){
				top = left;
				bottom = right;
				// save topleft value
				int topLeft = matrix[top][left + i];
				// move bottomLeft to topLeft
				matrix[top][left + i] = matrix[bottom - i][left];
				// move bottomRight to bottomLeft
				matrix[bottom - i][left] = matrix[bottom][right - i];
				// move topRight to bottomRight
				matrix[bottom][right - i] = matrix[top + i][right];
				// move saved topLeft to topRight
				matrix[top + i][right] = topLeft;
			}
			left++;
			right--;
		}
		
    }
}