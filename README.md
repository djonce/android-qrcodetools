# android-qrcodetools
生成二维码和二维码图片识别工具(nextqrcode 代码copy过来，移除了扫描的功能)

# NextQRCode ZXing开源库的精简版

基于ZXing Android实现的二维码扫描支持库。
包括`生成二维码图片`、`解析二维码图片`和`相机扫描即时解码`三部分功能。

# 与原ZXingMini项目对比

**NextQRCode做了重大架构修改，原ZXingMini项目与当前NextQRCode不兼容**

# 依赖

```groovy
dependencies {
    compile 'tech.wangjie:qrcodetools:1.0.0'
    ...
}
```

# 生成二维码图案

 ```java
 // 二维码中间图标
 final Bitmap centerImage = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
 // 生成的二维码图案
 Bitmap qrCodeImage = new QRCodeEncoder.Builder()
         .width(size) // 二维码图案的宽度
         .height(size)
         .paddingPx(0) // 二维码的内边距
         .marginPt(3) // 二维码的外边距
         .centerImage(centerImage) // 二维码中间图标
         .build()
         .encode(content);

 ```

# 二维码解码

```java
final QRCodeDecoder mDecoder = new QRCodeDecoder.Builder().build();
// 传入二维码图案Bitmap对象然后解码成文本内容
String content = mDecoder.decode(bitmap);
```
