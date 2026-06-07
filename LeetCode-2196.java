// 2196. Create Binary Tree From Descriptions
// https://leetcode.com/problems/create-binary-tree-from-descriptions/
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
    public TreeNode createBinaryTree(int[][] descriptions) {
        int n = descriptions.length;
        Map<Integer,TreeNode> map = new HashMap<>();
		Set<Integer> childSet = new HashSet<>();
		for(int i=0;i<n;i++){
			int parent = descriptions[i][0];
			int child = descriptions[i][1];
			int pos = descriptions[i][2];
			TreeNode parentNode = null;
			TreeNode childNode = null;
			// if child doesn't exists
			if(!map.containsKey(child)){
				childNode = new TreeNode(child, null, null);
				map.put(child, childNode);
			}else{
				childNode = map.get(child);
			}
			childSet.add(child);
			// if parent doesn't exists
			if(!map.containsKey(parent)){
				parentNode = new TreeNode(parent);
				// put parent in map
				map.put(parent, parentNode);
			}else{ // if parent is already created 
				parentNode = map.get(parent);
			}
			if(pos == 0)
				parentNode.right = childNode;
			else
				parentNode.left = childNode;
		}
		TreeNode root = null;
		for(int i=0;i<n;i++){
			int parent = descriptions[i][0];
			if(!childSet.contains(parent)){
				root = map.get(parent);
				break;
			}
		}
		return root;
    }
}