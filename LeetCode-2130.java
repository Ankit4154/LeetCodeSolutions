// 2130. Maximum Twin Sum of a Linked List
// https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list
// optim
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
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Find middle
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        ListNode prev = null;
        ListNode curr = slow;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Compute max twin sum
        int max = 0;
        ListNode first = head;
        ListNode second = prev;

        while(second != null){
            max = Math.max(max, first.val + second.val);
            first = first.next;
            second = second.next;
        }

        return max;
    }
}

// init
class Solution {
    public int pairSum(ListNode head) {
        List<Integer> list = new ArrayList<>();
		ListNode mid = head;
		ListNode temp = head;
		int n = 0;
		int count = 0;
		int max = -1;
		while(temp != null){
			n++;
			temp = temp.next;
		}
		while(count != n/2){
			list.add(mid.val);
			mid = mid.next;
			count++;
		}
		while(mid != null){
			int v = list.get(list.size()-1);
			list.remove(list.size()-1);
            max = Math.max(max, mid.val+v);
			mid = mid.next;
		}
		return max;
    }
}