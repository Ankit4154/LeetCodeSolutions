// 118. Pascal's Triangle
// https://leetcode.com/problems/pascals-triangle
class Solution {

	List<List<Integer>> res = new ArrayList<>();
	int n = 0;
    public List<List<Integer>> generate(int numRows) {
        n = numRows;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		res.add(list);
		solve(2, list);
		return res;
    }
	void solve(int level, List<Integer> numList){
		if(level > n)
			return;
		List<Integer> list = new ArrayList<>();
		list.add(1);
        for(int i=1;i<numList.size();i++){
            int sum = numList.get(i-1) + numList.get(i);
            list.add(sum);
        }
		list.add(1);
		res.add(list);
		solve(level+1, list);
		
	}
}