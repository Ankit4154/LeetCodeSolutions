// 575. Distribute Candies
// https://leetcode.com/problems/distribute-candies/
class Solution {
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for(int candie : candyType){
            set.add(candie);
        }
        int n = candyType.length / 2;
        if(n > set.size())
          return set.size();
        return n;
    }
}
