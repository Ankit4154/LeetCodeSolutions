// 2833. Furthest Point From Origin
// https://leetcode.com/problems/furthest-point-from-origin
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
		// count the max frequency of L and R
		// whichever is maximum we'll replace that over blank _
		int countL = 0, countR = 0, countB = 0;
		for(int i=0;i<moves.length();i++){
			char ch = moves.charAt(i);
			if(ch == 'L')
				countL++;
			else if(ch == 'R')
				countR++;
			else
				countB++;
		}
		if(countL > countR){
			return countL + countB - countR;
		}else if(countR >= countL){
			return countR + countB - countL;
		}
		return 0;
    }
}