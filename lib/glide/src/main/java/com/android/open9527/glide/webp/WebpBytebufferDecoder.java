package com.android.open9527.glide.webp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.load.resource.gif.GifBitmapProvider;
import com.facebook.animated.webp.WebPImage;
import com.facebook.soloader.SoLoader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/5/26
 **/
public class WebpBytebufferDecoder implements ResourceDecoder<ByteBuffer, WebpDrawable> {

    public final String TAG = "WebpBytebufferDecoder";
    private final List<ImageHeaderParser> mParsers;
    private final Context mContext;
    private final BitmapPool mBitmapPool;
    private final GifBitmapProvider mProvider;
    private final ArrayPool mByteArrayPool;


    public WebpBytebufferDecoder(Context context, Glide glide) {
        this(context, glide.getRegistry().getImageHeaderParsers(), glide.getArrayPool(),
                glide.getBitmapPool());
        // if not init Soloader, will get error when decode
        try {
            SoLoader.init(context, 0);
        } catch (IOException e) {
            Log.v(TAG, "Failed to init SoLoader", e);
        }
    }


    public WebpBytebufferDecoder(Context context, List<ImageHeaderParser> parsers, ArrayPool byteArrayPool, BitmapPool bitmapPool) {
        mContext = context.getApplicationContext();
        mParsers = parsers;
        mBitmapPool = bitmapPool;
        mProvider = new GifBitmapProvider(bitmapPool, byteArrayPool);
        mByteArrayPool = byteArrayPool;

    }

    private static int getSampleSize(int srcWidth, int srcHeight, int targetWidth, int targetHeight) {
        int exactSampleSize = Math.min(srcHeight / targetHeight,
                srcWidth / targetWidth);
        int powerOfTwoSampleSize = exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize);
        // Although functionally equivalent to 0 for BitmapFactory, 1 is a safer default for our code
        // than 0.
        int sampleSize = Math.max(1, powerOfTwoSampleSize);
        return sampleSize;
    }

    @Override
    public boolean handles(@NonNull ByteBuffer buffer, @NonNull Options options) throws IOException {
        ImageHeaderParser.ImageType type = ImageHeaderParserUtils.getType(mParsers, buffer);
        return type == ImageHeaderParser.ImageType.WEBP || type == ImageHeaderParser.ImageType.WEBP_A;
    }

    @Nullable
    @Override
    public Resource<WebpDrawable> decode(@NonNull ByteBuffer buffer, int width, int height, @NonNull Options options) {

        byte[] arr;
        if (buffer.hasArray())
            arr = buffer.array();
        else {
            arr = new byte[buffer.capacity()];
            buffer.get(arr);
        }


        WebPImage webp = WebPImage.createFromByteArray(arr);

        int sampleSize = getSampleSize(webp.getWidth(), webp.getHeight(), width, height);
        WebpDecoder webpDecoder = new WebpDecoder(mProvider, webp, sampleSize);
        Bitmap firstFrame = webpDecoder.getNextFrame();
        if (firstFrame == null) {
            return null;
        }

        Transformation<Bitmap> unitTransformation = UnitTransformation.get();

        return new WebpDrawableResource(new WebpDrawable(mContext, webpDecoder, mBitmapPool, unitTransformation, width, height,
                firstFrame));
    }

    public class WebpDrawableResource extends DrawableResource<WebpDrawable> implements Initializable {
        public WebpDrawableResource(WebpDrawable drawable) {
            super(drawable);
        }

        public Class<WebpDrawable> getResourceClass() {
            return WebpDrawable.class;
        }

        public int getSize() {
            return drawable.getSize();
        }

        public void recycle() {

        }

        public void initialize() {

        }
    }
}
//public class WebpBytebufferDecoder implements ResourceDecoder<ByteBuffer, WebpDrawable> {
//
//    public final String TAG = "WebpBytebufferDecoder";
//    private final List<ImageHeaderParser> mParsers;
//    private final Context mContext;
//    private final BitmapPool mBitmapPool;
//    private final GifBitmapProvider mProvider;
//    private final ArrayPool mByteArrayPool;
//
//
//    public WebpBytebufferDecoder(Context context, Glide glide) {
//        this(context, glide.getRegistry().getImageHeaderParsers(), glide.getArrayPool(),
//                glide.getBitmapPool());
//        // if not init Soloader, will get error when decode
//        try {
//            SoLoader.init(context, 0);
//        } catch (IOException e) {
//            Log.v(TAG, "Failed to init SoLoader", e);
//        }
//    }
//
//
//    public WebpBytebufferDecoder(Context context, List<ImageHeaderParser> parsers, ArrayPool byteArrayPool, BitmapPool bitmapPool) {
//        mContext = context.getApplicationContext();
//        mParsers = parsers;
//        mBitmapPool = bitmapPool;
//        mProvider = new GifBitmapProvider(bitmapPool, byteArrayPool);
//        mByteArrayPool = byteArrayPool;
//
//    }
//
//    private static int getSampleSize(int srcWidth, int srcHeight, int targetWidth, int targetHeight) {
//        int exactSampleSize = Math.min(srcHeight / targetHeight,
//                srcWidth / targetWidth);
//        int powerOfTwoSampleSize = exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize);
//        // Although functionally equivalent to 0 for BitmapFactory, 1 is a safer default for our code
//        // than 0.
//        int sampleSize = Math.max(1, powerOfTwoSampleSize);
//        return sampleSize;
//    }
//
//    @Override
//    public boolean handles(@NonNull ByteBuffer buffer, @NonNull Options options) throws IOException {
//        ImageHeaderParser.ImageType type = ImageHeaderParserUtils.getType(mParsers, buffer);
//        return type == ImageHeaderParser.ImageType.WEBP || type == ImageHeaderParser.ImageType.WEBP_A;
//    }
//
//    @Nullable
//    @Override
//    public Resource<WebpDrawable> decode(@NonNull ByteBuffer buffer, int width, int height, @NonNull Options options) {
//
//        byte[] arr;
//        if (buffer.hasArray())
//            arr = buffer.array();
//        else {
//            arr = new byte[buffer.capacity()];
//            buffer.get(arr);
//        }
//
//
//        WebPImage webp = WebPImage.create(arr);
//
//        int sampleSize = getSampleSize(webp.getWidth(), webp.getHeight(), width, height);
//        WebpDecoder webpDecoder = new WebpDecoder(mProvider, webp, sampleSize);
//        Bitmap firstFrame = webpDecoder.getNextFrame();
//        if (firstFrame == null) {
//            return null;
//        }
//
//        Transformation<Bitmap> unitTransformation = UnitTransformation.get();
//
//        return new WebpDrawableResource(new WebpDrawable(mContext, webpDecoder, mBitmapPool, unitTransformation, width, height,
//                firstFrame));
//    }
//
//    public class WebpDrawableResource extends DrawableResource<WebpDrawable> implements Initializable {
//        public WebpDrawableResource(WebpDrawable drawable) {
//            super(drawable);
//        }
//
//        public Class<WebpDrawable> getResourceClass() {
//            return WebpDrawable.class;
//        }
//
//        public int getSize() {
//            return drawable.getSize();
//        }
//
//        public void recycle() {
//
//        }
//
//        public void initialize() {
//
//        }
//    }
//}
