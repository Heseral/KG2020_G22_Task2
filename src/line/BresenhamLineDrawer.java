package line;

import task2.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pixelDrawer;

    public BresenhamLineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        drawLine(x1, y1, x2, y2, Color.BLACK);
    }

    /*
        алгоритм без использования чисел с плавающей точкой. Такой будет эффективнее, плавающая точка пригодится
        только в алгоритме Ву.
     */
    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        int delta = 0;

        int lengthX = Math.abs(x2 - x1);
        int lengthY = Math.abs(y2 - y1);

        int doubledLengthX = 2 * lengthX;
        int doubledLengthY = 2 * lengthY;

        int directionX = x1 < x2 ? 1 : -1;
        int directionY = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (lengthX >= lengthY) {
            while (true) {
                pixelDrawer.colorPixel(x, y, color);
                if (x == x2)
                    break;
                x += directionX;
                delta += doubledLengthY;
                if (delta > lengthX) {
                    y += directionY;
                    delta -= doubledLengthX;
                }
            }
            return;
        }

        while (true) {
            pixelDrawer.colorPixel(x, y, color);
            if (y == y2)
                break;
            y += directionY;
            delta += doubledLengthX;
            if (delta > lengthY) {
                x += directionX;
                delta -= doubledLengthY;
            }
        }

    }
}
