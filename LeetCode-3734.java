// 3734. Lexicographically Smallest Palindromic Permutation Greater Than Target
// https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
		int[] alpha = new int[26];
		for(int i=0;i<n;i++){
			int pos = s.charAt(i) - 'a';
			alpha[pos]++;
		}

        // check if palindrome possible
		int odd = 0;
        for(int i=0;i<26;i++){
            if((alpha[i] % 2) != 0)
			    odd++;
		}

		if(odd > 1)
			return "";

		// special case for length 1
		if(n == 1){
			for(int i=0;i<26;i++){
				if(alpha[i] > 0 && (char)('a' + i) > target.charAt(0))
					return String.valueOf((char)('a' + i));
			}
			return "";
		}
		
		int half = n/2;

		int[] halfAlpha = new int[26];
		for(int i=0;i<26;i++){
			halfAlpha[i] = alpha[i] / 2;
		}
		
		StringBuilder best = null;
		
		// Try every position in the first half as the position
		// where our palindrome becomes greater than target.
		for(int bestPos=0; bestPos < half; bestPos++){
			int[] freq = halfAlpha.clone();
			StringBuilder prefix = new StringBuilder();
			
			// keep target prefix unchanged
			boolean possible = true;
			
			for(int i=0;i<bestPos;i++){
				char tc = target.charAt(i);
				int pos = tc-'a';
				
				// if can't match with the current target character, exit
				if(freq[pos] == 0){
					possible = false;
					break;
				}
				
				// keep the character and reduce frequency
				prefix.append(tc);
				freq[pos]--;
			}
			
			if(!possible)
				continue;
			
			// Find the smallest character greater than target[bestPos]
            int targetChar = target.charAt(bestPos) - 'a';
            int bestChar = -1;

            for(int j=targetChar+1;j<26;j++){
                if(freq[j] > 0){
                    bestChar = j;
                    break;
                }
            }

            if(bestChar == -1)
                continue;
			
			prefix.append((char)('a' + bestChar));
            freq[bestChar]--;

            // put all remaining first-half characters in sorted order
            for(int j=0;j<26;j++){
                while(freq[j] > 0){
                    prefix.append((char)('a' + j));
                    freq[j]--;
                }
            }
			
			// Build palindrome
            StringBuilder candidate = new StringBuilder();
            candidate.append(prefix);

            // add middle character
            if(n % 2 == 1){
                for(int j=0;j<26;j++){
                    if(alpha[j] % 2 != 0){
                        candidate.append((char)('a' + j));
                        break;
                    }
                }
            }

            // mirror first half
            for(int i=half-1;i>=0;i--){
                candidate.append(prefix.charAt(i));
            }

            // Candidate must be strictly greater than target
            if(candidate.toString().compareTo(target) > 0){
                if(best == null || candidate.toString().compareTo(best.toString()) < 0){
                    best = candidate;
                }
            }
        }

        // Check if we can exactly match the first half of target.
		// If yes, construct the palindrome and check whether
		// the mirrored half makes it greater than target.
		
        int[] freq = halfAlpha.clone();
        StringBuilder prefix = new StringBuilder();
        boolean possible = true;

        for(int i=0;i<half;i++){
            char tc = target.charAt(i);
            int pos = tc-'a';

            if(freq[pos] == 0){
                possible = false;
                break;
            }

            prefix.append(tc);
            freq[pos]--;
        }

        if(possible){
            StringBuilder candidate = new StringBuilder();
            candidate.append(prefix);

            if(n % 2 == 1){
                for(int j=0;j<26;j++){
                    if(alpha[j] % 2 != 0){
                        candidate.append((char)('a' + j));
                        break;
                    }
                }
            }

            for(int i=half-1;i>=0;i--){
                candidate.append(prefix.charAt(i));
            }

            if(candidate.toString().compareTo(target) > 0){
                if(best == null || candidate.toString().compareTo(best.toString()) < 0){
                    best = candidate;
                }
            }
        }
		

        return best == null ? "" : best.toString();
    }
}