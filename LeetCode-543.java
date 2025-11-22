// 543. Diameter of Binary Tree
// https://leetcode.com/problems/diameter-of-binary-tree/
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
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
			return 0;
        maxHeight(root);
		return this.res;
    }
	public int maxHeight(TreeNode root){
		if(root == null)
			return 0;
		int leftHeight = maxHeight(root.left);
		int rightHeight = maxHeight(root.right);
        this.res = Math.max(this.res, leftHeight + rightHeight);
		return 1 + Math.max(leftHeight, rightHeight);
	}
}