// 378. Kth Smallest Element in a Sorted Matrix
// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix
class Solution {
	// intuition for search will be either start from top-left 
	// or from top-botton since its given rows and columns are sorted
	// in asending order.
	// We know that the mininum smallest element will be present at [0][0] index
	// and maximum smallest element will be present at [n][n] index.
	// So we have a range for performing binary search.
	// For each mid value, we count less than or equals 
	// elements present in the matrix. This takes O(n) time given the sorted
	// rows and columns. We start search from top-left and identify if 
	// the top-left element is less than or equals mid.
	// if less or equals, we count the no. of elements in that row ( by col+1)
	// and move to the next row (by row++)
	// if the element is greater than mid, then move to next column (by col--)
	// once we have the count available, if it is less than or equals k,
	// store the potential answer and move before mid doing high = mid - 1.
	// reducing the mid will increase the chances of getting Kth smallest element.
	// for example : 9th Smallest element will be 15, if we want to find 8th smallest 
	// element, we must search towards left direction eliminating the right half.
	// Similarly, if count is greater than k, we need to reduce the count by moving mid ahead doing low = mid + 1
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length - 1;
		int low = matrix[0][0], high = matrix[n][n];
        int ans = low;
		while(low <= high){
			int mid = low + (high-low)/2;
			int countLessThanOrEqualMid = count(mid, matrix);
            if(countLessThanOrEqualMid < k){
				low = mid + 1;
			}else {
				ans = mid;
				high = mid - 1;
			}
		}
		return ans;
    }

	private int count(int mid, int[][] matrix){
		int count = 0;
		int row = 0, col = matrix[0].length - 1;
		while(row < matrix.length && col >=0){
			if(matrix[row][col] <= mid){
				count += col + 1;
				row++;
			}else{
				col--;
			}
		}
		return count;
	}
}