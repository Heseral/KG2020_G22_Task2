package pixel_drawer;

import java.awt.*;

public class GraphicsPixelDrawer implements PixelDrawer {
    Graphics graphics;
    public GraphicsPixelDrawer(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void colorPixel(int x, int y, Color color) {
        Color graphicsColor = graphics.getColor();
        graphics.setColor(color);
        graphics.fillRect(x, y, 1, 1);
        graphics.setColor(graphicsColor);
    }
}
