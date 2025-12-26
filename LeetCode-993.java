// 993. Cousins in Binary Tree
// https://leetcode.com/problems/cousins-in-binary-tree
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
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		while(!q.isEmpty()){
			int size = q.size();
            TreeNode parentX = null;
		    TreeNode parentY = null;
			for(int i=0;i<size;i++){
				TreeNode n = q.poll();
				if(n.left != null){
					if(n.left.val == x)
						parentX = n;
					if(n.left.val == y)
						parentY = n;
					q.add(n.left);
				}
				if(n.right != null){
					if(n.right.val == x)
						parentX = n;
					if(n.right.val == y)
						parentY = n;
					q.add(n.right);
				}
			}
			if(parentX != null && parentY != null){
                return parentX != parentY;
            }
		}
		return false;
    }
}