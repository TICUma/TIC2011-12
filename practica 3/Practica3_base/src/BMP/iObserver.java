package BMP;

import java.awt.image.ImageObserver;
import java.awt.*;

public class iObserver implements ImageObserver {

    public boolean imageUpdate (Image img, int infoflags, 
                     int x, int y, int width, int height) {
        return true;
    }

}
