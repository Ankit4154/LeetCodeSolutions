// 3161. Block Placement Queries
// https://leetcode.com/problems/block-placement-queries
class Solution {

    class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int node, int l, int r, int idx, int val) {
            if(l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) / 2;

            if(idx <= mid) {
                update(node * 2, l, mid, idx, val);
            }else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int node, int l, int r, int ql, int qr) {
            if(ql > r || qr < l) {
                return 0;
            }

            if(ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = (l + r) / 2;

            return Math.max(
                query(node * 2, l, mid, ql, qr),
                query(node * 2 + 1, mid + 1, r, ql, qr)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {
		 int maxX = 0;

        for(int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(maxX + 1);

        // all obstacles after processing all queries
        for(int[] q : queries) {
            if(q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        List<Integer> coords = new ArrayList<>(obstacles);

        Map<Integer, Integer> index = new HashMap<>();
        for(int i = 0; i < coords.size(); i++) {
            index.put(coords.get(i), i);
        }

        SegmentTree seg = new SegmentTree(coords.size());

        Integer prev = null;
        for(int p : coords) {
            if(prev != null) {
                seg.update(1, 0, coords.size() - 1, index.get(p),p - prev);
            }
            prev = p;
        }

        List<Boolean> ans = new ArrayList<>();

        for(int i = queries.length - 1; i >= 0; i--) {

            int[] q = queries[i];

            if(q[0] == 2){

                int x = q[1];
                int sz = q[2];

                int pre = obstacles.floor(x);

                int idx = index.get(pre);

                int bestGap = seg.query(1,0,coords.size() - 1,0,idx);

                bestGap = Math.max(bestGap, x - pre);

                ans.add(bestGap >= sz);

            } else {

                int p = q[1];

                Integer left = obstacles.lower(p);
                Integer right = obstacles.higher(p);

                // remove p, merge intervals
                seg.update(1,0,coords.size() - 1,index.get(right),right - left);

                seg.update(1, 0, coords.size() - 1, index.get(p), 0);

                obstacles.remove(p);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}