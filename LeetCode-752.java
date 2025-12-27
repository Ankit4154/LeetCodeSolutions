// 752. Open the Lock
// https://leetcode.com/problems/open-the-lock
class Solution {
    public int openLock(String[] deadends, String target) {
        String start = "0000";
		if(target.equals(start))
			return 0;
		Set<String> dEnds = new HashSet<>(deadends.length);
		for(String x : deadends){
			dEnds.add(x);
		}
        if(dEnds.contains(start))
            return -1;
		Queue<String> q = new ArrayDeque<>();
		q.add(start);
		int[] comb = new int[]{-1,1};
        int count = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int s=0;s<size;s++){
				String currComb = q.poll();
				if(currComb.equals(target)){
					return count;
				}
				char[] currCombArr = currComb.toCharArray();
				for(int i=0;i<currCombArr.length;i++){
					char original = currCombArr[i];
					for(int r=0;r<2;r++){
						int c = Integer.parseInt(currCombArr[i]+"");
						if(c == 0 && r == 0)
							c = 9;
						else if(c == 9 && r == 1)
							c = 0;
						else {
							c = c + comb[r];
						}
						currCombArr[i] = (char)(c + '0');
						String newComb = new String(currCombArr);
						if(!dEnds.contains(newComb)){
                            dEnds.add(newComb);
							q.add(newComb);
						}
						currCombArr[i] = original;
					}
				}
			}
			count++;
		}
		
		return -1;
    }
}