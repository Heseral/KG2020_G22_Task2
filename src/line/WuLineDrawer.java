package line;

import task2.PixelDrawer;

import java.awt.*;
import java.awt.image.ColorModel;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pixelDrawer;

    public WuLineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        drawLine(x1, y1, x2, y2, Color.BLACK);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        boolean isLineVerticallyOriented = Math.abs(y2 - y1) > Math.abs(x2 - x1);
        if (isLineVerticallyOriented) {
            int temp = y1;
            y1 = x1;
            x1 = temp;
            temp = y2;
            y2 = x2;
            x2 = temp;
        }
        if (x1 > x2) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
            temp = x2;
            x2 = x1;
            x1 = temp;
        }

        pixelDrawer.colorPixel(x1, y1, color);//, isLineVerticallyOriented);
        pixelDrawer.colorPixel(x2, y2, color);//, isLineVerticallyOriented);
        double dx = x2 - x1;
        double dy = y2 - y1;
        double slopeCoefficient = dy / dx;
        double y = y1 + slopeCoefficient;
        for (var x = x1 + 1; x <= x2 - 1; x++) {
            pixelDrawer.colorPixel(
                    x,
                    (int) y,
                    new Color(
                            color.getRed(),
                            color.getGreen(),
                            color.getBlue(),
                            (int) (255 * (1 - y + (int) y))
                    ),
                    isLineVerticallyOriented
            );
            pixelDrawer.colorPixel(
                    x,
                    (int) y + 1,
                    new Color(
                            color.getRed(),
                            color.getGreen(),
                            color.getBlue(),
                            (int) (255 * (y - (int) y))
                    ),
                    isLineVerticallyOriented
            );
            y += slopeCoefficient;
        }
    }
}
