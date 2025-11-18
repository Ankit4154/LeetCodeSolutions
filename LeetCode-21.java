// 21. Merge Two Sorted Lists
// https://leetcode.com/problems/merge-two-sorted-lists/
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode merged = new ListNode();
		ListNode ans = merged;
		ListNode second = null;
		ListNode first = null;
        while(list1 != null && list2 != null){
			first = new ListNode(list1.val);
			second = new ListNode(list2.val);
			if(first.val == second.val){
				merged.next = first;
				merged.next.next = second;
				merged = merged.next;
				list1 = list1.next;
				list2 = list2.next;
			}else if(first.val > second.val){
				merged.next = second;
				list2 = list2.next;
			}else{
				merged.next = first;
				list1 = list1.next;
			}
			merged = merged.next;
		}
		while(list1 != null){
			first = new ListNode(list1.val);
			merged.next = first;
			list1 = list1.next;
			merged = merged.next;
		}
		while(list2 != null){
			second = new ListNode(list2.val);
			merged.next = second;
			list2 = list2.next;
			merged = merged.next;
		}
		return ans.next;
    }
}