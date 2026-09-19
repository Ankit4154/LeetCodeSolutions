// 1401. Circle and Rectangle Overlapping
// https://leetcode.com/problems/circle-and-rectangle-overlapping
class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
		int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        int dx = xCenter - closestX;
        int dy = yCenter - closestY;

        return dx * dx + dy * dy <= radius * radius;
    }
}

// naive, errored
class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
		boolean xMid = (xCenter >= x1 && xCenter <= x2);
		int xBorderRight = xCenter + radius;
		boolean xRightBorder = (xBorderRight >= x1 && xBorderRight <= x2);
		int xBorderLeft = xCenter - radius;
		boolean xLeftBorder = (xBorderLeft >= x1 && xBorderLeft <= x2);
		
		boolean yMid = (yCenter >= y1 && yCenter <= y2);
		int yBorderTop = yCenter + radius;
		boolean yTopBorder = (yBorderTop >= y1 && yBorderTop <= y2);
		int yBorderBott = yCenter - radius;
		boolean yBottomBorder = (yBorderBott >= y1 && yBorderBott <= y2);
		// circle lies inside rectangle check
		boolean circleInRec = (xMid && yMid) || (xRightBorder && yTopBorder) || (xLeftBorder && yBottomBorder);
		
		boolean x1Check = (x1 >= xBorderLeft && x1 <= xBorderRight);
		boolean x2Check = (x2 >= xBorderLeft && x2 <= xBorderRight);
		boolean y1Check = (y1 >= yBorderBott && y1 <= yBorderTop);
		boolean y2Check = (y2 >= yBorderBott && y2 <= yBorderTop);
		// rectangle lies inside circle check
		boolean recInCircle = (x1Check || x2Check) && (y1Check || y2Check);
		
		return circleInRec || recInCircle;
    }
}