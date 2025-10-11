// Biweekly 167 - Q1. Equal Score Substrings
// https://leetcode.com/contest/biweekly-contest-167/problems/equal-score-substrings/description/
class Solution {
    public boolean scoreBalance(String s) {
        // initialize left sum score and right sum score
        // if left score < right score 
        // then left++ and check if left score == right score
        // if left score > right score
        // then right++ and check if left score == right score
        // left <= right
        // if we find left score == right score 
        // then check if we have parsed the entire string or not
		// if we have parsed all characters of string then return true ( case : cba, adcb)
		// else if still some characters of string are remaining to be parsed
		// increment left, decrement right and continue with the loop (case : aaaa)
        // else if loop ends and none found (case : jhj, bace) return false
		
        int left = 0, right = s.length()-1, sumLeft = 0, sumRight = 0;
        char l = s.charAt(left);
        char r = s.charAt(right);
        int valLeft = (l - 'a') + 1;
        int valRight = (r - 'a') + 1;
        sumLeft += valLeft;
        sumRight += valRight;
        while(left < right){
            if(sumLeft == sumRight){
                if(left + 1 + (s.length()-right) == s.length()){
                    return true;
                }else{
                    left++;
                    l = s.charAt(left);
                    valLeft = (l - 'a') + 1;
                    sumLeft += valLeft;
                    right--;
                    r = s.charAt(right);
                    valRight = (r - 'a') + 1;
                    sumRight += valRight;
                }
            }else if(sumLeft < sumRight){
                left++;
                l = s.charAt(left);
                valLeft = (l - 'a') + 1;
                sumLeft += valLeft;
            }else{
                right--;
                r = s.charAt(right);
                valRight = (r - 'a') + 1;
                sumRight += valRight;
            }
        }
        return false;
    }
}