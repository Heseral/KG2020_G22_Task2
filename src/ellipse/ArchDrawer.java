package ellipse;

import pixel_drawer.PixelDrawer;

import java.awt.*;

public class ArchDrawer {
    private PixelDrawer pixelDrawer;

    public void drawArch(int x0, int y0, int width, int height, double coverageFactor, Color color) {
        //todo
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
        while (aSquared * y > bSquared * x) {
            pixelDrawer.colorPixel(x + x0, y + y0, color);
            pixelDrawer.colorPixel(x + x0, y0 - y, color);
            pixelDrawer.colorPixel(x0 - x, y + y0, color);
            pixelDrawer.colorPixel(x0 - x, y0 - y, color);
            if (delta >= 0) {
                y--;
                delta -= quadrupleASquared * (y);
            }
            delta += doubleBSquared * (3 + (x << 1));
            x++;
        }
        delta = doubleBSquared * (x + 1) * x + doubleASquared * (y * (y - 2) + 1) + (1 - doubleASquared) * bSquared;
        while (y + 1 > 0) {
            pixelDrawer.colorPixel(x + x0, y + y0, color);
            pixelDrawer.colorPixel(x + x0, y0 - y, color);
            pixelDrawer.colorPixel(x0 - x, y + y0, color);
            pixelDrawer.colorPixel(x0 - x, y0 - y, color);
            if (delta <= 0) {
                x++;
                delta += quadrupleBSquared * x;
            }
            y--;
            delta += doubleASquared * (3 - (y << 1));
        }
    }
}
