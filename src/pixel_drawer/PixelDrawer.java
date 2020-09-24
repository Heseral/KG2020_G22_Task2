package pixel_drawer;

import java.awt.*;

public interface PixelDrawer {
    default void colorPixel(int x, int y) {
        colorPixel(x, y, Color.BLACK);
    }

    void colorPixel(int x, int y, Color color);
}
