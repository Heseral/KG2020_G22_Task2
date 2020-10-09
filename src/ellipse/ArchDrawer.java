package ellipse;

import pixel_drawer.PixelDrawer;

import java.awt.*;

public class ArchDrawer {
    private PixelDrawer pixelDrawer;

    public ArchDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    private int getAngle(int centerX, int centerY, int pointX, int pointY) {
        int x = pointX - centerX;
        int y = pointY - centerY;

        if (x == 0)
            return (y > 0) ? 180 : 0;

        int a = (int) (Math.atan2(y, x) * 180 / Math.PI);
        a += 90;

        if ((x < 0) && (y < 0))
            a += 360;

        return a;
    }


    public void drawArch(int x0, int y0, int radius, int startAngle, int sweepAngle, Color color) {
        while (startAngle < 0)
            startAngle = 360 + startAngle;
        while (sweepAngle < 0)
            sweepAngle = 360 + sweepAngle;
        while (startAngle > 360)
            startAngle -= 360;
        while (sweepAngle > 360)
            sweepAngle -= 360;

        if (startAngle + sweepAngle > 360) {
            drawArch(x0, y0, radius, 0, startAngle + sweepAngle - 360, color);
            sweepAngle = 360 - startAngle;
        }

        int x = 0;
        int y = radius;
        int gap = 0;
        int delta = 2 * radius;
        int angle;
        while (y >= 0) {
            if (x0 + x >= 0 && y0 - y >= 0) {
                angle = getAngle(x0, y0, x0 + x, y0 - y);
                if ((angle >= startAngle) && (angle <= startAngle + sweepAngle)) {
                    if (0 <= angle && angle <= 90) {
                        pixelDrawer.colorPixel(x0 + x, y0 - y, color);
                    }
                }
            }

            if (x0 + x >= 0 && y0 + y >= 0) {
                angle = getAngle(x0, y0, x0 + x, y0 + y);
                if ((angle >= startAngle) && (angle <= startAngle + sweepAngle)) {
                    if (90 < angle && angle <= 180) {
                        pixelDrawer.colorPixel(x0 + x, y0 + y, color);
                    }
                }
            }

            if (x0 - x >= 0 && y0 + y >= 0) {
                angle = getAngle(x0, y0, x0 - x, y0 + y);
                if ((angle >= startAngle) && (angle <= startAngle + sweepAngle)) {
                    if (180 < angle && angle <= 270) {
                        pixelDrawer.colorPixel(x0 - x, y0 + y, color);
                    }
                }
            }

            if (x0 - x >= 0 && y0 - y >= 0) {
                angle = getAngle(x0, y0, x0 - x, y0 - y);
                if ((angle >= startAngle) && (angle <= startAngle + sweepAngle)) {
                    if (270 < angle && angle <= 360) {
                        pixelDrawer.colorPixel(x0 - x, y0 - y, color);
                    }
                }
            }

            gap = 2 * (delta + y) - 1;
            if (delta < 0 && gap <= 0) {
                delta += 2 * x + 1;
                x++;

                continue;
            }
            if (delta > 0 && gap > 0) {
                y--;
                delta -= 2 * y + 1;
                continue;
            }
            y--;
            delta += 2 * (x - y);
            x++;

        }
    }
}
