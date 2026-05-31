// 2126. Destroying Asteroids
// https://leetcode.com/problems/destroying-asteroids
class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long nMass = mass;
		for(int i=0;i<asteroids.length;i++){
			if(nMass < asteroids[i]){
				return false;
			}else{
				nMass += asteroids[i]; 
			}
		}
		return true;
    }
}