// 2305. Fair Distribution of Cookies
// https://leetcode.com/problems/fair-distribution-of-cookies/
class Solution {
    int ans = Integer.MAX_VALUE;
    public int distributeCookies(int[] cookies, int k) {
        int[] children = new int[k];

        // Sort cookies descending for better pruning
        Arrays.sort(cookies);
        reverse(cookies);

        backtrack(0, cookies, children, k);
        return ans;
    }

    private void backtrack(int idx, int[] cookies, int[] children, int k) {
        if (idx == cookies.length) {
            int max = 0;
            for (int c : children) 
                max = Math.max(max, c);
            ans = Math.min(ans, max);
            return;
        }

        for(int i = 0; i < k; i++) {
            // Prune if already worse than best answer
            if(children[i] + cookies[idx] >= ans) 
                continue;

            // Avoid symmetric states
            if(i > 0 && children[i] == children[i - 1])
                continue;

            children[i] += cookies[idx];
            backtrack(idx + 1, cookies, children, k);
            children[i] -= cookies[idx];

            // If this child had 0 cookies before assignment,
            // no need to try other empty children
            if(children[i] == 0) 
                break;
        }
    }

    private void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
    }
}
