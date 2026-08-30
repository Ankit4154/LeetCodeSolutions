// 2091. Removing Minimum and Maximum From Array
// https://leetcode.com/problems/removing-minimum-and-maximum-from-array
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if(n==1)
          return 1;
int max = Integer.MIN_VALUE;
int maxPos = -1;
int minPos = -1;
int min = Integer.MAX_VALUE;
for(int i=0;i<n;i++) {
  if(nums[i] < min) {
      min = nums[i];
      minPos = i;
  }
if(nums[i] > max) {
  max = nums[i];
  maxPos = i;
  }
}

int left = Math.min(minPos, maxPos);
        int right = Math.max(minPos, maxPos);

        int bothLeft = right + 1;
        int bothRight = n - left;
        int oneLeftOneRight = (left + 1) + (n - right);

        return Math.min(bothLeft, Math.min(bothRight, oneLeftOneRight));
        
    }
}
