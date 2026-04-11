// Q3. Minimum Operations to Transform Array into Alternating Prime
// https://leetcode.com/contest/biweekly-contest-180/problems/minimum-operations-to-transform-array-into-alternating-prime/
class Solution {
    public int minOperations(int[] nums) {
        // check which are prime and which are not
        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(i == 0 || i % 2 == 0){
                while(!isPrime(nums[i])){
                    nums[i] = nums[i]+1;
                    count++;
                }
            }else{
                while(isPrime(nums[i])){
                    nums[i]++;
                    count++;
                }
            }
        }
        return count;
    }
    boolean isPrime(int n){
        if(n == 1)
            return false;
        if(n == 2)
            return true;
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n % i == 0)
                return false;
        }
        return true;
    }
}