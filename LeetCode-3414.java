// 3414. Maximum Score of Non-overlapping Intervals
// https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals
class Solution {

    class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    Result[][] dp;
    int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();

        arr = new Interval[n];

        // Keep the original index
        for(int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);

            arr[i] = new Interval(
                interval.get(0),
                interval.get(1),
                interval.get(2),
                i
            );
        }

        // Sort by start position
        Arrays.sort(arr, (a, b) -> {
            if(a.start != b.start)
                return Integer.compare(a.start, b.start);

            if(a.end != b.end)
                return Integer.compare(a.end, b.end);

            return Integer.compare(a.index, b.index);
        });

        dp = new Result[n + 1][5];

        Result ans = solve(0, 0);

        int[] result = new int[ans.indices.size()];

        for(int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    Result solve(int ind, int count) {

        // We have already selected 4 intervals
        if(count == 4 || ind == n) {
            return new Result(0, new ArrayList<>());
        }

        if(dp[ind][count] != null) {
            return dp[ind][count];
        }

        // Option 1: Skip current interval

        Result skip = solve(ind + 1, count);

        // Option 2: Take current interval

        int next = findNext(ind);

        Result takeNext = solve(next, count + 1);

        long takeScore = arr[ind].weight + takeNext.score;

        List<Integer> takeIndices = new ArrayList<>();

        takeIndices.add(arr[ind].index);
        takeIndices.addAll(takeNext.indices);

        // Keep original indices sorted
        Collections.sort(takeIndices);

        Result take = new Result(takeScore, takeIndices);

        // Choose the better result
        Result best;

        if(take.score > skip.score) {
            best = take;
        } else if(take.score < skip.score) {
            best = skip;
        } else {
            // Same score -> lexicographically smaller
            if(compare(take.indices, skip.indices) < 0)
                best = take;
            else
                best = skip;
        }

        dp[ind][count] = best;

        return best;
    }

    int findNext(int ind) {

        int target = arr[ind].end;

        int left = ind + 1;
        int right = n;

        while(left < right) {

            int mid = left + (right - left) / 2;

            // Need start > end
            if(arr[mid].start > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    int compare(List<Integer> a, List<Integer> b) {

        int size = Math.min(a.size(), b.size());

        for(int i = 0; i < size; i++) {

            if(!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        // If one is a prefix of another,
        // shorter one is lexicographically smaller.
        return Integer.compare(a.size(), b.size());
    }
}