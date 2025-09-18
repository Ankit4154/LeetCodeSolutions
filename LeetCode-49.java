// 49. Group Anagrams
// https://leetcode.com/problems/group-anagrams/
// Optimal solution, Time : O(m*n*26) Space : O(n) not considering List<List<String>> as it is already required/provided for the output in the program
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> group = new ArrayList<>();
        List<String> list;
        int[] count;
        for(int i=0;i<strs.length;i++){
            String s = strs[i];
            count = new int[26];
			// count & store frequencies of each character
            for(int j=0;j<s.length();j++)
                count[s.charAt(j)- 'a']++;

            // Form a key with character and its count value
			// example : cat , key = a1c1t1
			String key = "";
            for(int j=0;j<26;j++){
                if(count[j]>0)
                    key += (char)(j+'a')+""+count[j];
            }

			// check in map if exact same key already exists
			// if yes, then add to the list
            if(map.containsKey(key))
                list = map.get(key);
            else
                list = new ArrayList();
            list.add(s);
            map.put(key, list);
        }
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            group.add(entry.getValue());
        }
        return group;
    }
}


// Brute force solution Time : O(m* n^2): TLE(Time Limit Exceeded) for high constraint values
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Character, Integer> map = new HashMap<>();
        List<List<String>> list = new ArrayList<>();
        int count = 0;
        int[] mark = new int[strs.length];
        for(int i=0;i<strs.length;i++){
            String s = strs[i];
            List<String> l = new ArrayList<>();
            if(mark[i]==1)
                continue;
            l.add(s);
            map = new HashMap<>();
            for(int k=0;k<s.length();k++){
                char ch = s.charAt(k);
                map.put(ch, map.getOrDefault(ch,0)+1);
            }
            for(int j=i+1;j<strs.length;j++){
                if(strs[j].length() != s.length())
                    continue;
                if(mark[j]==1)
                    continue;
                String s2 = strs[j];
                Map<Character, Integer> map2 = new HashMap<>(map);
                for(int k=0;k<s2.length();k++){
                    char c = s2.charAt(k);
                    if(map2.containsKey(c)){
                        if(map2.get(c) > 1)
                            map2.put(c, map2.get(c)-1);
                        else
                            map2.remove(c);
                    }
                }
                if(map2.size() == 0){               
                    l.add(s2);
                    mark[j]++;
                }
            }
            list.add(l);
        }
        return list;
    }
}