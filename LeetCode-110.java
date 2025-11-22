// 110. Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/
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
    boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null)
			return true;
		maxHeight(root);
		return ans;
    }
	
	public int maxHeight(TreeNode root){
		if(root == null)
			return 0;
		int leftHeight = maxHeight(root.left);
		int rightHeight = maxHeight(root.right);
        if(Math.abs(leftHeight-rightHeight) > 1)
			ans = false;
		return 1 + Math.max(leftHeight, rightHeight);
	}
}