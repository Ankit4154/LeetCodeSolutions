// 2213. Longest Substring of One Repeating Character
// https://leetcode.com/problems/longest-substring-of-one-repeating-character/
class Solution {
    class Node {
        char leftChar;
        char rightChar;

        int length;
        int prefix;
        int suffix;
        int max;

        Node(char leftChar, char rightChar,
             int length, int prefix, int suffix, int max) {

            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.length = length;
            this.prefix = prefix;
            this.suffix = suffix;
            this.max = max;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();
        int k = queryIndices.length;

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].max;
        }

        return ans;
    }

    void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(
                arr[left],
                arr[left],
                1,
                1,
                1,
                1
            );
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int left, int right, int index, char ch) {

        if (left == right) {
            tree[node] = new Node(
                ch,
                ch,
                1,
                1,
                1,
                1
            );
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {

        Node res = new Node(
            a.leftChar,
            b.rightChar,
            a.length + b.length,
            a.prefix,
            b.suffix,
            Math.max(a.max, b.max)
        );

        if (a.rightChar == b.leftChar) {

            res.max = Math.max(
                res.max,
                a.suffix + b.prefix
            );

            if (a.prefix == a.length) {
                res.prefix = a.length + b.prefix;
            }

            if (b.suffix == b.length) {
                res.suffix = b.length + a.suffix;
            }
        }

        return res;
    }
}