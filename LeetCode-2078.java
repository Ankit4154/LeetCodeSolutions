// 2078. Two Furthest Houses With Different Colors
// https://leetcode.com/problems/two-furthest-houses-with-different-colors
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int end = n-1;
        int max = 1;
        // scan from end for different number
        while(end > 0){
            if(colors[0] != colors[end]){
                max = Math.abs(end);
                break;
            }else{
                end--;
            }
        }
        // scan from start for different number
        int start = 0;
        while(start < n){
            if(colors[n-1] != colors[start]){
                max = Math.max(max, Math.abs((n-1) - start));
                break;
            }else{
                start++;
            }
        }
        return max;
    }
}