package tech.wangjie.qrcodetoolssample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tech.wangjie.qrcodetools.QRCodeDecoder;
import tech.wangjie.qrcodetools.QRCodeEncoder;

public class MainActivity extends Activity {
    Bitmap qrcodeBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 生成二维码按钮
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrcode = ((EditText)findViewById(R.id.editText)).getText().toString().trim();
                final int size = findViewById(R.id.imageView).getWidth();
                Observable.just(qrcode)
                        .map(new Func1<String, Bitmap>() {
                            @Override
                            public Bitmap call(String content) {
                                final Bitmap centerImage = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                                return new QRCodeEncoder.Builder()
                                        .width(size)
                                        .height(size)
                                        .paddingPx(0)
                                        .marginPt(3)
                                        .centerImage(centerImage)
                                        .build()
                                        .encode(content);

                            }
                        })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Bitmap>() {
                            @Override
                            public void call(Bitmap bitmap) {
                                qrcodeBitmap = bitmap;
                                ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bitmap);
                            }
                        });

            }
        });


        // 长按图片二维码识别
        findViewById(R.id.imageView).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (qrcodeBitmap != null) {

                    Observable.just(qrcodeBitmap)
                            .map(new Func1<Bitmap, String>() {
                                @Override
                                public String call(Bitmap bitmap) {
                                    return new QRCodeDecoder.Builder().build().decode(bitmap);
                                }
                            })
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                                }
                            });

                }

                return false;
            }
        });


    }
}
