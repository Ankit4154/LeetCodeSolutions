// 997. Find the Town Judge
// https://leetcode.com/problems/find-the-town-judge/
class Solution {
    public int findJudge(int n, int[][] trust) {
        // calculate the inward and outward degree of each node
        // if for a node inward degree == n-1 && outward degree == 0
        // then return the node as answer else return -1
        // edge case 1 : n=4 [[2,3],[1,4],[2,4],[3,4]] --> output 4
        // edge case 2 : n=5 [[2,3],[1,4],[2,4],[5,4]] --> output -1
        // edge case 3 : n=6 [[2,3],[1,4],[5,4],[6,4]] --> output -1
        // edge case 4 : n=2 [[1,2],[2,1]] --> output -1
        // edge case 5 : n=1 [] --> output 1
        // edge case 6 : n=2 [] --> output -1
        if(trust.length == 0 && n == 1)
            return 1;
        if(trust.length == 0 && n > 1)
            return -1;
        int[] inDegree = new int[n+1];
        int[] outDegree = new int[n+1];
        for(int i=0;i<trust.length;i++){
            inDegree[trust[i][1]]++;
            outDegree[trust[i][0]]++;
        }
        for(int i=1;i<=n;i++){
            if(inDegree[i] == n-1 && outDegree[i] == 0)
                return i;
        }
        return -1;
    }
}