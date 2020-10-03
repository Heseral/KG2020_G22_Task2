import ellipse.ArchDrawer;
import ellipse.EllipseDrawer;
import line.BresenhamLineDrawer;
import line.DDALineDrawer;
import line.LineDrawer;
import line.WuLineDrawer;
import pixel_drawer.GraphicsPixelDrawer;
import pixel_drawer.PixelDrawer;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawTester extends JPanel implements MouseMotionListener {
    private Point2D wuLineTestPosition = new Point(0, 0);

    public DrawTester() {
        addMouseMotionListener(this);
    }

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

        ArchDrawer archDrawer = new ArchDrawer(pixelDrawer);
        archDrawer.drawArch(defaultRadius * 3, defaultRadius * 3, defaultRadius, -125, 175, Color.ORANGE);
        EllipseDrawer ellipseDrawer = new EllipseDrawer(pixelDrawer);
        ellipseDrawer.drawEllipse(defaultRadius * 5, defaultRadius, defaultRadius, Color.BLACK);
        ellipseDrawer.drawCircle(defaultRadius * 7, defaultRadius, defaultRadius, true, Color.MAGENTA);
        ellipseDrawer.drawEllipse(defaultRadius * 6, defaultRadius * 3, defaultRadius * 2, defaultRadius, Color.GREEN);

        wuLineDrawer.drawLine((int) (GlobalVar.SCREEN_WIDTH / 2), (int) (GlobalVar.SCREEN_HEIGHT / 2), (int) wuLineTestPosition.getX(), (int) wuLineTestPosition.getY(), Color.BLACK);

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

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        wuLineTestPosition = new Point(mouseEvent.getX(), mouseEvent.getY());
        repaint();
    }
}
