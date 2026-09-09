// 3871. Count Commas in Range II
// https://leetcode.com/problems/count-commas-in-range-ii
// optim
class Solution {
    public long countCommas(long n) {
        if(n <= 999L)
			return 0;
        long sum = 0L;
		long base = 999L;
		long count = 1L;
		long limit = 1_000_000_000_000_000L;
		for(long i=1000L;i<=limit;i*=1000L){
			long endLimit = (i*1000L) - 1;
			if(n <= endLimit)
				return count * (n - base) + sum;
			base = endLimit;
			sum += count * (endLimit - i) + count; 
			count++;
		}
		return sum;
    }
}

// init, naive
class Solution {
    public long countCommas(long n) {
        if(n <= 999L)
			return 0;
		long sum = 0L;
		// 1 comma count
		if(n <= 999_999L)
			return n - 999L + sum;
		sum += 999_999L - 1000L + 1;
		// 2 commas count
		if(n <= 999_999_999L)
			return 2L*(n - 999_999L) + sum;
		sum += 2L*(999_999_999L - 1_000_000L) + 2;
		// 3 commas count
		if(n <= 999_999_999_999L)
			return 3L*(n - 999_999_999L) + sum;
		sum += 3L*(999_999_999_999L - 1_000_000_000L) + 3;
		// 4 commas count
		if(n <= 999_999_999_999_999L)
			return 4L*(n - 999_999_999_999L) + sum;
		sum += 4L*(999_999_999_999_999L - 1_000_000_000_000L) + 4;
        // 5 commas count
		return sum+5;
    }
}