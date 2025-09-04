// 1423. Maximum Points You Can Obtain from Cards
// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int left = k-1, right = cardPoints.length -1, leftSum = 0, rightSum = 0, maxSum = 0;
        for(int i=0;i<k;i++){
            leftSum += cardPoints[i];
        }
        maxSum = Math.max(leftSum, rightSum);
        while(right >= cardPoints.length-k){
            rightSum += cardPoints[right];
            leftSum -= cardPoints[left];
            maxSum = Math.max(maxSum, leftSum+rightSum);
            left--;
            right--;
        }
        return maxSum;
    }
}