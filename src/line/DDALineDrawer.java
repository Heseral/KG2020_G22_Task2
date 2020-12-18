package line;

import task2.PixelDrawer;

import java.awt.*;

public class DDALineDrawer implements LineDrawer {
    private PixelDrawer pixelDrawer;

    public DDALineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        drawLine(x1, y1, x2, y2, Color.BLACK);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        double slopeCoefficient = (double) (y2 - y1) / (x2 - x1);

        if (Math.abs(slopeCoefficient) > 1) {
            slopeCoefficient = 1 / slopeCoefficient;
            if (y1 > y2) {
                int temp = y2;
                y2 = y1;
                y1 = temp;
                temp = x2;
                x2 = x1;
                x1 = temp;
            }

            for (int y = y1; y <= y2; y++) {
                // round() обязательно нужен, так как приведение дабла к инту просто выбрасывает дробную часть, а не
                // округляет ее до ближайшего числа
                int x = (int) Math.round((y - y1) * slopeCoefficient + x1);
                pixelDrawer.colorPixel(x, y, color);
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

        for (int x = x1; x <= x2; x++) {
            // round() обязательно нужен, так как приведение дабла к инту просто выбрасывает дробную часть, а не
            // округляет ее до ближайшего числа
            int y = (int) Math.round((x - x1) * slopeCoefficient + y1);
            pixelDrawer.colorPixel(x, y, color);
        }
    }
}
