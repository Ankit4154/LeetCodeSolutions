// 1833. Maximum Ice Cream Bars
// https://leetcode.com/problems/maximum-ice-cream-bars
// optim
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // find max cost ice-cream bar
		int n = costs.length;
		int max = 0;
		for(int i=0;i<n;i++){
			max = Math.max(max, costs[i]);
		}
		int[] count = new int[max+1];
		// count and store frequencies
		for(int i=0;i<n;i++){
			count[costs[i]]++;
		}
		max = 0;
		for(int i=1;i<count.length;i++){
			int canBuy = Math.min(count[i], coins / i);
            max += canBuy;
            coins -= canBuy * i;
		}
		return max;
    }
}

// init
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // find max cost ice-cream bar
		int n = costs.length;
		int max = 0;
		for(int i=0;i<n;i++){
			max = Math.max(max, costs[i]);
		}
		int[] count = new int[max+1];
		// count and store frequencies
		for(int i=0;i<n;i++){
			count[costs[i]]++;
		}
		max = 0;
		for(int i=1;i<count.length;i++){
			if(i > coins)
				return max;
			else if(i <= coins){
				// reduce i from coins until or if either coins or count becomes 0;
				while(count[i] != 0){
					if(coins < i)
					    return max;
                    coins -= i;
					count[i]--;
					max++;
				}				
			}
		}
		return max;
    }   
}