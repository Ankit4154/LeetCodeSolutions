// 3751. Total Waviness of Numbers in Range I
// https://leetcode.com/problems/total-waviness-of-numbers-in-range-i/
class Solution {
    public int totalWaviness(int num1, int num2) {
        // brute force
		int sum = 0;
		// edge case for num2
		if(num2 <= 100)
			return 0;
		for(int i=num1;i<=num2;i++){
			sum += getWaviness(i+"");
		}
		return sum;
    }
	int getWaviness(String n){
		// edge case for num1
		int len = n.length();
		if(len < 3)
			return 0;

		int waviness = 0;

		for(int i=1; i<len-1;i++){
			char left = n.charAt(i - 1);
			char curr = n.charAt(i);
			char right = n.charAt(i + 1);

			if(curr > left && curr > right)
				waviness++;
			else if(curr < left && curr < right)
				waviness++;
		}

		return waviness;
		
	}
}
// init
class Solution {
    public int totalWaviness(int num1, int num2) {
        // brute force
		int sum = 0;
		// edge case for num2
		if(num2 <= 100)
			return 0;
		for(int i=num1;i<=num2;i++){
			sum += getWaviness(i+"");
		}
		return sum;
    }
	int getWaviness(String n){
		// edge case for num1
		int len = n.length();
		if(len < 3)
			return 0;

		int peak = 0;
		int valley = 0;
		if(n.charAt(0) < n.charAt(1) && n.charAt(1) > n.charAt(2)){
            peak++;
        }else if(n.charAt(0) > n.charAt(1) && n.charAt(1) < n.charAt(2)){
            valley++;
        }
        if(len >= 4){
			if(n.charAt(1) < n.charAt(2) && n.charAt(2) > n.charAt(3)){
				peak++;
			}else if(n.charAt(1) > n.charAt(2) && n.charAt(2) < n.charAt(3)){
				valley++;
			}
        }
        if(len >= 5){			
			if(n.charAt(2) < n.charAt(3) && n.charAt(3) > n.charAt(4)){
				peak++;
			}else if(n.charAt(2) > n.charAt(3) && n.charAt(3) < n.charAt(4)){
				valley++;
			}
		}
		return peak + valley;
		
	}
}