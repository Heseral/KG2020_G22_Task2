package task2.graphics_impl.pie;

import task2.PieFiller;
import task2.PixelDrawer;

public interface PieFillerFactoryByPixelDrawer {
    PieFiller createInstance(PixelDrawer pd);
}
