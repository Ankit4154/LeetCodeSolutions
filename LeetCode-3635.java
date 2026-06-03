// 3635. Earliest Finish Time for Land and Water Rides II
// https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-ii
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        long ans = Long.MAX_VALUE;

        // Land -> Water
        ans = Math.min(ans,
                solve(landStartTime, landDuration,
                      waterStartTime, waterDuration));

        // Water -> Land
        ans = Math.min(ans,
                solve(waterStartTime, waterDuration,
                      landStartTime, landDuration));

        return (int) ans;
    }

    int solve(int[] firstStart, int[] firstDuration,
              int[] secondStart, int[] secondDuration) {

        int m = secondStart.length;

        int[][] rides = new int[m][2];

        for(int i = 0; i < m; i++) {
            rides[i][0] = secondStart[i];
            rides[i][1] = secondDuration[i];
        }

        Arrays.sort(rides, (a, b) -> a[0] - b[0]);

        int[] starts = new int[m];
        int[] prefixMinDuration = new int[m];
        int[] suffixMinEndTime = new int[m];

        for(int i = 0; i < m; i++) {
            starts[i] = rides[i][0];

            if(i == 0) {
                prefixMinDuration[i] = rides[i][1];
            } else {
                prefixMinDuration[i] =
                    Math.min(prefixMinDuration[i - 1], rides[i][1]);
            }
        }

        for(int i = m - 1; i >= 0; i--) {

            int currEndTime = rides[i][0] + rides[i][1];

            if(i == m - 1) {
                suffixMinEndTime[i] = currEndTime;
            } else {
                suffixMinEndTime[i] =
                    Math.min(suffixMinEndTime[i + 1], currEndTime);
            }
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < firstStart.length; i++) {

            int firstEndTime = firstStart[i] + firstDuration[i];

            int pos = upperBound(starts, firstEndTime) - 1;

            int currAns = Integer.MAX_VALUE;

            if(pos >= 0) {
                currAns = Math.min(
                    currAns,
                    firstEndTime + prefixMinDuration[pos]
                );
            }

            if(pos + 1 < m) {
                currAns = Math.min(
                    currAns,
                    suffixMinEndTime[pos + 1]
                );
            }

            ans = Math.min(ans, currAns);
        }

        return ans;
    }

    int upperBound(int[] arr, int target) {

        int low = 0;
        int high = arr.length;

        while(low < high) {

            int mid = low + (high - low) / 2;

            if(arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}