// 1871. Jump Game VII
// https://leetcode.com/problems/jump-game-vii/
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] reachable = new boolean[n];
        reachable[0] = true;
        int count = 0;
        for(int i=1;i<n;i++){

            if(i - minJump >=0 && reachable[i-minJump]){
                count++;
            }

            if(i - maxJump - 1 >= 0 && reachable[i-maxJump-1]){
                count--;
            }
            if(count > 0 && s.charAt(i) == '0')
                reachable[i] = true;
        }
        return reachable[n-1];
    }
       
}

// TLE
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        return canReach(0, s, minJump, maxJump);
    }
    public boolean canReach(int ind, String s, int minJump, int maxJump) {
        if(ind == s.length()-1 && s.charAt(ind) == '0')
            return true;
        int minJ = ind + minJump;
        if(ind >= s.length() || minJ >= s.length())
            return false;
        
        int maxJ = Math.min(ind + maxJump, s.length()-1);
        for(int i=minJ;i<=maxJ;i++){
            if(s.charAt(i) == '0'){
                if(canReach(i, s, minJump, maxJump))
                    return true;
            }
        }
        return false;
    }
}