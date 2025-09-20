// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer>[] freq = new List[nums.length+1];
        int[] output = new int[k];
        int o = 0;
        for(int x: nums){
            map.put(x, map.getOrDefault(x, 0)+1);
        }
        for(int i=0;i<freq.length;i++){
            freq[i] = new ArrayList<>();
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            freq[entry.getValue()].add(entry.getKey());
        }
        
        for(int i=freq.length-1;i>0;i--){
            for(int l = freq[i].size()-1; l >= 0 && o < k;l--){
                output[o++] = freq[i].get(l);
            }
            if(o == k)
                return output;
        }
        return output;
    }
}