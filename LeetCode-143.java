// 143. Reorder List
// https://leetcode.com/problems/reorder-list
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
	// Solution approach : if the half part of the list will be reversed
	// then we will be able to link those correctly to the first half
	// to form the answer list.
	// identify the mid part from where the list will be revered
	// using fast and slow pointers, slow pointers next will always point to 
	// the second half. We can take slow pointer's next for both odd or even
	// elements present in the list.
	// Reverse the second half part 
	// Traverse list from head and start of the second half simultaneously
	// and re-assign the required pointers
	// in the end, ensure to assign the left out values and null in the end.
    public void reorderList(ListNode head) {
        // 1->2->3->4
		// output : 1->4->2->3
		// 1->2->3->4->5
		// output : 1->5->2->4->3
		// first find the mid part and the rest part to reverse
		// slow pointer's next will point the the second part to be reversed
		if(head == null || head.next == null)
			return;
		ListNode slow = head;
		ListNode fast = head.next;
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		
		// reverse the second part
		ListNode curr = slow.next;
		ListNode prev = null;
		while(curr != null){
			// save curr's next
			ListNode t = curr.next;
			// change curr.next to prev
			curr.next = prev;
			// change prev to curr
			prev = curr;
			// change curr to curr.next / t
			curr = t;
		}
		// reorder the list
		ListNode sec = prev;
		curr = head;
		// 1->2->3->4->5
		// output : 1->5->2->4->3
		// 1->2->3-> 5->4->null
		// 1->5->2->3
		while(sec.next != null){
			// save curr's next
			ListNode t1 = curr.next;
			// save sec's next
			ListNode t2 = sec.next;
			// change curr.next to sec
			curr.next = sec;
			// change sec.next to curr.next / t1
			sec.next = t1;
			// increment curr and sec
			curr = t1;
			sec = t2;
		}
		ListNode t = curr.next;
		curr.next = sec;
		sec.next = t;
		t.next = null;
    }
}