package ellipse;

import pixel_drawer.PixelDrawer;

import java.awt.*;

public class ArcDrawer {
    private PixelDrawer pixelDrawer;

    public ArcDrawer(PixelDrawer pixelDrawer) {
        this.setPixelDrawer(pixelDrawer);
    }

    public void fillArc(int x0, int y0, int width, int height, int startAngle, int endAngle, Color color) {

    }

    public void drawArc(int x0, int y0, int width, int height, int startAngle, int endAngle, Color color) {
        while (startAngle < -360) {
            startAngle = 360 + startAngle;
        }
        while (endAngle < -360) {
            endAngle = 360 + endAngle;
        }
        while (startAngle > 360) {
            startAngle -= 360;
        }
        while (endAngle > 360) {
            endAngle -= 360;
        }
        int y = height;
        int x = 0;
        // НАЧАЛО: переменные для облегчения участи процессора. Просто сохраним их, чтобы не пересчитывать каждый раз
        final int bSquared = height * height;
        final int aSquared = width * width;
        final int doubleASquared = aSquared * 2;
        final int quadrupleASquared = aSquared * 4;
        final int quadrupleBSquared = bSquared * 4;
        final int doubleBSquared = bSquared * 2;
        // КОНЕЦ: переменные для облегчения участи процессора
        int delta = doubleASquared * ((y - 1) * y) + aSquared + doubleBSquared * (1 - aSquared);
        // горизонтально-ориентированные кривые
        while (aSquared * y > bSquared * x) {
            if (isValidAngle(startAngle, endAngle, x0, y0, x + x0, y + y0)) {
                getPixelDrawer().colorPixel(x + x0, y + y0, color);
            }
            if (isValidAngle(startAngle, endAngle, x0, y0, x + x0, y0 - y)) {
                getPixelDrawer().colorPixel(x + x0, y0 - y, color);
            }
            if (isValidAngle(startAngle, endAngle, x0, y0, x0 - x, y + y0)) {
                getPixelDrawer().colorPixel(x0 - x, y + y0, color);
            }
            if (isValidAngle(startAngle, endAngle, x0, y0, x0 - x, y0 - y)) {
                getPixelDrawer().colorPixel(x0 - x, y0 - y, color);
            }

            if (delta >= 0) {
                y--;
                delta -= quadrupleASquared * (y);
            }
            delta += doubleBSquared * (3 + x * 2);
            x++;
        }
        delta = doubleBSquared * (x + 1) * x + doubleASquared * (y * (y - 2) + 1) + (1 - doubleASquared) * bSquared;
        // вертикально-ориентированные кривые
        while (y + 1 > 0) {
            if (isValidAngle(startAngle, endAngle, x0, y0, x + x0, y + y0)) {
                getPixelDrawer().colorPixel(x + x0, y + y0, color);
            }
            if (isValidAngle(startAngle, endAngle, x0, y0, x + x0, y0 - y)) {
                getPixelDrawer().colorPixel(x + x0, y0 - y, color);
            }
            if (isValidAngle(startAngle, endAngle, x0, y0, x0 - x, y + y0)) {
                getPixelDrawer().colorPixel(x0 - x, y + y0, color);
            }
            if (isValidAngle(startAngle, endAngle, x0, y0, x0 - x, y0 - y)) {
                getPixelDrawer().colorPixel(x0 - x, y0 - y, color);
            }
            if (delta <= 0) {
                x++;
                delta += quadrupleBSquared * x;
            }
            y--;
            delta += doubleASquared * (3 - y * 2);
        }
    }

    private int getAngle(int centerX, int centerY, int pointX, int pointY) {
        int x = pointX - centerX;
        int y = pointY - centerY;

        if (x == 0) {
            return y > 0 ? 180 : 0;
        }

        int a = (int) (Math.atan2(x, y) * 180 / Math.PI) - 90;

        if ((x < 0) && (y < 0)) {
            a += 360;
        }

        return a;
    }

    private boolean isValidAngle(int startAngle, int endAngle, int centerX, int centerY, int pointX, int pointY) {
        int angle = getAngle(centerX, centerY, pointX, pointY);
        return angle >= startAngle && angle <= endAngle;
    }

    public PixelDrawer getPixelDrawer() {
        return pixelDrawer;
    }

    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }
}
