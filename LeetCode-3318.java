// 3318. Find X-Sum of All K-Long Subarrays I
// https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i
class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Initialize first window
        for (int i = 0; i < k; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        ans[0] = calcXSum(freq, nums, 0, k, x);
        
        // Slide window
        for (int i = k; i < n; i++) {
            int out = nums[i - k];
            int in = nums[i];
            
            // Remove outgoing
            freq.put(out, freq.get(out) - 1);
            if (freq.get(out) == 0) freq.remove(out);
            
            // Add incoming
            freq.put(in, freq.getOrDefault(in, 0) + 1);
            
            ans[i - k + 1] = calcXSum(freq, nums, i - k + 1, i + 1, x);
        }
        
        return ans;
    }
    private int calcXSum(Map<Integer, Integer> freq, int[] nums, int start, int end, int x) {
        // Build a max heap by frequency desc, value desc
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (b[1] == a[1]) return b[0] - a[0];
            return b[1] - a[1];
        });
        
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            pq.offer(new int[]{e.getKey(), e.getValue()});
        }
        
        Set<Integer> keep = new HashSet<>();
        for (int i = 0; i < x && !pq.isEmpty(); i++) {
            keep.add(pq.poll()[0]);
        }
        
        int sum = 0;
        for (int i = start; i < end; i++) {
            if (keep.contains(nums[i])) sum += nums[i];
        }

        return sum;
    }
}