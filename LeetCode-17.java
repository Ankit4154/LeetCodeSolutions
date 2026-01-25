// 17. Letter Combinations of a Phone Number
// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

// optimized
class Solution {
	final String[] map = {
        "", "", "abc", "def", "ghi", "jkl",
        "mno", "pqrs", "tuv", "wxyz"
		};
    List<String> comb = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
		List<Character> list = new ArrayList<>();
		solve(0, digits, list);
        return comb;
    }
	
	private void solve(int ind, String digits, List<Character> list){
		if(ind == digits.length()){
			// not needed as we are now not calling multiple recursion calls
            // if(list.size() < digits.length())
            //     return;            
			String out = "";
			for(Character x : list){
				out += x;
			}
			comb.add(out);
			return;
		}
        // String s = map.get(Integer.parseInt(digits.charAt(ind)+""));
		// use below to get number from a character
        // String s = map.get(digits.charAt(ind) - '0');
		// Changed map implementation to String array
		String s = map[digits.charAt(ind) - '0'];
        for(int j=0;j<s.length();j++){
            list.add(s.charAt(j));
            solve(ind+1, digits, list);
            list.remove(list.size()-1);
        }
	}
    
}

class Solution {
	Map<Integer, String> map = new HashMap<>();
    List<String> comb = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
		char ch = 'a';
		for(int i=2;i<=9;i++){
			String s = "";
			for(int j=0;j<3;j++){
				s += ch+"";
				ch++;
			}
            if(i== 7 || i == 9){
                s += ch+"";
                ch++;
            }
			map.put(i, s);
		}
		List<Character> list = new ArrayList<>();
		solve(0, digits, list);
        return comb;
    }
	
	private void solve(int ind, String digits, List<Character> list){
		if(ind == digits.length()){
            if(list.size() < digits.length())
                return;            
			String out = "";
			for(Character x : list){
				out += x;
			}
			comb.add(out);
			return;
		}
        String s = map.get(Integer.parseInt(digits.charAt(ind)+""));
        for(int j=0;j<s.length();j++){
            list.add(s.charAt(j));
            solve(ind+1, digits, list);
            list.remove(list.size()-1);
        }
	}
    
}