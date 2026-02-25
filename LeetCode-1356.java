// 1356. Sort Integers by The Number of 1 Bits
// https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits
class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] temp = new Integer[arr.length];
        
        // Convert to Integer[] because Arrays.sort with comparator
        // does not work directly on primitive int[]
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        
        Arrays.sort(temp, (a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            
            if (countA == countB) {
                return a - b;   // sort by value if bit count same
            }
            return countA - countB; // sort by bit count
        });
        
        // Convert back to int[]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
        
        return arr;
    }
}