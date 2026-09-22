// 3525. Find X Value of Array II
// https://leetcode.com/problems/find-x-value-of-array-ii
// optim, Segment Tree, Build: O(n*k), Queries: O(q*k log n), Memory : O(n*k)
class Solution {
    class Node {
        int product;
        int[] count;

        Node(int k) {
            product = 1;
            count = new int[k];
        }
    }

    int k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] out = new int[queries.length];

        for(int i = 0; i < queries.length; i++){

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update persists for future queries
            nums[index] = value;

            update(1, 0, n - 1, index);

            Node result = query(1, 0, n - 1, start, n - 1);

            out[i] = result.count[x];
        }

        return out;
    }

    void build(int node, int left, int right) {

        if(left == right){

            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].product = rem;
            tree[node].count[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int left, int right, int index) {

        if(left == right){

            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].product = rem;
            tree[node].count[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if(index <= mid)
            update(node * 2, left, mid, index);
        else
            update(node * 2 + 1, mid + 1, right, index);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int left, int right, int ql, int qr) {

        if(ql <= left && right <= qr)
            return tree[node];

        int mid = left + (right - left) / 2;

        if(qr <= mid)
            return query(node * 2, left, mid, ql, qr);

        if(ql > mid)
            return query(node * 2 + 1, mid + 1, right, ql, qr);

        Node leftNode = query(node * 2, left, mid, ql, qr);
        Node rightNode = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(leftNode, rightNode);
    }

    Node merge(Node left, Node right) {

        Node result = new Node(k);

        result.product = (left.product * right.product) % k;

        // Prefixes completely inside left
        for(int r = 0; r < k; r++)
            result.count[r] += left.count[r];

        // Prefixes containing all of left + prefix of right
        for(int r = 0; r < k; r++){

            int newRemainder =
                (left.product * r) % k;

            result.count[newRemainder] += right.count[r];
        }

        return result;
    }
}

// naive, TLE
class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int[] out = new int[queries.length];
		for(int i=0;i<queries.length;i++){
			int ind = queries[i][0];
			int val = queries[i][1];
			int start = queries[i][2];
			int x = queries[i][3];
			// modify nums index to query value
			nums[ind] = val;
			int numsStart = ((start - 1) < 0) ? 0 : start - 1;
			// start reverse loop for suffix and product determination
			int n = nums.length;

			int product = 1;
			int count = 0;

			for(int j = start; j < nums.length; j++){

				product = (product * (nums[j] % k)) % k;

				if(product == x)
					count++;
			}
			out[i] = count;
		}
		return out;
    }
}