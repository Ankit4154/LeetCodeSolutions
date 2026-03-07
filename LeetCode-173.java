// 173. Binary Search Tree Iterator
// https://leetcode.com/problems/binary-search-tree-iterator
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
class BSTIterator {

    Deque<TreeNode> stack = new ArrayDeque<>();
    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }
    
    public int next(){
        TreeNode node = stack.pop();
        if(node.right != null){
            pushLeft(node.right);
        }
        return node.val;
    }
    
    public boolean hasNext() {
        if(!stack.isEmpty())
            return true;
        return false;
    }

    void pushLeft(TreeNode node){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */