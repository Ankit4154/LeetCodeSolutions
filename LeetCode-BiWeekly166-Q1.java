// Q1. Majority Frequency Characters
// https://leetcode.com/contest/biweekly-contest-166/problems/majority-frequency-characters/description/
class Solution {
    public String majorityFrequencyGroup(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, List<Character>> map2 = new HashMap<>();
        List<Character> out = new ArrayList<>();
        int max = 0, maxVal = 0;
        if(s.length() == 1)
                return s;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            int val = entry.getValue();
            if(map2.containsKey(val)){
                map2.get(val).add(entry.getKey());
            }else{
                List<Character> list = new ArrayList<>();
                list.add(entry.getKey());
                map2.put(val, list);
            }
            if(map2.get(val).size() > max){
                max = map2.get(val).size();
                out = map2.get(val);
                maxVal = val;
            }
            if(map2.get(val).size() == max){
                if(val > maxVal){
                    out = map2.get(val);
                    maxVal = val;
                }else{
                    out = map2.get(maxVal);
                }
            }
        }
        String o = "";
        for(char c : out){
            o += c;
        }
        return o;
    }
}