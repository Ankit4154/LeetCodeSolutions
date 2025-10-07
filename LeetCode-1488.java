// 1488. Avoid Flood in The City
// https://leetcode.com/problems/avoid-flood-in-the-city
class Solution {
    public int[] avoidFlood(int[] rains) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[rains.length];
        TreeSet<Integer> st = new TreeSet<Integer>();
        for(int i=0;i<rains.length;i++){
            int n = rains[i];
            if(n == 0){
                st.add(i);
                ans[i] = 1;
                continue;
            }
            ans[i] = -1;
            if(map.containsKey(n)){
                Integer index = st.ceiling(map.get(n));
                if(index == null)
                    return new int[0];
                ans[index] = n;
                ans[i] = -1;
                st.remove(index);
            }
            map.put(n, i);
        }
        return ans;
    }
}