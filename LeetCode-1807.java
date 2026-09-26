// 1807. Evaluate the Bracket Pairs of a String
// https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
		Map<String, String> map = new HashMap<>();
		for(List<String> list : knowledge){
			map.put(list.get(0), list.get(1));
		}
		StringBuilder out = new StringBuilder();
		for(int i=0;i<n;i++){
			char c = s.charAt(i);
			if(c == '('){
				i++;
				char close = s.charAt(i);
				StringBuilder key = new StringBuilder();
				while(i<n && close != ')'){
					close = s.charAt(i);
					key.append(close);
					i++;
				}
				
				key.deleteCharAt(key.length()-1);
                String k = key.toString();
                if(map.containsKey(k)){
					out.append(map.get(k));
				}else{
					out.append("?");
				}
                i--;
			}else{
				out.append(c);
			}
		}
		return out.toString();
    }
}