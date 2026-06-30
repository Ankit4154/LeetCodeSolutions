// 1358. Number of Substrings Containing All Three Characters
// https://leetcode.com/problems/number-of-substrings-containing-all-three-characters
// optim, last seen position
class Solution {
    public int numberOfSubstrings(String s) {
		int n = s.length();
		int[] last = {-1, -1, -1};
        int count = 0;

        for(int i=0;i<n;i++) {
            last[s.charAt(i) - 'a'] = i;
            count += Math.min(last[0], Math.min(last[1], last[2])) + 1;
        }

        return count;
    }
}

//optim, variables reduced
class Solution {
    public int numberOfSubstrings(String s) {
		int n = s.length();
		int[] freq = new int[3];
		int count = 0, left = 0, right = 0;
		while(right < n){
			freq[s.charAt(right) - 'a']++;
			while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0){
				count += n - right; // store subarrays count till end of string
				freq[s.charAt(left) - 'a']--;
				left++;
			}
			right++;
		}
		
		return count;
    }
}

// optim, array instead of HashMap
class Solution {
    public int numberOfSubstrings(String s) {
		int n = s.length();
		int[] freq = new int[3];
		int count = 0, left = 0, right = 0;
		while(right < n){
			char c = s.charAt(right);
			freq[c - 'a']++;
			while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0 && left < n){
				count++;
				count += n - right - 1; // store subarrays count till end of string
				c = s.charAt(left);
				freq[c - 'a']--;
				left++;
			}
			right++;
		}
		
		return count;
    }
}


// init
class Solution {
    public int numberOfSubstrings(String s) {
		int n = s.length();
		Map<Character, Integer> map = new HashMap<>();
		int count = 0, left = 0, right = 0;
		while(right < n){
			char c = s.charAt(right);
			map.put(c, map.getOrDefault(c, 0)+1);
			while(map.size()==3 && left < n){
				count++;
				count += n - right - 1; // store subarrays count till end of string
				c = s.charAt(left);
				int val = map.get(c);
				val--;
				if(val == 0)
					map.remove(c);
				else
					map.put(c, val);
				left++;
			}
			right++;
		}
		
		return count;
    }
}