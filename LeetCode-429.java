// 429. N-ary Tree Level Order Traversal
// https://leetcode.com/problems/n-ary-tree-level-order-traversal/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
	List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
		if(root == null)
			return res;
		List<Integer> list = new ArrayList<>();
		list.add(root.val);
		res.add(list);
        bfs(root);
		return res;
    }
	void bfs(Node node){
		Queue<Node> q = new ArrayDeque<>();
		q.add(node);
		while(!q.isEmpty()){
			int size = q.size();
            List<Integer> list = new ArrayList<>();
			for(int i=1;i<=size;i++){
				Node n = q.poll();
				for(Node child : n.children){
					q.add(child);
					list.add(child.val);
				}
			}
            if(!list.isEmpty())
            res.add(list);
		}
	}
}