package ellipse;

import task2.PieFiller;
import task2.PixelDrawer;

import java.awt.*;

public class ArcDrawer implements task2.ArcDrawer, PieFiller {
    private PixelDrawer pixelDrawer;

    public ArcDrawer(PixelDrawer pixelDrawer) {
        this.setPixelDrawer(pixelDrawer);
    }

    public void fillArc(int x0, int y0, int width, int height, int startAngle, int sweepAngle, Color color) {
        if (sweepAngle == 0) {
            return;
        }
        while (startAngle < -180) {
            startAngle += 360;
        }
        while (startAngle > 180) {
            startAngle -= 360;
        }
        if (startAngle + sweepAngle > 180) {
            if (sweepAngle >= 360) {
                startAngle = -180;
                sweepAngle = 360;
            } else {
                fillArc(x0, y0, width, height, -180, sweepAngle - 180 + startAngle, color);
                sweepAngle = 180 - startAngle;
            }
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
        final int xFinalLeft;
        final int xFinalRight;
        // горизонтально-ориентированные кривые
        while (aSquared * y > bSquared * x) {
            for (int yToDraw = y0 - y; yToDraw <= y0 + y; yToDraw++) {
                if (isValidAngle(startAngle, sweepAngle, x0, y0, x0 + x, yToDraw)) {
                    pixelDrawer.colorPixel(x0 + x, yToDraw, color);
                }
                if (isValidAngle(startAngle, sweepAngle, x0, y0, x0 - x, yToDraw)) {
                    pixelDrawer.colorPixel(x0 - x, yToDraw, color);
                }
            }

            if (delta >= 0) {
                y--;
                delta -= quadrupleASquared * (y);
            }
            delta += doubleBSquared * (3 + x * 2);
            x++;
        }
        xFinalLeft = x0 - x + 1;
        xFinalRight = x0 + x - 1;
        delta = doubleBSquared * (x + 1) * x + doubleASquared * (y * (y - 2) + 1) + (1 - doubleASquared) * bSquared;
        // вертикально-ориентированные кривые
        while (y + 1 > 0) {
            for (int xToDraw = x0 - x; xToDraw < xFinalLeft; xToDraw++) {
                if (isValidAngle(startAngle, sweepAngle, x0, y0, xToDraw, y0 + y)) {
                    pixelDrawer.colorPixel(xToDraw, y0 + y, color);
                }
                if (isValidAngle(startAngle, sweepAngle, x0, y0, xToDraw, y0 - y)) {
                    pixelDrawer.colorPixel(xToDraw, y0 - y, color);
                }
            }
            for (int xToDraw = x0 + x; xToDraw > xFinalRight; xToDraw--) {
                if (isValidAngle(startAngle, sweepAngle, x0, y0, xToDraw, y0 + y)) {
                    pixelDrawer.colorPixel(xToDraw, y0 + y, color);
                }
                if (isValidAngle(startAngle, sweepAngle, x0, y0, xToDraw, y0 - y)) {
                    pixelDrawer.colorPixel(xToDraw, y0 - y, color);
                }
            }

            if (delta <= 0) {
                x++;
                delta += quadrupleBSquared * x;
            }
            y--;
            delta += doubleASquared * (3 - y * 2);
        }
    }

    public void drawArc(int x0, int y0, int width, int height, int startAngle, int sweepAngle, Color color) {
        if (sweepAngle == 0) {
            return;
        }
        while (startAngle < -180) {
            startAngle += 360;
        }
        while (startAngle > 180) {
            startAngle -= 360;
        }
        if (startAngle + sweepAngle > 180) {
            if (sweepAngle >= 360) {
                startAngle = -180;
                sweepAngle = 360;
            } else {
                drawArc(x0, y0, width, height, -180, sweepAngle - 180 + startAngle, color);
                sweepAngle = 180 - startAngle;
            }
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
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x + x0, y + y0)) {
                getPixelDrawer().colorPixel(x + x0, y + y0, color);
            }
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x + x0, y0 - y)) {
                getPixelDrawer().colorPixel(x + x0, y0 - y, color);
            }
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x0 - x, y + y0)) {
                getPixelDrawer().colorPixel(x0 - x, y + y0, color);
            }
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x0 - x, y0 - y)) {
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
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x + x0, y + y0)) {
                getPixelDrawer().colorPixel(x + x0, y + y0, color);
            }
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x + x0, y0 - y)) {
                getPixelDrawer().colorPixel(x + x0, y0 - y, color);
            }
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x0 - x, y + y0)) {
                getPixelDrawer().colorPixel(x0 - x, y + y0, color);
            }
            if (isValidAngle(startAngle, sweepAngle, x0, y0, x0 - x, y0 - y)) {
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

    private boolean isValidAngle(int startAngle, int sweepAngle, int centerX, int centerY, int pointX, int pointY) {
        int angle = getAngle(centerX, centerY, pointX, pointY);
        return angle >= startAngle && angle <= startAngle + sweepAngle;
    }

    public PixelDrawer getPixelDrawer() {
        return pixelDrawer;
    }

    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawArc(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        height /= 2;
        width /= 2;
        startAngle *= 180 / Math.PI;
        arcAngle *= 180 / Math.PI;
        drawArc(x + width, y + height, width, height, (int) startAngle, (int) arcAngle, c);
    }

    @Override
    public void fillPie(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        height /= 2;
        width /= 2;
        startAngle *= 180 / Math.PI;
        arcAngle *= 180 / Math.PI;
        fillArc(x + width, y + height, width, height, (int) startAngle, (int) arcAngle, c);
    }
}
