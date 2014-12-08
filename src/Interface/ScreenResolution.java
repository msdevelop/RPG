package Interface;

import java.awt.*;

public interface ScreenResolution
{
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) dim.getWidth();
    int screenHeight = (int) dim.getHeight();
}
