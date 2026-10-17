// 3551. Minimum Swaps to Sort by Digit Sum
// https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            int sum1 = getDigitSum(a[0]);
            int sum2 = getDigitSum(b[0]);

            if(sum1 != sum2)
                return sum1 - sum2;

            return a[0] - b[0];
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for(int i = 0; i < n; i++) {
            if(visited[i] || arr[i][1] == i)
                continue;

            int j = i;
            int cycle = 0;

            while(!visited[j]) {
                visited[j] = true;
                j = arr[j][1];
                cycle++;
            }

            swaps += cycle - 1;
        }

        return swaps;
    }
	private int getDigitSum(int n){
		int sum = 0;
		while(n > 0){
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
}