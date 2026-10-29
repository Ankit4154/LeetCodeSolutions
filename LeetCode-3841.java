// 3841. Palindromic Path Queries in a Tree
// https://leetcode.com/problems/palindromic-path-queries-in-a-tree 
class Solution {
    int LOG;
    int[] parent, depth, tin, tout, prefix;
    int[][] up;
    int timer;
    List<List<Integer>> adj;
    Fenwick bit;
    char[] s;

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        // XOR update at one Euler Tour position
        void update(int i, int val) {
            for(i++; i <= n; i += i & -i)
                tree[i] ^= val;
        }

        // XOR of all updates up to this position
        int query(int i) {
            int res = 0;
            for(i++; i > 0; i -= i & -i)
                res ^= tree[i];
            return res;
        }

        // Range XOR update [l, r]
        void rangeUpdate(int l, int r, int val) {
            update(l, val);
            update(r + 1, val);
        }
    }

    int mask(char c) {
        return 1 << (c - 'a');
    }

    void dfs() {
        int n = s.length;
        int[] stack = new int[2 * n + 5];
        int top = 0;
        stack[top++] = 0;

        parent[0] = -1;

        while(top > 0) {
            int x = stack[--top];

            // Exit event
            if(x < 0) {
                x = ~x;
                tout[x] = timer - 1;
                continue;
            }

            // Enter event
            tin[x] = timer++;
            prefix[x] = mask(s[x]);

            if(parent[x] != -1) {
                prefix[x] ^= prefix[parent[x]];
                depth[x] = depth[parent[x]] + 1;
            }

            // Process exit after all children
            stack[top++] = ~x;

            for(int v : adj.get(x)) {
                if(v == parent[x])
                    continue;

                parent[v] = x;
                stack[top++] = v;
            }
        }
    }

    void buildLCA(int n) {
        LOG = 1;
        while((1 << LOG) <= n)
            LOG++;

        up = new int[LOG][n];

        for(int i = 0; i < n; i++)
            up[0][i] = parent[i] == -1 ? i : parent[i];

        for(int j = 1; j < LOG; j++) {
            for(int i = 0; i < n; i++)
                up[j][i] = up[j - 1][up[j - 1][i]];
        }
    }

    int lca(int u, int v) {
        if(depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for(int j = 0; j < LOG; j++) {
            if((diff & (1 << j)) != 0)
                u = up[j][u];
        }

        if(u == v)
            return u;

        for(int j = LOG - 1; j >= 0; j--) {
            if(up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    int getPrefix(int u) {
        return prefix[u] ^ bit.query(tin[u]);
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {

        this.s = s.toCharArray();

        adj = new ArrayList<>();
        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for(int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        prefix = new int[n];

        Arrays.fill(parent, -2);

        timer = 0;
        dfs();
        buildLCA(n);

        bit = new Fenwick(n);

        List<Boolean> result = new ArrayList<>();

        for(String q : queries) {
            String[] parts = q.split(" ");

            if(parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta = mask(this.s[u]) ^ mask(c);

                // Update the root-path masks of all descendants
                bit.rangeUpdate(tin[u], tout[u], delta);

                this.s[u] = c;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int pathMask = getPrefix(u) ^ getPrefix(v)
                             ^ mask(this.s[ancestor]);

                // At most one character may have odd frequency
                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return result;
    }
}