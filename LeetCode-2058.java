// 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
// https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points
// optim, removed lists for positions and logic optimization
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
		if(head == null || head.next == null || head.next.next == null){
			return new int[]{-1,-1};
		}
        ListNode curr = head.next;
		ListNode prev = head;
		ListNode nextN = head.next.next;
        int firstVal = -1, lastVal = -1;
		int pos = 2, prevPos = 0;
        int minDist = Integer.MAX_VALUE;
		while(nextN != null){
            boolean found = false;
			// local minima && local maxima conditions
			if(curr.val < prev.val && curr.val < nextN.val
            || curr.val > prev.val && curr.val > nextN.val
            ){
                if(firstVal == -1){
                    firstVal = pos;
                }
                lastVal = pos;
                if(prevPos != 0 && pos - prevPos < minDist){
                    minDist = pos - prevPos;
                }
                prevPos = pos;
			}

			prev = curr;
			curr = nextN;
			nextN = nextN.next;
			pos++;
		}
        if(minDist == Integer.MAX_VALUE)
            return new int[]{-1,-1};
        
        int maxDist = lastVal - firstVal;
		
		return new int[]{minDist, maxDist};
		
    }
}

// init, used lists to store positions
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
		if(head == null && head.next == null && head.next.next == null){
			return new int[]{-1,-1};
		}
        ListNode curr = head.next;
		ListNode prev = head;
		ListNode nextN = head.next.next;
		List<Integer> minList = new ArrayList<>();
		List<Integer> maxList = new ArrayList<>();
		int pos = 2, prevPos = 0;
        int localMin = Integer.MAX_VALUE;
		while(nextN != null){
            boolean found = false;
			// local minima 
			if(curr.val < prev.val && curr.val < nextN.val){
				minList.add(pos);
                found = true;
			}
			// local maxima
			if(curr.val > prev.val && curr.val > nextN.val){
				maxList.add(pos);
                found = true;
			}
            if(found){
                if(prevPos != 0 && pos - prevPos < localMin){
                    localMin = pos - prevPos;
                }
                prevPos = pos;
            }
			prev = curr;
			curr = nextN;
			nextN = nextN.next;
			pos++;
		}
        if(localMin == Integer.MAX_VALUE)
            return new int[]{-1,-1};
        if(maxList.isEmpty() && minList.isEmpty())
            return new int[]{-1,-1};
        int lastVal = -1;
        int firstVal = -1;
        if(maxList.isEmpty()){
            lastVal = minList.get(minList.size()-1);
            firstVal = minList.get(0);
        }
        if(minList.isEmpty()){
            lastVal = maxList.get(maxList.size()-1);
            firstVal = maxList.get(0);
        }
        int maxLastVal = lastVal;
        int minFirstVal = firstVal;
        if(maxLastVal == -1)
		    maxLastVal = Math.max(maxList.get(maxList.size()-1), minList.get(minList.size()-1));
        if(minFirstVal == -1)
            minFirstVal = Math.min(minList.get(0), maxList.get(0));
        
        int maxDist = maxLastVal - minFirstVal;
		
		return new int[]{localMin, maxDist};
		
    }
}