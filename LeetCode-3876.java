// 3876. Construct Uniform Parity Array II
// https://leetcode.com/problems/construct-uniform-parity-array-ii
// optim 2, removed sorting
class Solution {
    public boolean uniformArray(int[] nums1) {
		int len = nums1.length;
		int evens = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0;i<len;i++){
			int n  = nums1[i];
			if(n % 2 == 0){
				evens++;
			}else{
				// store minimum odd values
				min = Math.min(min, n);
			}
		}
		// all odd or all even
		if(evens == 0 || evens == len)
			return true;
		
        for(int i=0;i<len;i++){
			int n = nums1[i];
			// if even, then try to convert to odd
			if(n % 2 == 0){
				// if there is no lesser odd number present to be substracted
				// and make this even to odd by even-odd = odd
				// then not possible
				if(n < min)
					return false;
			}
		}
		return true;
		
    }
}
// naive
class Solution {
    public boolean uniformArray(int[] nums1) {
		int odds = 0, evens = 0;
		for(int i=0;i<nums1.length;i++){
			if(nums1[i] % 2 == 0){
				evens++;
			}else{
				odds++;
			}
		}
		if(evens == 0 || odds == 0)
			return true;
		Arrays.sort(nums1);
		if(nums1[0] % 2 == 0)
            return false; // not possible to convert this/first smallest even to odd.

        // there is atleast 1 odd number present in front to convert the
        // upcoming evens to odd
				
		return true;
		
    }
}


// init
class Solution {
    public boolean uniformArray(int[] nums1) {
		int odds = 0, evens = 0;
		for(int i=0;i<nums1.length;i++){
			if(nums1[i] % 2 == 0){
				evens++;
			}else{
				odds++;
			}
		}
		if(evens == 0 || odds == 0)
			return true;
		Arrays.sort(nums1);
		if(nums1[0] % 2 == 0)
            return false; // not possible to convert this/first smallest even to odd.

		// try converting smaller counting ones
		// if there is even 1 odd it means that we need to convert all to odd only
		// reason odd - odd = even, we need 2 pairs to dissolve 1 odd value.
		// and even - odd = odd , odd - even = odd.
		// so we keep the odd ones as odd only and focus on evens
		//int currOddCount = 1;
		//for(int i=1;i<nums1.length;i++){
		//	if(nums1[i] % 2 == 0){ // if even
		//		if(currOddCount >= 1){
		//
		//		}else{
		//			return false;
		//		}
		//	}else{
		//		currOddCount++;
		//	}
		//}
		
		return true;
		
    }
}