// 25. Reverse Nodes in k-Group
// https://leetcode.com/problems/reverse-nodes-in-k-group/
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
	// Input: head = [1,2,3,4,5], k = 2
	// Output: [2,1,4,3,5]
	// Approach : Have a method to reverse the k group list and returning head of that list.
	// Traverse till k elements and populate/store the nextStart pointer with next head/start pointer
	// 1,2, nextStart = 3 , current start = 1(head)
	// Pass the head of the list to the method along with k. [1,2,3,4,5] , k = 2
	// store the retrieved list in newHead  2->1
	// Add the newHead in merged.next and increment merged to start
	// -1 -> 2 -> 1 -> null   merged = 1 (start)
	// move current pointer to nextStart , 3
	// also update start pointer to nextStart, 3
	// in next iteration, start = 3, nextStart = 5 list = [3,4,5] , k = 2
	// reversedList = [4,3] merged.next = 4 (1->4->3) merged = 3 (start) 
	// list = -1 -> 2 -> 1 -> 4 -> 3
	// When i!=k, store the potential non-k group values in remaining (rem)
	// When i==k, clear off the rem pointer and reset
	// After the loop ends, merge both the lists merged and remaining.
    public ListNode reverseKGroup(ListNode head, int k) {
		
		ListNode curr = head;
		ListNode start = head;
		ListNode dummy2 = new ListNode(-1);
		ListNode rem = dummy2;
		ListNode dummy = new ListNode(-1);
		ListNode merged = dummy;
		int i = 0;
		while(curr != null){
			i++;
			if(i==k){
				i=0;
				rem = dummy2; // clear leftover list
				dummy2.next = null; // clear its next pointer
                ListNode nextStart = curr.next;
				ListNode startHead = reverseList(start, k); // 3,2,1
                merged.next = startHead;
                merged = start;
                curr = nextStart;
				start = nextStart;
			}else{
                rem.next = curr;
                rem = rem.next;
                curr = curr.next;
            }
		}
        merged.next = dummy2.next;
		return dummy.next;
    }
	
	public ListNode reverseList(ListNode start, int k){
		ListNode curr = start;
		ListNode prev = null;
		int count = 0;
		while(curr != null && count < k){
			ListNode temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
			count++;
		}
		return prev;
	}
}