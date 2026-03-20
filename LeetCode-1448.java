// 1448. Count Good Nodes in Binary Tree
// https://leetcode.com/problems/count-good-nodes-in-binary-tree/
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
	int count = 1;
    public int goodNodes(TreeNode root) {
		if(root == null)
			return 0;
        dfs(root, root.val);
		return count;
    }
	void dfs(TreeNode node, int max){
		if(node == null)
			return;
		if(node.right != null){
			if(node.right.val >= max){
				count++;
				dfs(node.right, node.right.val);
			}else{
				dfs(node.right, max);
			}
		}
		
		if(node.left != null){
			if(node.left.val >= max){
				count++;
				dfs(node.left, node.left.val);
			}else{
				dfs(node.left, max);
			}
		}
	}
}