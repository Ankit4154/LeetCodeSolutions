// 2515. Shortest Distance to Target String in a Circular Array
// https://leetcode.com/problems/shortest-distance-to-target-string-in-a-circular-array
class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
		boolean present = false;
		int n = words.length;
		for(String word : words){
			if(word.equals(target)){
                present = true;
                break;
            }
		}
		// edge case if target is not present
		if(!present)
			return -1;
		// if start index equals target
		if(words[startIndex].equals(target))
			return 0;
		int left = startIndex-1;
		int right = startIndex+1;
		int dist = 0;
		int minDist = 0;
		boolean found = false;
		// scan left and get minimum distance
		while(!found){
			if(left < 0)
				left = n-1;
			dist++;
			if(words[left].equals(target)){
				minDist = dist;
				found = true;
			}
			left--;
		}
		found = false;
		dist = 0;
		// scan right and get minimum distance 
		while(!found){
			if(right == n)
				right = 0;
			dist++;
			if(words[right].equals(target)){
				minDist = Math.min(minDist,dist);
				found = true;
			}
			right++;
		}
		return minDist;
    }
}