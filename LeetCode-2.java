// 2. Add Two Numbers
// https://leetcode.com/problems/add-two-numbers/description/
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // loop over both the lists simultaneously
		// add the nodes and form sum node for sumList
		// during sum, if there is a carry over, add carry to the next node4
		// l1 = [2,4,3], l2 = [5,6,4], output = [7,0,8]
		// Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
		// Output: [8,9,9,9,0,0,0,1]
		ListNode dummy = new ListNode(-1);
		ListNode sum = dummy;
		int carry = 0, s = 0, num = 0;
		while(l1 != null && l2 != null){
			s = l1.val + l2.val + carry;
			carry = s / 10;
			num = s % 10;
			ListNode sumNode = new ListNode(num); // create new node for sum list
			sum.next = sumNode; // assign new node to sum list's next
			sum = sum.next;  // move the head to next node of sum list
			l1 = l1.next;
			l2 = l2.next;
		}
		// if lists are not of same length, iterate over the remaining one
		while(l1 != null){
			s = l1.val + carry;
			carry = s / 10;
			num = s % 10;
			ListNode sumNode = new ListNode(num);
			sum.next = sumNode;
			sum = sum.next;
			l1 = l1.next;
		}

		while(l2 != null){
			s = l2.val + carry;
			carry = s / 10;
			num = s % 10;
			ListNode sumNode = new ListNode(num);
			sum.next = sumNode;
			sum = sum.next;
			l2 = l2.next;
		}
		// edge case when last number is not a single digit / has a carry over
		if(carry != 0){
			ListNode sumNode = new ListNode(carry);
			sum.next = sumNode;
		}
		return dummy.next;
    }
}