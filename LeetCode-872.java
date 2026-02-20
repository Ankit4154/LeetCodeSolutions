// 872. Leaf-Similar Trees
// https://leetcode.com/problems/leaf-similar-trees
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
	
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> leaves1 = new ArrayList<>();
		List<Integer> leaves2 = new ArrayList<>();
        dfs(root1, leaves1);
		dfs(root2, leaves2);
		if(leaves1.size()!=leaves2.size())
			return false;
		for(int i=0;i<leaves2.size();i++){
            //if(leaves1.get(i)!=leaves2.get(i)) fails because
            // java caches Integer objects from -128 to 127
			if(!leaves1.get(i).equals(leaves2.get(i)))
				return false;
		}
		return true;
    }
	void dfs(TreeNode root, List<Integer> leaves){
		if(root == null)
			return;
		// leaf node
		if(root.left == null && root.right == null){
			leaves.add(root.val);
			return;
		}
		if(root.left != null){
			dfs(root.left, leaves);
		}
		if(root.right != null){
			dfs(root.right, leaves);
		}
	}
}