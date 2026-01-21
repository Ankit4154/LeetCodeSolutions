// 556. Next Greater Element III
// https://leetcode.com/problems/next-greater-element-iii/

// optimal, O(n)
class Solution {
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();

        //Find pivot
        int i = arr.length - 2;
        while(i >= 0 && arr[i] >= arr[i + 1]){
            i--;
        }
        if(i < 0)
			return -1;

        //Find just larger digit
        int j = arr.length - 1;
        while(arr[j] <= arr[i]){
            j--;
        }

        //Swap
        swap(arr, i, j);

        //Reverse suffix
        reverse(arr, i + 1, arr.length - 1);

        //Overflow check
        long val = Long.parseLong(new String(arr));
        return val > Integer.MAX_VALUE ? -1 : (int) val;
    }

    private void swap(char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private void reverse(char[] a, int l, int r) {
        while(l < r) 
			swap(a, l++, r--);
    }
}


// initial, O(d! * d)
class Solution {
    public int nextGreaterElement(int n) {
        long res = solve(0, String.valueOf(n), n, Long.MAX_VALUE);
        if(res == Long.MAX_VALUE || res > Integer.MAX_VALUE || res < n)
            return -1;
		return (int)res;
    }
	
	private long solve(int start, String num, int n, long min){
		if(start == num.length()-1){
			long k = Long.parseLong(num);
			if(k != n && k > n){
				min = Math.min(min, k);
			}
			return min;
		}
        int s = Integer.parseInt(num.charAt(start)+"");
		for(int i=start;i<num.length();i++){
            int c = Integer.parseInt(num.charAt(i)+"");
            if(c == s){
                min = solve(start+1, num, n, min);
				continue;
			}
			num = swap(i, start, num.toCharArray());
			min = solve(start+1, num, n, min);
			num = swap(start, i, num.toCharArray());
		}
		return min;
	}
    private String swap(int a, int b, char[] ch){
		char temp = ch[a];
		ch[a] = ch[b];
		ch[b] = temp;
		return String.valueOf(ch);
	}
}