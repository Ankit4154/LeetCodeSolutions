// 3727. Maximum Alternating Sum of Squares
// https://leetcode.com/problems/maximum-alternating-sum-of-squares
// optim
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int[] freq = new int[40001];
        long total = 0;

        // Count absolute values and calculate sum of squares
        for(int x : nums) {
            long sq = (long)x * x;
            total += sq;
            freq[Math.abs(x)]++;
        }

        // We need the smallest n/2 elements
        int need = n / 2;
        long min = 0;

        // Traverse values in ascending order
        for(int i = 0; i <= 40000 && need > 0; i++) {
            int take = Math.min(need, freq[i]);

            min += (long)take * i * i;
            need -= take;
        }

        return total - 2 * min;
    }
}

// naive
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
		int k = n/2;
		if(n % 2 != 0)
			k = n/2 + 1;
		PriorityQueue<Long> minHeap = new PriorityQueue<>(k);
		long min = 0;
		for(int x : nums){
			long sq = (long)x * x;
			minHeap.add(sq);
			if(minHeap.size() > k){
                min += minHeap.poll();
            }
		}
		long max = 0;
		while(!minHeap.isEmpty()){
			max += minHeap.poll();
		}
		return max - min;
		
    }
}

// init
class Solution {
    public long maxAlternatingSum(int[] nums) {
        nums = Arrays.stream(nums)
               .boxed()
               .sorted((a,b)->Integer.compare(Math.abs(b),Math.abs(a)))
               .mapToInt(i->i)
               .toArray();
               
        int n = nums.length;
        int left = 0, right = n-1;
        long sum = 0;
        while(left < right){
            long max = nums[left] * nums[left];
            long min = nums[right] * nums[right];
            sum += max - min;
        
        left++;
        right--;
        }
        if(left == right && n%2!=0){
            sum += nums[left] * nums[left];
        }
        return sum;
    }
}