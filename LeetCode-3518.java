// 3518. Smallest Palindromic Rearrangement II
// https://leetcode.com/problems/smallest-palindromic-rearrangement-ii
class Solution {
    long LIMIT = 1000001;

    public String smallestPalindrome(String s, int k) {
        int[] alpha = new int[26];
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			alpha[c - 'a']++;
		}

        int[] cnt = new int[26];
        int half = 0;
        StringBuilder mid = new StringBuilder();

		for(int i=0;i<26;i++){
            cnt[i] = alpha[i]/2;
            half += cnt[i];

            if(alpha[i]%2!=0)
               mid.append((char)(i+'a'));
        }

        if(count(cnt) < k)
            return "";

        StringBuilder left = new StringBuilder();

        for(int pos=0;pos<half;pos++){

            for(int i=0;i<26;i++){

                if(cnt[i]==0)
                    continue;

                cnt[i]--;

                long ways = count(cnt);

                if(ways >= k){
                    left.append((char)(i+'a'));
                    break;
                }

                k -= ways;
                cnt[i]++;
            }
        }

        StringBuilder out = new StringBuilder();
        out.append(left);
        out.append(mid);
        out.append(left.reverse());

		return out.toString();
    }

    private long count(int[] cnt){

        int total = 0;
        for(int x : cnt)
            total += x;

        long ans = 1;

        for(int i=0;i<26;i++){

            if(cnt[i]==0)
                continue;

            ans = multiplyCombination(ans, total, cnt[i]);

            if(ans >= LIMIT)
                return LIMIT;

            total -= cnt[i];
        }

        return ans;
    }


    private long multiplyCombination(long ans, int n, int r){

        r = Math.min(r, n-r);

        for(int i=1;i<=r;i++){

            ans = ans * (n-r+i) / i;

            if(ans >= LIMIT)
                return LIMIT;
        }

        return ans;
    }
}