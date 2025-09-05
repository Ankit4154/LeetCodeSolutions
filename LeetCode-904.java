// 904. Fruit Into Baskets
// https://leetcode.com/problems/fruit-into-baskets
class Solution {
    public int totalFruit(int[] fruits) {
        int left = 0, right = 0, maxFruit = 0, k = 2;
        Map<Integer, Integer> map = new HashMap<>();
        while(right<fruits.length){
            int fruit = fruits[right];
            map.put(fruit, map.getOrDefault(fruit, 0)+1);
            if(map.size() > k){
                if(map.get(fruits[left]) > 1)
                    map.put(fruits[left], map.get(fruits[left])-1);
                else
                    map.remove(fruits[left]);
                left++;
            }else{
                maxFruit = Math.max(maxFruit, right-left+1);
            }
            right++;
        }
        return maxFruit;
    }
}