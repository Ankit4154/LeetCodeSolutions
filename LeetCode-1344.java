// 1344. Angle Between Hands of a Clock
// https://leetcode.com/problems/angle-between-hands-of-a-clock/
class Solution {
    public double angleClock(int hour, int minutes) {
        double hourPos = (hour % 12) * 5.0;

        // additional movement of hour hand due to minutes passed
        hourPos += ((double) minutes / 60.0) * 5.0;

        double diffMinutes = Math.abs(hourPos - minutes);

        double angle = diffMinutes * 6.0;

        return Math.min(angle, 360.0 - angle);
    }
}