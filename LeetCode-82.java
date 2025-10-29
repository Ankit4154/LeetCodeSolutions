// 82. Remove Duplicates from Sorted List II
// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head); // dummy before head
        ListNode prev = dummy; // last node before duplicate sequence

        while (head != null) {
            // Check if this is a start of duplicates
            if (head.next != null && head.val == head.next.val) {
                // Skip all nodes with this value
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Remove duplicates completely
                prev.next = head.next;
            } else {
                prev = prev.next; // no duplicate, move prev
            }
            head = head.next; // move forward
        }
        return dummy.next;
    }
}