// 3069. Distribute Elements Into Two Arrays I
// https://leetcode.com/problems/distribute-elements-into-two-arrays-i
class Solution {
    public int[] resultArray(int[] nums){
        int n = nums.length;

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        int i = 0, j = 0;

        arr1[i] = nums[0];
        arr2[j] = nums[1];

        for(int k = 2; k < n; k++){
            if(arr1[i] > arr2[j]){
                arr1[++i] = nums[k];
            }else{
                arr2[++j] = nums[k];
            }
        }

        int[] out = new int[n];
        int index = 0;

        for(int k = 0; k <= i; k++){
            out[index++] = arr1[k];
        }

        for(int k = 0; k <= j; k++) {
            out[index++] = arr2[k];
        }

        return out;
    }
}

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        for(int i=2;i<n;i++){
            if(arr1.get(arr1.size()-1) > arr2.get(arr2.size()-1)){
                arr1.add(nums[i]);
            }else{
                arr2.add(nums[i]);
            }
        }
        int i=0;
        int[] out = new int[n];
        for(int k : arr1)
            out[i++] = k;
        for(int k : arr2)
            out[i++] = k;
        return out;
    }
}