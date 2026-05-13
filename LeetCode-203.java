// 203. Remove Linked List Elements
// https://leetcode.com/problems/remove-linked-list-elements/
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
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
        ListNode curr = head;
        ListNode prev = head;
        while(curr != null){
            if(curr.val == val){
                prev.next = curr.next;
                curr = curr.next;
                continue;
            }
            prev = curr;
            curr = curr.next;
        }
        if(head.val == val)
            return head.next;
        return head;
    }
}


class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
        ListNode curr = head;
        ListNode newList = new ListNode();
        ListNode head2 = newList;
        while(curr != null){
            if(curr.val == val){
                curr = curr.next;
                continue;
            }
            newList.next = new ListNode(curr.val);
            curr = curr.next;
            newList = newList.next;
        }
        return head2.next;
        
    }
}