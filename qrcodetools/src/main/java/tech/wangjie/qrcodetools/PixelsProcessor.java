package tech.wangjie.qrcodetools;

import android.graphics.Bitmap;

/**
 * @author Yoojia Chen
 * @since 2.0
 */
public interface PixelsProcessor {

    Bitmap create(int[] pixels, int width, int height);
}
