// 2904. Shortest and Lexicographically Smallest Beautiful String
// https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string
// optim
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
		int left = 0, right = 0;
		int m = 0, min = Integer.MAX_VALUE;
		int bestLeft = -1, bestRight = -1;
		while(right < s.length()){
			char r = s.charAt(right);
			if(r == '1'){
				m++;
				while(m > k && left < s.length()){
					char l = s.charAt(left);
					if(l == '1')
						m--;
					left++;
				}
                while(m == k && s.charAt(left) == '0'){
                    left++;
                }
			}
			if(m == k){
                int diff = right - left + 1;
				if(diff < min){
					min = diff;
                    bestLeft = left;
					bestRight = right;
				}else if(diff == min){
                    int tempL = left;
					for(int i=bestLeft;i<=bestRight;i++){
						if(s.charAt(i) == s.charAt(tempL)){
                            tempL++;
                            continue;
                        }
						if(s.charAt(i) > s.charAt(tempL)){
							bestLeft = left;
							bestRight = right;
						}
						break;
					}
                }
			}
			right++;
		}
        if(bestLeft == -1)
			return "";
		
		return s.substring(bestLeft, bestRight+1);
    }
}

// init
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
		int left = 0, right = 0;
		int m = 0, min = Integer.MAX_VALUE;
		List<int[]> list = new ArrayList<>();
		while(right < s.length()){
			char r = s.charAt(right);
			if(r == '1'){
				m++;
				while(m > k && left < s.length()){
					char l = s.charAt(left);
					if(l == '1')
						m--;
					left++;
				}
                while(m == k && s.charAt(left) == '0'){
                    left++;
                }
			}
			if(m == k){
                int diff = right - left + 1;
				if(diff < min){
					min = diff;
                    list.clear();
					list.add(new int[]{left, right});
				}else if(diff == min){
                    list.add(new int[]{left, right});
                }
			}
			right++;
		}
        if(list.isEmpty())
            return "";
		List<String> stringList = new ArrayList<>();
		for(int[] p : list){
			StringBuilder sb = new StringBuilder();
			for(int i=p[0];i<=p[1];i++){
				sb.append(s.charAt(i));
			}
			stringList.add(sb.toString());
		}
		Collections.sort(stringList);
		return stringList.get(0);
    }
}