// 116. Populating Next Right Pointers in Each Node
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
// optimized
class Solution {
    public Node connect(Node root) {
        if(root == null)
			return null;
		Queue<Node> q = new ArrayDeque<>();
		q.add(root);
		while(!q.isEmpty()){
			int size = q.size();
            Node prev = null;
			for(int i=0;i<size;i++){
				Node n = q.poll();
                if(prev != null)
                    prev.next = n;
                prev = n;
                if(n.left != null){
                    q.add(n.left);
                }
				if(n.right != null){
                    q.add(n.right);
                }
			}
		}
		return root;
    }
}
// non-optmized
class Solution {
    public Node connect(Node root) {
        if(root == null)
			return null;
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(root, null));
		while(!q.isEmpty()){
			int size = q.size();
			List<Node> list = new ArrayList<>();
			for(int i=0;i<size;i++){
				Pair p = q.poll();
				Node n = p.n;
				n.next = p.next;
				if(n.right != null){
                    Node k = null;
                    if(!list.isEmpty()){
                        k = list.get(list.size()-1);
                        list.remove(k);
                    }
                    q.add(new Pair(n.right, k));
                }                    
                if(n.left != null){
                    q.add(new Pair(n.left, n.right));
				    list.add(n.left);
                }
			}
		}
		return root;
    }
	private class Pair{
		Node n;
		Node next;
		Pair(Node n, Node next){
			this.n = n;
			this.next = next;
		}
	}
}