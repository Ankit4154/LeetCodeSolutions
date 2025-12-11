// 103. Binary Tree Zigzag Level Order Traversal
// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> out = new ArrayList<>();
        if(root == null)
            return out;
		Queue<TreeNode> q = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
		q.add(root);
        boolean leftToRight = true;
		while(!q.isEmpty()){
			list = new ArrayList<>();
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode n = q.poll();
                list.add(n.val);
                if(n.left != null)
				    q.add(n.left);
                if(n.right != null)
				    q.add(n.right);
            }
            if(!leftToRight)
                Collections.reverse(list);
            
            out.add(list);
            leftToRight = !leftToRight;
        }
		return out;
	}
}