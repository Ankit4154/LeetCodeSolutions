// 496. Next Greater Element I
// https://leetcode.com/problems/next-greater-element-i/description/
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
	// Have a map for a constant lookup for nums1 values reducing O(m*n) to O(m+n)
	// extra Size O(m + m = 2m) 1 for Map and other for Stack
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> s = new Stack<>();
        int[] out = new int[nums1.length];
        for(int i=0;i<nums1.length;i++)
            map.put(nums1[i], i);
        
        for(int i=0;i<out.length;i++)
            out[i] = -1;
        
        for(int i=0;i<nums2.length;i++){
            while(!s.isEmpty() && nums2[i] > s.peek()){
                int val = s.pop();
                out[map.get(val)] = nums2[i];
            }
			// if the key is present/required to be calculated only then
			// push to stack
            if(map.containsKey(nums2[i]))
                s.push(nums2[i]);
        }
        return out;
    }
}


// Brute force iterate through each of the m and n elements to find next greater element
// Time : O(m * n) , extra Size : O(1)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] out = new int[nums1.length];
        if(nums2.length == 1){
            out[0] = -1;
            return out;
        }
        for(int i=0;i<nums1.length;i++){
            out[i] = -1;
            for(int j=nums2.length-1;j>=0 && nums2[j] != nums1[i];j--){
                if(nums2[j] > nums2[j-1]){
                    if(nums2[j] > nums1[i])
                        out[i] =  nums2[j];
                }
            }
        }
        return out;
    }
}