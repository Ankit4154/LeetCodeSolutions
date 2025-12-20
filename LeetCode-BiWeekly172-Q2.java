// Q2. Maximum Sum of Three Numbers Divisible by Three
// https://leetcode.com/contest/biweekly-contest-172/problems/maximum-sum-of-three-numbers-divisible-by-three/
class Solution {
    public int maximumSum(int[] nums) {
        List<Integer> r0 = new ArrayList<>();
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();

        // Split numbers by remainder
        for(int num : nums) {
            if(num % 3 == 0) 
                r0.add(num);
            else if(num % 3 == 1)
                r1.add(num);
            else 
                r2.add(num);
        }

        // Sort in descending order
        r0.sort(Collections.reverseOrder());
        r1.sort(Collections.reverseOrder());
        r2.sort(Collections.reverseOrder());

        int maxSum = 0;

        // Case 1: 0 + 0 + 0
        if(r0.size() >= 3){
            maxSum = Math.max(maxSum, r0.get(0) + r0.get(1) + r0.get(2));
        }

        // Case 2: 1 + 1 + 1
        if(r1.size() >= 3){
            maxSum = Math.max(maxSum, r1.get(0) + r1.get(1) + r1.get(2));
        }

        // Case 3: 2 + 2 + 2
        if(r2.size() >= 3){
            maxSum = Math.max(maxSum, r2.get(0) + r2.get(1) + r2.get(2));
        }

        // Case 4: 0 + 1 + 2
        if(r0.size() >= 1 && r1.size() >= 1 && r2.size() >= 1) {
            maxSum = Math.max(maxSum, r0.get(0) + r1.get(0) + r2.get(0));
        }

        return maxSum;
    }
}©leetcode