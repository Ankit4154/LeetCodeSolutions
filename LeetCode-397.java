// 397. Integer Replacement
// https://leetcode.com/problems/integer-replacement/

//optim 2, Time : O(logn), Space : O(1)
class Solution {
    public int integerReplacement(int n) {
        long num = n; // avoid overflow
        int steps = 0;

        while(num != 1){
            if(num % 2 == 0){
                num /= 2;
            }else{
                if(num == 3 || num % 4 == 1){
                    num--;   // go down
                } else {
                    num++;   // go up
                }
            }
            steps++;
        }
        return steps;
    }
}

//optim, Time : O(logn), Space : O(logn)
class Solution {
	// state for n is unbounded and not continuous, 
	// state can either be n+1 or n-1
	// so fixed/bounded dp can't be used
	// also, only some / sparse values need to be explored
	// and not all from n,n-1..to 1
	Map<Long, Integer> dp;
    public int integerReplacement(int n) {
        dp = new HashMap<>();
		return solve((long)n);
    }
	int solve(long n){
		if(n == 1)
			return 0;
		if(dp.containsKey(n))
			return dp.get(n);
		int min = 0;
		if(n%2==0){
			min = 1 + solve(n/2);
		}else{
			min = 1 + Math.min(solve(n-1),solve(n+1));
		}
		dp.put(n, min);
		return min;
	}
}

// init , Stack overflow
class Solution {
    public int integerReplacement(int n) {
		return solve(n);
    }
	int solve(int n){
		if(n <= 0)
			return 0;
		if(n == 1)
			return 1;
		int min = 0;
		if(n%2==0){
			min = 1+solve(n/2);
		}else{
			min = 1 + Math.min(solve(n-1),solve(n+1));
		}
		return min;
	}
}