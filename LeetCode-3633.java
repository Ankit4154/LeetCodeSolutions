// 3633. Earliest Finish Time for Land and Water Rides I
// https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-i/
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;

        for(int i=0;i<landStartTime.length;i++){

            int landEndTime = landStartTime[i] + landDuration[i];

            for(int j=0;j<waterStartTime.length;j++) {

                int waterEndTime = waterStartTime[j] + waterDuration[j];

                // Land -> Water
                int landFirst =
                        Math.max(landEndTime, waterStartTime[j])
                        + waterDuration[j];

                // Water -> Land
                int waterFirst =
                        Math.max(waterEndTime, landStartTime[i])
                        + landDuration[i];

                ans = Math.min(ans, Math.min(landFirst, waterFirst));
            }
        }

        return ans;
		
    }
}