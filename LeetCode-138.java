// 138. Copy List with Random Pointer
// https://leetcode.com/problems/copy-list-with-random-pointer/
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        Node tmp = head;
		Map<Node, Node> map = new HashMap<>();
		// create copy nodes and store in map with respective original nodes
		while(tmp != null){
			Node t = new Node(tmp.val);
			map.put(tmp, t);
			tmp = tmp.next;
		}
		// map.forEach((k, v) -> System.out.println(k.val + " -> " + v.val));
		// assign next and random pointers in the copied list
		tmp = head;
        Node dummy = map.get(tmp);
        Node c = dummy;
		while(tmp != null){
			c = map.get(tmp);
			c.next = map.get(tmp.next);
			c.random = map.get(tmp.random);
			tmp = tmp.next;
		}
		return dummy;
    }
}
