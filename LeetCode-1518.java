// 1518. Water Bottles
// https://leetcode.com/problems/water-bottles/
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles;
        if(numBottles == numExchange)
            return sum+1;
        int exchange = numBottles / numExchange;
        sum += exchange;
        if(exchange == 0)
            return sum;
        int rem = numBottles % numExchange;
        numBottles = exchange + rem;
        if(numBottles < numExchange)
            return sum;
        while(numBottles > numExchange){
            exchange = numBottles / numExchange;
            sum += exchange;
            rem = numBottles % numExchange;
            numBottles = exchange + rem;
        }
        if(numBottles == numExchange)
            return sum+1;
        return sum;

    }
}