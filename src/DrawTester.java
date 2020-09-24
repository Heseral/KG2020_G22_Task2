import line.BresenhamLineDrawer;
import line.DDALineDrawer;
import line.LineDrawer;
import line.WuLineDrawer;
import pixel_drawer.GraphicsPixelDrawer;
import pixel_drawer.PixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawTester extends JPanel  {
    @Override
    public void paint(Graphics basicGraphics) {
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        PixelDrawer pixelDrawer = new GraphicsPixelDrawer(graphics);
        launchTests(pixelDrawer);
        basicGraphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
    }

    private void launchTests(PixelDrawer pixelDrawer) {
        int snowflakeLinesAmount = 32;
        int snowflakeRadius = 50;
        drawSnowflake(
                new DDALineDrawer(pixelDrawer),
                snowflakeRadius,
                snowflakeRadius,
                snowflakeRadius,
                snowflakeLinesAmount,
                Color.BLACK
        );
        drawSnowflake(
                new BresenhamLineDrawer(pixelDrawer),
                snowflakeRadius * 3,
                snowflakeRadius,
                snowflakeRadius,
                snowflakeLinesAmount,
                Color.RED
        );
        drawSnowflake(
                new WuLineDrawer(pixelDrawer),
                snowflakeRadius,
                snowflakeRadius * 3,
                snowflakeRadius,
                snowflakeLinesAmount,
                Color.BLUE
        );


    }

    private void drawSnowflake(LineDrawer lineDrawer, int centerX, int centerY, int radius, int amount, Color color) {
        double da = 2 * Math.PI / amount;

        for (int i = 0; i < amount; i++) {
            double a = da * i;
            double dx = radius * Math.cos(a);
            double dy = radius * Math.sin(a);
            lineDrawer.drawLine(centerX, centerY, centerX + (int) dx, centerY + (int) dy, color);
        }
    }
}
