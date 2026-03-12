// 199. Binary Tree Right Side View
// https://leetcode.com/problems/binary-tree-right-side-view/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// dfs
class Solution {
	List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
		if(root == null)
			return res;
		dfs(root, 0);
		return res;
    }
	void dfs(TreeNode node, int depth){
		if(node == null)
			return;

        if(depth == res.size()) {
            res.add(node.val);
        }

        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
	}
}

// bfs
class Solution {
	List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
		if(root == null)
			return res;
		res.add(root.val);
		bfs(root);
		return res;
    }
	void bfs(TreeNode node){
		if(node == null)
			return;
		Queue<TreeNode> q = new ArrayDeque<>();
		q.add(node);
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=1;i<=size;i++){
				TreeNode n = q.poll();
				if(n.right != null){
					q.add(n.right);
				}
				if(n.left != null){
					q.add(n.left);
				}
			}
            if(!q.isEmpty())
			    res.add(q.peek().val);
		}
	}
}