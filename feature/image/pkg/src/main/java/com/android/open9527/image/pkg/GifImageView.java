package com.android.open9527.image.pkg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.android.open9527.common.action.HandlerAction;
import com.android.open9527.common.action.LifecycleHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * @author open_9527
 * Create at 2021/5/25
 **/
public class GifImageView extends AppCompatImageView {

    private static final int DEFAULT_DURATION = 1000;
    private float mScaleW = 1.0f;
    private float mScaleH = 1.0f;
    private float mScale = 1.0f;
    private Movie movie;
    //播放开始时间点
    private long mMovieStart;
    //播放暂停时间点
    private long mMoviePauseTime;
    //播放暂停时间
    private long offsetTime;
    //播放完成进度
    @FloatRange(from = 0, to = 1.0f)
    float percent;
    //播放次数，-1为循环播放
    private int counts = -1;

    private volatile boolean reverse = false;
    private volatile boolean mPaused;
    private volatile boolean hasStart;

    private boolean mVisible = true;

    private OnPlayListener mOnPlayListener;
    private int movieDuration;
    private boolean endLastFrame = false;
    private Handler mHandler;

    public GifImageView(Context context) {
        this(context, null);
    }

    public GifImageView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public GifImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 9527) {
                    setGif();
                } else if (msg.what == 9528) {
                    setBitmapInputStream((InputStream) msg.obj);
                } else if (msg.what == 9529) {
                    setBitmap((Bitmap) msg.obj);
                }
            }
        };


    }


    public void setGifResource(int movieResourceId, OnPlayListener onPlayListener) {
        if (onPlayListener != null) {
            mOnPlayListener = onPlayListener;
        }
        reset();
        movie = Movie.decodeStream(getResources().openRawResource(movieResourceId));
        if (movie == null) {
            //如果movie为空，那么就不是gif文件，尝试转换为bitmap显示
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), movieResourceId);
            if (bitmap != null) {
                setImageBitmap(bitmap);
                return;
            }
        }
        movieDuration = movie.duration() == 0 ? DEFAULT_DURATION : movie.duration();
        requestLayout();
    }

    public void setGifInputStream(InputStream inputStream) {
        reset();
        new Thread(() -> {
            movie = Movie.decodeStream(inputStream);
            if (movie == null) {
                Message message = new Message();
                message.what = 9528;
                message.obj = inputStream;
                mHandler.post(() -> mHandler.sendMessage(message));
                return;
            }
            movieDuration = movie.duration() == 0 ? DEFAULT_DURATION : movie.duration();
            mHandler.post(() -> mHandler.sendEmptyMessage(9527));
        }).start();
    }


    private void setGif() {
        requestLayout();
        play(-1);
    }

    public void setBitmapInputStream(InputStream inputStream) {
        new Thread(() -> {
            try {
                BitmapFactory.Options newOpts = new BitmapFactory.Options();
                // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
                newOpts.inJustDecodeBounds = true;
                byte[] bytes = getBytes(inputStream);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                newOpts.inJustDecodeBounds = false;
                int w = newOpts.outWidth;
                int h = newOpts.outHeight;

                Message message = new Message();
                message.what = 9529;
                message.obj = bitmap;
                mHandler.post(() -> mHandler.sendMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setBitmap(Bitmap bm) {
        if (null != bm) {
            setImageBitmap(bm);
        }
        requestLayout();
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 用数据装
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();   // 关闭流一定要记得。

        return outstream.toByteArray();
    }


    public void setGifResource(int movieResourceId) {
        setGifResource(movieResourceId, null);
    }

    public void setGifResource(final String path, OnPlayListener onPlayListener) {
        movie = Movie.decodeFile(path);
        mOnPlayListener = onPlayListener;
        reset();
        if (movie == null) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            if (bitmap != null) {
                setImageBitmap(bitmap);
                return;
            }
        }
        movieDuration = movie.duration() == 0 ? DEFAULT_DURATION : movie.duration();
        requestLayout();
        if (mOnPlayListener != null) {
            mOnPlayListener.onPlayStart();
        }
    }

    //从新开始播放
    public void playOver() {
        if (movie != null) {
            play(-1);
        }
    }

    //倒叙播放
    public void playReverse() {
        if (movie != null) {
            reset();
            reverse = true;
            if (mOnPlayListener != null) {
                mOnPlayListener.onPlayStart();
            }
            invalidate();
        }
    }

    public void play(int counts) {
        this.counts = counts;
        reset();
        if (mOnPlayListener != null) {
            mOnPlayListener.onPlayStart();
        }
        invalidate();
    }

    private void reset() {
        reverse = false;
        mMovieStart = SystemClock.uptimeMillis();
        mPaused = false;
        hasStart = true;
        mMoviePauseTime = 0;
        offsetTime = 0;
    }

    public void play() {
        if (movie == null)
            return;
        if (hasStart) {
            if (mPaused && mMoviePauseTime > 0) {
                mPaused = false;
                offsetTime = offsetTime + SystemClock.uptimeMillis() - mMoviePauseTime;
                invalidate();
                if (mOnPlayListener != null) {
                    mOnPlayListener.onPlayRestart();
                }
            }
        } else {
            play(-1);
        }
    }

    public void pause() {
        if (movie != null && !mPaused && hasStart) {
            mPaused = true;
            invalidate();
            mMoviePauseTime = SystemClock.uptimeMillis();
            if (mOnPlayListener != null) {
                mOnPlayListener.onPlayPause(true);
            }
        } else {
            if (mOnPlayListener != null) {
                mOnPlayListener.onPlayPause(false);
            }
        }
    }

    private int getCurrentFrameTime() {
        if (movieDuration == 0)
            return 0;
        long now = SystemClock.uptimeMillis() - offsetTime;
        int nowCount = (int) ((now - mMovieStart) / movieDuration);
        if (counts != -1 && nowCount >= counts) {
            hasStart = false;
            if (mOnPlayListener != null) {
                mOnPlayListener.onPlayEnd();
            }
            return endLastFrame ? movieDuration : 0;
        }
        float currentTime = (now - mMovieStart) % movieDuration;
        percent = currentTime / movieDuration;
        if (mOnPlayListener != null && hasStart) {
            BigDecimal mData = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP);
            double f1 = mData.doubleValue();
            f1 = f1 == 0.99 ? 1.0 : f1;
            mOnPlayListener.onPlaying((float) f1);
        }
        return (int) currentTime;
    }

    public void setPercent(float percent) {
        if (movie != null && movieDuration > 0) {
            this.percent = percent;
            movie.setTime((int) (movieDuration * percent));
            invalidateView();
            if (mOnPlayListener != null) {
                mOnPlayListener.onPlaying(percent);
            }
        }

    }

    public boolean isPaused() {
        return this.mPaused;
    }

    public boolean isPlaying() {
        return !this.mPaused && hasStart;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (movie != null) {
            if (!mPaused && hasStart) {
                if (reverse) {
                    movie.setTime(movieDuration - getCurrentFrameTime());
                } else {
                    movie.setTime(getCurrentFrameTime());
                }
                drawMovieFrame(canvas);
                invalidateView();
            } else {
                drawMovieFrame(canvas);
            }
        }
    }

    /**
     * 画出gif帧
     */
    private void drawMovieFrame(Canvas canvas) {
        canvas.save();
        canvas.scale(1 / mScale, 1 / mScale);
        movie.draw(canvas, 0.0f, 0.0f);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (movie != null) {
            int movieWidth = movie.width();
            int movieHeight = movie.height();
            if (widthMode == MeasureSpec.EXACTLY) {
                mScaleW = ((float) movieWidth) / sizeWidth;
            }
            if (heightMode == MeasureSpec.EXACTLY) {
                mScaleH = ((float) movieHeight) / sizeHeight;
            }
            mScale = Math.max(mScaleW, mScaleH);
            setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                    : movieWidth, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                    : movieHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    private void invalidateView() {
        if (mVisible) {
            postInvalidateOnAnimation();
        }
    }

    public int getDuration() {
        if (movie != null) {
            return movie.duration();
        } else return 0;
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        mVisible = screenState == SCREEN_STATE_ON;
        invalidateView();
    }


    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        mVisible = visibility == View.VISIBLE;
        invalidateView();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mVisible = visibility == View.VISIBLE;
        invalidateView();
    }


    public interface OnPlayListener {
        void onPlayStart();

        void onPlaying(@FloatRange(from = 0f, to = 1.0f) float percent);

        void onPlayPause(boolean pauseSuccess);

        void onPlayRestart();

        void onPlayEnd();
    }

}
