// 515. Find Largest Value in Each Tree Row
// https://leetcode.com/problems/find-largest-value-in-each-tree-row
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if(root == null)
            return out;
        Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		int max = Integer.MIN_VALUE;
		while(!q.isEmpty()){
			int size = q.size();
			max = Integer.MIN_VALUE;
			for(int i=0;i<size;i++){
				TreeNode n = q.poll();
				max = Math.max(max,n.val);
				if(n.left != null){
					q.add(n.left);
				}
				if(n.right != null){
					q.add(n.right);
				}
			}
			out.add(max);
		}
		return out;
    }
}