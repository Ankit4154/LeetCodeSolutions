// 3612. Process String with Special Operations I
// https://leetcode.com/problems/process-string-with-special-operations-i
class Solution {
    public String processStr(String s) {
        StringBuilder result = new StringBuilder();
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '#'){
				result.append(result);
			}else if(c == '%'){
				result.reverse();
			}else if(c == '*'){
                if(!result.isEmpty())
				    result.deleteCharAt(result.length()-1);
			}else{
				result.append(c);
			}
		}
		return result.toString();
		
    }
}