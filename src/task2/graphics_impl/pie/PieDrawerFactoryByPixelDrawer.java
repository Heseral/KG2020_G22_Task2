package task2.graphics_impl.pie;

import task2.PieDrawer;
import task2.PixelDrawer;

public interface PieDrawerFactoryByPixelDrawer {
    PieDrawer createInstance(PixelDrawer pd);
}
