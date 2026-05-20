// 2657. Find the Prefix Common Array of Two Arrays
// https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        Set<Integer> setA = new HashSet<>();
		Set<Integer> setB = new HashSet<>();
		// edge case 
		if(A.length == 1){
            if(A[0] == B[0])
                return new int[]{1};
            else
                return new int[]{0};
        }
		int count = 0;
		int[] out = new int[A.length];
		for(int i=0;i<A.length;i++){
            setA.add(A[i]);
			setB.add(B[i]);
            if(A[i] == B[i]){
                count++;
                out[i] = count;
                continue;
            }
			if(setA.contains(B[i])){
				count++;
			}
			if(setB.contains(A[i])){
				count++;
			}
			out[i] = count;
		}
		return out;
    }
}