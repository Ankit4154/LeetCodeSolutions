// 513. Find Bottom Left Tree Value
// https://leetcode.com/problems/find-bottom-left-tree-value
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
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		int out = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				TreeNode n = q.poll();
				if(i==0){
					out = n.val;
				}
				if(n.left != null){
					q.add(n.left);
				}
				if(n.right != null){
					q.add(n.right);
				}
			}
		}
		return out;
    }
}