// 74. Search a 2D Matrix
// https://leetcode.com/problems/search-a-2d-matrix/
// Optimized version O(log m * n)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
		int r = matrix.length, c = matrix[0].length;
        int low = 0, high = r*c - 1;
		while(low <= high){
			int mid = low + (high-low)/2;
			int indexVal = matrix[mid/c][mid%c];
			if(indexVal == target){
				return true;
			}else if(indexVal > target){
				high = mid - 1;
			}else{
				low = mid + 1;
			}
		}
		return false;
    }
}

// while traversing each row check if target is less than the last row element
// if it is less, perform binary seach on that row itself to find the target
// if it is not less, move on to the next row with same condition check
// Once the row is identified
// perform binary search on index and not on the array element values. O(n * logm)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i=0;i<matrix.length;i++){
			if(target <= matrix[i][matrix[i].length-1]){
				int high = matrix[i].length-1;
				int low = 0;
				while(low <= high){
					int mid = low + (high-low)/2;
					if(target == matrix[i][mid]){
						return true;
					}else if(target > matrix[i][mid]){
						low = mid+1;
					}else{
						high = mid-1;
					}
				}
			}
		}
		return false;
    }

}
