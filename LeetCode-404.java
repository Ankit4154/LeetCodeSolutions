// 404. Sum of Left Leaves
// https://leetcode.com/problems/sum-of-left-leaves/
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
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null)
			return 0;
		int sum = 0;
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(root, false));
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0;i<size;i++){
				Pair p = q.poll();
				TreeNode n = p.node;
				if(n.left == null && n.right == null && p.isLeft)
					sum+= n.val;
				if(n.left != null)
					q.add(new Pair(n.left, true));
				if(n.right != null)
					q.add(new Pair(n.right, false));
			}
		}
		return sum;
    }
	private class Pair{
		TreeNode node;
		boolean isLeft;
		Pair(TreeNode node, boolean isLeft){
			this.node = node;
			this.isLeft = isLeft;
		
		}
	}
}
