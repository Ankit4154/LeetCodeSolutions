// 1200. Minimum Absolute Difference
// https://leetcode.com/problems/minimum-absolute-difference
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> out = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i=1;i<arr.length;i++){
            int diff = Math.abs(arr[i]- arr[i-1]);
            if(diff <= min){
                if(diff < min)
                    out.clear();
                min = diff;
                List<Integer> list = new ArrayList<>();
                list.add(arr[i-1]);
                list.add(arr[i]);
                out.add(list);
            }
        }
        return out;
    }
}