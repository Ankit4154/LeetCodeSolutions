// 670. Maximum Swap
// https://leetcode.com/problems/maximum-swap
// optimized Greedy O(n)
class Solution {
    public int maximumSwap(int num){
		// take char array of numbers for easy int val retrieval
		// by doing '9'(char type) - '0' = 9(int type)
		char[] digits = String.valueOf(num).toCharArray();
		int[] lastPosition = new int[10];
		
		// store last position of each digit
		for(int i=0; i<digits.length; i++){
			lastPosition[digits[i] - '0'] = i;
			// example : num = 9569
			// lastPosition array will populate last indexes of respective digits : 
			//  0,1,2,3,4,5,6,7,8,9  digits
			// [0,0,0,0,0,1,2,0,0,3]  indexes
		}
		// Form larger number
		for(int i=0;i<digits.length;i++){
			int curr = digits[i] - '0';
			// check for bigger digits later
			for(int d=9; d>curr; d--){
				// if bigger number's index is greater than current index's number
				// then swap and return
				if(lastPosition[d] > i){
					char temp = digits[i];
                    digits[i] = digits[lastPosition[d]];
                    digits[lastPosition[d]] = temp;
                    return Integer.parseInt(new String(digits));
				}
			}
		}
		// if no other number was found then
		// the existing/given number is the largest
		return num;
	}
}


// intial
class Solution {
    public int maximumSwap(int num) {
        int max = maxNum(0, String.valueOf(num), 1, num);
        return max;
    }
	int maxNum(int start, String num, int k, int maxN){
		if(start == num.length() || k == 0){
			int n = Integer.parseInt(num);
			if(n > maxN){
				maxN = n;
			}
            return maxN;
		}
		int s = Integer.parseInt(num.charAt(start)+"");
		for(int i=start;i<num.length();i++){
			int n = Integer.parseInt(num.charAt(i)+"");
            if(n < s){
				continue;
			}
            if(n == s){
                maxN = maxNum(start + 1, num, k, maxN);
				continue;
			}
			num = swap(start, i, num.toCharArray());
			maxN = maxNum(start + 1, num, k-1, maxN);
			num = swap(i, start, num.toCharArray());
		}
		return maxN;
	}
	String swap(int a, int b, char[] ch){
		char temp = ch[a];
		ch[a] = ch[b];
		ch[b] = temp;
		return String.valueOf(ch);
	}
}