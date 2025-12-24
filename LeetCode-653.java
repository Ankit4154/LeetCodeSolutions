// 653. Two Sum IV - Input is a BST
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst
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
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;
        Queue<TreeNode> q = new ArrayDeque<>();
		Set<Integer> set = new HashSet<>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode n = q.poll();
			if(set.contains(k-n.val))
				return true;
			set.add(n.val);
			if(n.left != null)
				q.add(n.left);
			if(n.right != null)
				q.add(n.right);
		}
		return false;
    }
}