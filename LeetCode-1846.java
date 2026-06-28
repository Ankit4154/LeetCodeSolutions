// 1846. Maximum Element After Decreasing and Rearranging
// https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging
// optim
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] count = new int[n + 1];

        for(int x : arr){
            count[Math.min(x, n)]++;
        }
        int ans = 0;
        for(int i = 1; i <= n; i++){
            ans = Math.min(ans + count[i], i);
        }

        return ans;
    }
}
// init
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int len = arr.length;
		Arrays.sort(arr);
		int pos = 1;
		for(int i=1;i<len;i++){
			if(arr[i] > pos)
				pos++;
		}
		return pos;
    }
}