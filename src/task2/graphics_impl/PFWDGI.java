package task2.graphics_impl;

import ellipse.ArcDrawer;
import task2.graphics_impl.arc.ArcDrawerFactoryByPixelDrawer;
import task2.graphics_impl.pie.PieFillerFactoryByPixelDrawer;

public class PFWDGI extends PrimitivesFactoryWithDefaultGraphicsImplementation {
    @Override
    protected ArcDrawerFactoryByPixelDrawer getCustomArcDrawerFactory() {
        return ArcDrawer::new;
    }

    @Override
    protected PieFillerFactoryByPixelDrawer getCustomPieFillerFactory() {
        return ArcDrawer::new;
    }
}
