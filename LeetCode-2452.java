// 2452. Words Within Two Edits of Dictionary
// https://leetcode.com/problems/words-within-two-edits-of-dictionary
class Solution {
    
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> out = new ArrayList<>();
        for(String q : queries) {
            for(String d : dictionary) {
                int diff = 0;
                for(int i = 0; i < q.length(); i++) {
                    if(q.charAt(i) != d.charAt(i) && ++diff > 2) 
                        break;
                }
                if(diff <= 2) {
                    out.add(q);
                    break;
                }
            }
        }
		return out;
    }
}