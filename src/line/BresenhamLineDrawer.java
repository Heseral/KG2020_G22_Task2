package line;

import pixel_drawer.PixelDrawer;

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
        // длина линии по оси х
        int lengthX = Math.abs(x2 - x1) + 1;
        // длина линии по оси у
        int lengthY = Math.abs(y2 - y1) + 1;
        int stepCounter = 0;
        // "направление" линии. То есть точки во входных данных могут быть заданы так, что линия будет чертиться
        // сверху вниз(-1), а не снизу вверх(1)
        int direction = 0;
        // может наша линия вертикально-ориентированная?
        if (lengthY > lengthX) {
            if (y1 > y2) {
                int temp = y2;
                y2 = y1;
                y1 = temp;
                temp = x2;
                x2 = x1;
                x1 = temp;
            }
            if (x2 > x1) {
                direction = 1;
            } else if (x2 < x1) {
                direction = -1;
            }
            for (int y = y1, x = x1; y < y2; y++) {
                pixelDrawer.colorPixel(x, y, color);
                stepCounter += lengthX;
                if (stepCounter >= lengthY) {
                    x += direction;
                    stepCounter -= lengthY;
                }
            }

            return;
        }
        if (x1 > x2) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
            temp = x2;
            x2 = x1;
            x1 = temp;
        }
        if (y2 > y1) {
            direction = 1;
        } else if (y2 < y1) {
            direction = -1;
        }
        for (int x = x1, y = y1; x < x2; x++) {
            pixelDrawer.colorPixel(x, y, color);
            stepCounter += lengthY;
            if (stepCounter >= lengthX) {
                y += direction;
                stepCounter -= lengthX;
            }
        }
    }
}
