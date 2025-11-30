// 2095. Delete the Middle Node of a Linked List
// https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list
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
    public ListNode deleteMiddle(ListNode head) {
        // move fast and slow pointer to reach the middle
		// slow's next will point to the middle element
		// Input: head = [1,3,4,7,1,2,6]
		// Output: [1,3,4,1,2,6]
		// Input: head = [1,2,3,4]
		// Output: [1,2,4]
		// Input: head = [2,1]
		// Output: [2]
		// Input: head = [1]
		// Output: []
		if(head == null || head.next == null)
			return null;
		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = slow;
		while(fast != null && fast.next != null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = prev.next.next;
		return head;
    }
}