package tech.wangjie.qrcodetools;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * @author Yoojia Chen
 * @since 2.0
 */
class PaddingProcessor implements BitmapProcessor {

    private final int mPadding;
    private final int mBackgroundColor;

    public PaddingProcessor(int paddingPx, int backgroundColor) {
        this.mPadding = paddingPx;
        this.mBackgroundColor = backgroundColor;
    }

    @Override
    public Bitmap process(Bitmap src) {
        final int newWidth = src.getWidth() + mPadding * 2;
        final int newHeight = src.getHeight() + mPadding * 2;
        final Bitmap output = Bitmap.createBitmap(newWidth, newHeight, src.getConfig());
        Canvas canvas = new Canvas(output);
        canvas.drawColor(mBackgroundColor);
        canvas.drawBitmap(src, mPadding, mPadding, null);
        return output;
    }
}
