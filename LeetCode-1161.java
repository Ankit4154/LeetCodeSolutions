// 1161. Maximum Level Sum of a Binary Tree
// https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree
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
    public int maxLevelSum(TreeNode root) {
		int maxSum = Integer.MIN_VALUE;
        Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		int out = 0, count = 0;
		while(!q.isEmpty()){
			int size = q.size();
			count++;
			int sum = 0;
			for(int s=0;s<size;s++){
				TreeNode n = q.poll();
				sum += n.val;
				if(n.left != null){
					q.add(n.left);
				}
				if(n.right != null){
					q.add(n.right);
				}
			}
			if(sum > maxSum){
				out = count;
				maxSum = sum;
			}
		}
		return out;
    }
}