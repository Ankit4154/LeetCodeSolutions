// 572. Subtree of Another Tree
// https://leetcode.com/problems/subtree-of-another-tree/
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
	boolean ans = false;
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if(root == null)
			return false;
		boolean res = isSameTree(root, subRoot);
		if(!res){
			isSubtree(root.left, subRoot);
			isSubtree(root.right, subRoot);
		}else{
			ans = true;
		}
		return ans;
    }
	
	public boolean isSameTree(TreeNode p, TreeNode q){
		if(p == null && q == null)
			return true;
		if(p == null || q == null)
			return false;
		if(p.val != q.val)
			return false;
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}
}
