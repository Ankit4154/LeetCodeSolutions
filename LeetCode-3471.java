// 3471. Find the Largest Almost Missing Integer
// https://leetcode.com/problems/find-the-largest-almost-missing-integer
// solve for k=1 and k=n edge cases first and then for 1<=k<=n
class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int first = nums[0];
        int last = nums[n-1];
        int sideMax = Math.max(first, last);
        int max = -1;
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
            max = Math.max(max, num);
        }

        if(k == 1){
            if(map.get(max) == 1) // if max has only 1 occurrence
                return max;
            List<Integer> list = new ArrayList<>();
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                if(entry.getValue() == 1){
                    list.add(entry.getKey());
                }
            }
            if(list.isEmpty())
                return -1;
            Collections.sort(list, Collections.reverseOrder());
            return list.get(0);
        }
        if(k == n)
            return max;   

        if(k < n){
            if(map.get(sideMax) == 1) // if max has only 1 occurrence
                return sideMax;
            if(first > last){
                if(map.get(first) == 1){
                    return first;
                }else{
                    if(map.get(last) == 1)
                        return last;
                    else
                        return -1;
                }
            }else if(last > first){
                if(map.get(last) == 1){
                    return last;
                }else{
                    if(map.get(first) == 1)
                        return first;
                    else
                        return -1;
                }
            }else
                return -1;
        }        
        return -1;
    }
}