package task2.graphics_impl.line;

import task2.LineDrawer;
import task2.PixelDrawer;

import java.awt.*;

public interface LineDrawerFactoryByPixelDrawer {
    LineDrawer createInstance(PixelDrawer pd);
}
