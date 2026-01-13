// 3453. Separate Squares I
// https://leetcode.com/problems/separate-squares-i
class Solution {
    public double separateSquares(int[][] squares){
        double totalArea = 0;
        double low = Double.MAX_VALUE, high = Double.MIN_VALUE;

        for(int[] s : squares){
            double y = s[1], l = s[2];
            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double target = totalArea / 2.0;

        while(high - low > 1e-6){
            double mid = (low + high) / 2;
            double above = areaAbove(squares, mid);

            if(above > target){
                low = mid;      // need to move line up
            }else{
                high = mid;
            }
        }
        return low;
    }
    private double areaAbove(int[][] squares, double line){
        double area = 0;
        for(int[] s : squares){
            double y = s[1], l = s[2];
            double top = y + l;

            if(line <= y){
                area += l * l;
            }else if(line < top){
                area += l * (top - line);
            }
        }
        return area;
    }
}