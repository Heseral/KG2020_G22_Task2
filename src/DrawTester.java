import ellipse.ArcDrawer;
import ellipse.EllipseDrawer;
import line.BresenhamLineDrawer;
import line.DDALineDrawer;
import line.LineDrawer;
import line.WuLineDrawer;
import pixel_drawer.GraphicsPixelDrawer;
import task2.PixelDrawer;
import task2.graphics_impl.PFWDGI;
import task2.testing.TestArcs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawTester extends JPanel {
    private Point2D wuLineTestPosition = new Point(0, 0);

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
        /*try {
            TestArcs.startTest(
                    new PFWDGI(),
                    TestArcs.IMG_DIFF,
                    TestArcs.TEST_ARC | TestArcs.TEST_FILL,
                    true,
                    "."
            );
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        int snowflakeLinesAmount = 32;
        int defaultRadius = 50;
        drawSnowflake(
                new DDALineDrawer(pixelDrawer),
                defaultRadius,
                defaultRadius,
                defaultRadius,
                snowflakeLinesAmount,
                Color.BLACK
        );
        drawSnowflake(
                new BresenhamLineDrawer(pixelDrawer),
                defaultRadius * 3,
                defaultRadius,
                defaultRadius,
                snowflakeLinesAmount,
                Color.RED
        );
        WuLineDrawer wuLineDrawer = new WuLineDrawer(pixelDrawer);
        drawSnowflake(
                wuLineDrawer,
                defaultRadius,
                defaultRadius * 3,
                defaultRadius,
                snowflakeLinesAmount,
                Color.BLUE
        );

        ArcDrawer arcDrawer = new ArcDrawer(pixelDrawer);
        arcDrawer.drawArc(defaultRadius * 3, defaultRadius * 3, defaultRadius, defaultRadius, 20, 370, Color.ORANGE);
        arcDrawer.fillArc(defaultRadius * 3, defaultRadius * 3, defaultRadius, defaultRadius, 90, 160, Color.PINK);

        EllipseDrawer ellipseDrawer = new EllipseDrawer(pixelDrawer);
        ellipseDrawer.drawEllipse(defaultRadius * 5, defaultRadius, defaultRadius, Color.BLACK);
        ellipseDrawer.drawCircle(defaultRadius * 7, defaultRadius, defaultRadius, true, Color.MAGENTA);
        ellipseDrawer.drawEllipse(defaultRadius * 6, defaultRadius * 3, defaultRadius * 2, defaultRadius, Color.GREEN);
        ellipseDrawer.fillEllipse(defaultRadius * 9, defaultRadius, defaultRadius, defaultRadius, Color.DARK_GRAY);
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
