// 3483. Unique 3-Digit Even Numbers
// https://leetcode.com/problems/unique-3-digit-even-numbers/
// optim
class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        for(int digit : digits){
            freq[digit]++;
        }

        int count = 0;

        for(int a = 1; a <= 9; a++){
            if(freq[a] == 0)
                continue;

            freq[a]--;

            for(int b = 0; b <= 9; b++){
                if(freq[b] == 0)
                    continue;

                freq[b]--;

                for(int c = 0; c <= 8; c += 2){
                    if(freq[c] == 0)
                        continue;

                    count++;
                }

                freq[b]++;
            }

            freq[a]++;
        }

        return count;
	}
}

// brute forced, all possible combinations
class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        for(int digit : digits){
            freq[digit]++;
        }

        int count = 0;

        for(int num = 100; num <= 998; num += 2){
            int[] available = freq.clone();

            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            if(available[a] == 0)
                continue;
            available[a]--;

            if(available[b] == 0)
                continue;
            available[b]--;

            if(available[c] == 0)
                continue;

            count++;
        }

        return count;
	}
}
// init, errored
class Solution {
    public int totalNumbers(int[] digits) {
        int n = digits.length;
		int zeroCount = 0;
		int evenCount = 0;
		int duplicateCount = 0;
		int[] nums = new int[10];
		for(int i=0;i<n;i++){
			if(digits[i] == 0){
				zeroCount++;
				evenCount++;
			}else if(digits[i]%2 == 0){
				evenCount++;
			}
            if(nums[digits[i]] > 0){
				duplicateCount++;
			}
			nums[digits[i]]++;
		}
        if(evenCount == n && zeroCount == 0)
            return 1;
		if(duplicateCount == 0 && zeroCount == 0){
			return fact(n-1)*evenCount;
		}
		if(duplicateCount > 0 && zeroCount == 0){
			return (fact(n-1)*evenCount) / 2*duplicateCount;
		}
		return ((fact(n-1)*evenCount) / 2*duplicateCount) - zeroCount;
    }
	
	private int fact(int n){
		if(n <= 1)
			return 1;
		return n * fact(n-1);
	}
}