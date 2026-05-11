// 2553. Separate the Digits in an Array
// https://leetcode.com/problems/separate-the-digits-in-an-array
class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
		for(int n : nums){
            List<Integer> temp = new ArrayList<>();
			while(n > 0){
				int rem = n % 10;
				temp.add(rem);
				n = n / 10;
			}
            Collections.reverse(temp);
            list.addAll(temp);
		}
		int[] out = new int[list.size()];
		int i=0;
		for(Integer n : list){
			out[i++] = n;
		}
		return out;
    }
}
class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
		for(int n : nums){
            Stack<Integer> temp = new Stack<>();
			while(n > 0){
				int rem = n % 10;
				temp.push(rem);
				n = n / 10;
			}
            while(!temp.isEmpty()){
                list.add(temp.pop());
            }
		}
		int[] out = new int[list.size()];
		int i=0;
		for(Integer n : list){
			out[i++] = n;
		}
		return out;
    }
}