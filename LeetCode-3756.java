// 3756. Concatenate Non-Zero Digits and Multiply by Sum II
// https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii
// optim, O(n + q)
class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
		int MOD = 1_000_000_007;
        int[] first = new int[n];
        int[] last = new int[n];
        int[] digits = new int[n];

		int count = 0;
		for(int i=0;i<n;i++){
			int digit = s.charAt(i) - '0';
			if(digit != 0){
				digits[count++] = digit;
			}
		}

        int nextSeen = count;
        for(int i=n-1;i>=0;i--){
            if(s.charAt(i) != '0'){
                nextSeen--;
            }
            first[i] = nextSeen;
        }

        int lastSeen = -1;
        for(int i=0;i<n;i++){
            if(s.charAt(i) != '0'){
                lastSeen++;
            }
            last[i] = lastSeen;
        }
        int[] out = new int[queries.length];
		int[] prefixSum = new int[count+1];
		int[] hash = new int[count+1];
		int[] pow10 = new int[count+1];
		pow10[0] = 1;
		for(int i=1;i<=count;i++){
			prefixSum[i] = prefixSum[i-1] + digits[i-1];
			hash[i] = (int)(((long)hash[i-1] * 10 + digits[i-1]) % MOD);
			pow10[i] = (int)((long)pow10[i-1] * 10 % MOD);
		}
		for(int i=0;i<queries.length;i++){
			int l = queries[i][0];
			int r = queries[i][1];
			int leftInd = first[l];
            int rightInd = last[r];
			if(leftInd > rightInd){
				out[i] = 0;
				continue;
			}
			int sum = prefixSum[rightInd+1] - prefixSum[leftInd];
			int len = rightInd - leftInd + 1;
			long num = (hash[rightInd+1] - (long)hash[leftInd] * pow10[len]) % MOD;
			if(num < 0)
				num += MOD;
			out[i] = (int)((long)sum * num % MOD);
		}
		return out;
    }
}

// init, Time : O(n + q log n)
class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
		int MOD = 1_000_000_007;
		int[] pos = new int[n];
		int[] digits = new int[n];
		int count = 0;
		for(int i=0;i<n;i++){
			int digit = s.charAt(i) - '0';
			if(digit != 0){
				pos[count] = i;
				digits[count++] = digit;
			}
		}
		int[] out = new int[queries.length];
		int[] prefixSum = new int[count+1];
		int[] hash = new int[count+1];
		int[] pow10 = new int[count+1];
		pow10[0] = 1;
		for(int i=1;i<=count;i++){
			prefixSum[i] = prefixSum[i-1] + digits[i-1];
			hash[i] = (int)(((long)hash[i-1] * 10 + digits[i-1]) % MOD);
			pow10[i] = (int)((long)pow10[i-1] * 10 % MOD);
		}
		for(int i=0;i<queries.length;i++){
			int l = queries[i][0];
			int r = queries[i][1];
			int leftInd = lowerBound(l, count-1, 0, pos);
			int rightInd = upperBound(r, count-1, 0, pos);
			if(leftInd > rightInd){
				out[i] = 0;
				continue;
			}
			int sum = prefixSum[rightInd+1] - prefixSum[leftInd];
			int len = rightInd - leftInd + 1;
			long num = (hash[rightInd+1] - (long)hash[leftInd] * pow10[len]) % MOD;
			if(num < 0)
				num += MOD;
			out[i] = (int)((long)sum * num % MOD);
		}
		return out;
    }
	
	int lowerBound(int target, int high, int low, int[] pos){
		int ans = high + 1;
		while(low <= high){
			int mid = low + (high-low)/2;
			if(pos[mid] >= target){
				ans = mid;
				high = mid - 1;
			}else{
				low = mid + 1;
			}
		}
		return ans;
	}
	int upperBound(int target, int high, int low, int[] pos){
		int ans = -1;
		while(low <= high){
			int mid = low + (high-low)/2;
			if(pos[mid] <= target){
				ans = mid;
				low = mid + 1;
			}else{
				high = mid - 1;
			}
		}
		return ans;
	}
}