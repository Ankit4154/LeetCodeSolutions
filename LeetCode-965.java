// 965. Univalued Binary Tree
// https://leetcode.com/problems/univalued-binary-tree
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
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root == null)
			return false;
		Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		int val = root.val;
		while(!q.isEmpty()){
			TreeNode n = q.poll();
			if(n.left != null){
				if(n.left.val != val)
					return false;
				q.add(n.left);
			}
			if(n.right != null){
				if(n.right.val != val)
					return false;
				q.add(n.right);
			}
			
		}
		return true;
    }
}