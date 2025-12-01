// 92. Reverse Linked List II
// https://leetcode.com/problems/reverse-linked-list-ii/
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
    // Input: head = [1,2,3,4,5], left = 2, right = 4
	// Output: [1,4,3,2,5]
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 1: reach node before left
        ListNode prev = dummy;
        for(int i = 1; i < left; i++){
            prev = prev.next;
        }

        // Now prev is at left-1
        ListNode curr = prev.next; // curr is left

        // Step 2: reverse the sublist from left to right
        ListNode next = null;
        ListNode prevNode = null;

        for(int i = 0; i <= right - left; i++){
            next = curr.next;
            curr.next = prevNode;
            prevNode = curr;
            curr = next;
        }

        // Step 3: reconnect
        prev.next.next = curr;  // left becomes tail
        prev.next = prevNode;   // prevNode is new head of reversed part

        return dummy.next;
    }
}