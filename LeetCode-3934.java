// 3934. Smallest Unique Subarray
// https://leetcode.com/problems/smallest-unique-subarray
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = buildSA(nums);
        int[] lcp = buildLCP(nums, sa);

        int ans = n;

        for(int i=0;i<n;i++){
            int maxLCP = 0;

            if(i > 0)
                maxLCP = Math.max(maxLCP, lcp[i]);

            if(i < n-1)
                maxLCP = Math.max(maxLCP, lcp[i+1]);

            // Remaining length of this suffix
            int len = n - sa[i];

            // A unique prefix must fit inside the suffix
            if(maxLCP < len)
                ans = Math.min(ans, maxLCP + 1);
        }

        return ans;
    }

    int[] buildSA(int[] nums) {
        int n = nums.length;
        int m = n + 1;

        // Add sentinel (0), all original values are shifted by 1
        int[] a = new int[m];

        for(int i=0;i<n;i++)
            a[i+1] = nums[i] + 1;

        int[] p = new int[m];
        int[] c = new int[m];
        int[] cnt = new int[Math.max(m, 100002)];

        // Initial sorting by individual values
        for(int x : a)
            cnt[x]++;

        for(int i=1;i<cnt.length;i++)
            cnt[i] += cnt[i-1];

        for(int i=m-1;i>=0;i--)
            p[--cnt[a[i]]] = i;

        c[p[0]] = 0;
        int classes = 1;

        for(int i=1;i<m;i++){
            if(a[p[i]] != a[p[i-1]])
                classes++;
            c[p[i]] = classes - 1;
        }

        // Sort cyclic shifts using doubling
        for(int len=1;len<m;len*=2){
            int[] pn = new int[m];

            for(int i=0;i<m;i++){
                pn[i] = p[i] - len;
                if(pn[i] < 0)
                    pn[i] += m;
            }

            Arrays.fill(cnt, 0);

            for(int x : pn)
                cnt[c[x]]++;

            for(int i=1;i<classes;i++)
                cnt[i] += cnt[i-1];

            for(int i=m-1;i>=0;i--){
                int x = pn[i];
                p[--cnt[c[x]]] = x;
            }

            int[] cn = new int[m];
            cn[p[0]] = 0;

            int newClasses = 1;

            for(int i=1;i<m;i++){
                int cur = p[i];
                int prev = p[i-1];

                int mid = (cur + len) % m;
                int midPrev = (prev + len) % m;

                if(c[cur] != c[prev] ||
                   c[mid] != c[midPrev])
                    newClasses++;

                cn[cur] = newClasses - 1;
            }

            c = cn;
            classes = newClasses;
        }

        // Remove sentinel
        int[] sa = new int[n];

        for(int i=0;i<n;i++)
            sa[i] = p[i+1] - 1;

        return sa;
    }

    int[] buildLCP(int[] nums, int[] sa) {
        int n = nums.length;

        int[] rank = new int[n];
        int[] lcp = new int[n];

        for(int i=0;i<n;i++)
            rank[sa[i]] = i;

        int k = 0;

        for(int i=0;i<n;i++){
            int r = rank[i];

            if(r == 0) {
                k = 0;
                continue;
            }

            int j = sa[r-1];

            while(i+k<n && j+k<n &&
                  nums[i+k] == nums[j+k])
                k++;

            lcp[r] = k;

            if(k > 0)
                k--;
        }

        return lcp;
    }
}