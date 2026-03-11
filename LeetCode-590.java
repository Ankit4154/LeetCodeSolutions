// 590. N-ary Tree Postorder Traversal
// https://leetcode.com/problems/n-ary-tree-postorder-traversal/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
*/

class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null)
            return res;
        dfs(root);
        res.add(root.val);
        return res;
    }
    void dfs(Node node){
        if(node == null)
            return;
        if(node.children == null){
            res.add(node.val);
            return;
        }
        for(Node n : node.children){
            dfs(n);
            res.add(n.val);
        }
    }
}