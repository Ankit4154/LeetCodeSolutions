// 119. Pascal's Triangle II
// https://leetcode.com/problems/pascals-triangle-ii
class Solution {
    List<Integer> res = new ArrayList<>();
	int n = 0;
    public List<Integer> getRow(int rowIndex) {
        n = rowIndex;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		solve(0, list);
		return res;
    }
    void solve(int level, List<Integer> numList){
		if(level == n){
            res.addAll(numList);
            return;
        }
		List<Integer> list = new ArrayList<>();
		list.add(1);
        for(int i=1;i<numList.size();i++){
            int sum = numList.get(i-1) + numList.get(i);
            list.add(sum);
        }
		list.add(1);
		solve(level+1, list);
	}
}