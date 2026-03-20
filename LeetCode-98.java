// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/
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
    public boolean isValidBST(TreeNode root) {
        if(root == null)
			return false;
		return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
	boolean dfs(TreeNode node, long min, long max){
        if(node == null)
            return true;
		if(node.val <= min || node.val >= max)
            return false;

        return dfs(node.left, min, node.val) &&
               dfs(node.right, node.val, max);
	}
}
