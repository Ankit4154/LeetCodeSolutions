// 1331. Rank Transform of an Array
// https://leetcode.com/problems/rank-transform-of-an-array
// optim
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for(int a : sorted){
            if(!map.containsKey(a)){
                map.put(a, rank++);
            }
        }
        for(int i=0;i<arr.length;i++){
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
}
// init
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] original = arr.clone();
        Arrays.sort(arr);
        int[] rank = new int[arr.length];
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int a : arr){
            if(!map.containsKey(a)){
                map.put(a, ++count);
            }
        }
        for(int i=0;i<original.length;i++){
            rank[i] = map.get(original[i]);
        }
        return rank;
    }
}