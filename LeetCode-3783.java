// 3783. Mirror Distance of an Integer
// https://leetcode.com/problems/mirror-distance-of-an-integer/
// optim
class Solution {
    public int mirrorDistance(int n) {
        int rev = 0;
        int temp = n;
        while(temp > 0){
            int rem = temp % 10;
            rev = rev * 10 + rem;
            temp /= 10;
        }
        return Math.abs(n-rev);
    }
}


class Solution {
    public int mirrorDistance(int n) {
        StringBuilder sb = new StringBuilder(n+"");
		String rev = sb.reverse().toString();
		int revv = Integer.parseInt(rev);
		return Math.abs(n-revv);
	}
}