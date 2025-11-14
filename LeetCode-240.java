// 240. Search a 2D Matrix II
// https://leetcode.com/problems/search-a-2d-matrix-ii
class Solution {
	// since the array is sorted in special order
	// we can search our element in starting with 1st row 
	// and last column, 
	// if the target is greater than matrix[first_row][last_col]
	// (all the elements in first row)
	// it means that the target will definately not be present in the
	// first_row since all the elements in first row will be less than
	// target, so we move search down performing row++.
	// Similarly, if the target is greater than all the element present
	// in the last column, then we eliminate the column and move
	// search to the left performing col--
    public boolean searchMatrix(int[][] matrix, int target) {
		int row = 0, col = matrix[0].length - 1;
		while(row < matrix.length && col >= 0){
			int mid = matrix[row][col];
			if(target == mid)
				return true;
			else if(target > mid){
				row++;
			}else {
				col--;
			}
		}
		return false;
    }
}