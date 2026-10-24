// 2409. Count Days Spent Together
// https://leetcode.com/problems/count-days-spent-together/
class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceStart = getDay(arriveAlice);
        int aliceEnd = getDay(leaveAlice);
        int bobStart = getDay(arriveBob);
        int bobEnd = getDay(leaveBob);

        // Find the overlapping date range
        int start = Math.max(aliceStart, bobStart);
        int end = Math.min(aliceEnd, bobEnd);

        // No overlap
        if (start > end) {
            return 0;
        }

        // +1 because both dates are inclusive
        return end - start + 1;
    }
    private int getDay(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30,
                              31, 31, 30, 31, 30, 31};

        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        // Add all days from the preceding months
        for (int i = 0; i < month - 1; i++) {
            day += daysInMonths[i];
        }

        return day;
    }
}