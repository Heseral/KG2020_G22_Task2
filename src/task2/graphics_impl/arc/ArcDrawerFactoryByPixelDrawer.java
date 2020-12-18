package task2.graphics_impl.arc;

import task2.ArcDrawer;
import task2.PixelDrawer;

public interface ArcDrawerFactoryByPixelDrawer {
    ArcDrawer createInstance(PixelDrawer pd);
}
