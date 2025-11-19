// 19. Remove Nth Node From End of List
// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1->2->3->4
		// n=4 then remove (n-4) (4-4) 0th index node -> 1
		// n=1 then remove (n-1) (4-1) 3rd index node -> 4
		// n=2 then remove (n-2) (4-2) 2nd index node -> 3
		// [1,2,3,4,5]
		// n=2 then remove (n-2) (5-2) 3nd index node -> 4
		// first find the nth node to be removed
		// move fast pointer to nth node
		if(head.next == null)
			return null;
		ListNode fast = head;
		int count = 0;
		while(count < n && fast != null){
			fast = fast.next;
			count++;
		}
		// edge case if fast is already reached null
        if(fast == null){
            return head.next;
        }
		// move both slow and fast pointers, and stop when fast reaches end
		ListNode slow = head;
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next;
		}
		// slow.next will have the node to be removed
	    slow.next = slow.next.next;
		return head;
    }
}