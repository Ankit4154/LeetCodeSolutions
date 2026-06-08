// 2161. Partition Array According to Given Pivot
// https://leetcode.com/problems/partition-array-according-to-given-pivot
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
		int less = 0, equal = 0;

        for(int num : nums) {
            if(num < pivot) less++;
            else if(num == pivot) equal++;
        }

        int[] out = new int[nums.length];

        int left = 0;
        int mid = less;
        int right = less + equal;

        for(int num : nums) {
            if(num < pivot) {
                out[left++] = num;
            } else if(num == pivot) {
                out[mid++] = num;
            } else {
                out[right++] = num;
            }
        }
        return out;
		
    }
}
// init
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
		int n = nums.length;
        int lowCount = 0, sameCount = 0;
		for(int i=0;i<n;i++){
			if(nums[i] < pivot)
				lowCount++;
			if(nums[i] == pivot)
				sameCount++;
		}
		int highCount = n - lowCount - sameCount;
		int highCountStart = lowCount + sameCount;
		int sameCountStart = lowCount;
		int[] out = new int[n];
		int j = 0;
		for(int i=0;i<n;i++){
			if(nums[i] < pivot && lowCount > 0){
				out[j++] = nums[i];
				lowCount--;
			}else if(nums[i] > pivot && highCount > 0){
				out[highCountStart++] = nums[i];
				highCount--;
			}else{
				out[sameCountStart++] = nums[i];
			}
		}
		return out;
		
    }
}