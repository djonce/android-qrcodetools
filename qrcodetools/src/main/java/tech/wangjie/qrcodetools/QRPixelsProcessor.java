package tech.wangjie.qrcodetools;

import android.graphics.Bitmap;

/**
 * @author Yoojia Chen
 * @since 2.0
 */
public class QRPixelsProcessor implements PixelsProcessor {

    @Override
    public Bitmap create(int[] pixels, int width, int height) {
        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
