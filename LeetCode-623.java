// 623. Add One Row to Tree
// https://leetcode.com/problems/add-one-row-to-tree/
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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
		if(depth == 1){
			TreeNode rNode = new TreeNode(val);
			rNode.left = root;
			return rNode;
		}
        Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		int level = 1;
		while(!q.isEmpty()){
			int size = q.size();
			if(level == depth-1){
				for(int s=0;s<size;s++){
					TreeNode n = q.poll();
					TreeNode rNodeLeft = new TreeNode(val);
					TreeNode rNodeRight = new TreeNode(val);
					rNodeLeft.left = n.left;
					n.left = rNodeLeft;
					rNodeRight.right = n.right;
					n.right = rNodeRight;
				}
				break;
			}
			for(int s=0;s<size;s++){
				TreeNode n = q.poll();
				if(n.left != null)
					q.add(n.left);
				if(n.right != null)
					q.add(n.right);
			}
			level++;
		}
		return root;
    }
}