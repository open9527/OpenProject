package com.android.custom.pkg.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import java.io.IOException;

/**
 * @author open_9527
 * Create at 2021/8/13
 **/
public class VoicePlayer implements LifecycleEventObserver {
    private static final String TAG = "VoicePlayer";

    public static final int PLAY_STOP = 100;//停止播放
    private int sleep = 500;

    private Context mContext;
    private VoicePlayerListener mVoicePlayerListener;
    private String mVoiceUrl;
    private MediaPlayer mMediaPlayer;
    private boolean isPlaying = false;
    private int mDuration;

    private VoicePlayer(Context context) {
        mContext = context;
    }

    public static VoicePlayer with(Context context) {
        return new VoicePlayer(context);
    }

    public VoicePlayer addVoicePlayerListener(VoicePlayerListener voicePlayerListener) {
        mVoicePlayerListener = voicePlayerListener;
        return this;
    }

    public VoicePlayer setVoiceUrl(String url) {
        mVoiceUrl = url;
        return this;
    }

    public VoicePlayer build(LifecycleOwner owner) {
        setLifecycleOwner(owner);
        createMediaPlayer();
        return this;
    }


    private void createMediaPlayer() {
//        raw
//        mMediaPlayer = MediaPlayer.create(mContext, rawId);

//        assets
//        AssetFileDescriptor fileDescriptor = mContext.getAssets().openFd(assetsName);
//        mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
//        mMediaPlayer.prepare();
//
//        文件
//        mMediaPlayer.setDataSource(targetFile.getAbsolutePath());
//        mMediaPlayer.prepare();

        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(mVoiceUrl);
            mMediaPlayer.prepareAsync();

            //播放完成自动停止
            mMediaPlayer.setOnCompletionListener(mediaPlayer -> {
                if (mVoicePlayerListener != null) {
                    mVoicePlayerListener.onCompletion(mediaPlayer);
                    destroy();
                }
            });

            //准备完毕 自动播放
            mMediaPlayer.setOnPreparedListener(mediaPlayer -> {
                mediaPlayer.start();
                mHandler.postDelayed(mRunnable, sleep);
                mDuration = mMediaPlayer.getDuration();
                if (mVoicePlayerListener != null) {
                    mVoicePlayerListener.prepared();
                    mVoicePlayerListener.start();
                }
            });

            //播放错误监听
            mMediaPlayer.setOnErrorListener((mp, what, extra) -> {
                if (mVoicePlayerListener != null) {
                    mVoicePlayerListener.onError(mp, what, extra);
                }
                if (mMediaPlayer != null) {
                    destroy();
                    mHandler.removeCallbacks(mRunnable);
                }
                return false;
            });

            //网络缓冲监听
            mMediaPlayer.setOnBufferingUpdateListener((mp, percent) -> {
                if (mVoicePlayerListener != null)
                    mVoicePlayerListener.onBufferingUpdate(mp, percent);
            });
            //调整进度监听
            mMediaPlayer.setOnSeekCompleteListener(mp -> {
                if (mVoicePlayerListener != null)
                    mVoicePlayerListener.onSeekComplete(mp);
            });

        } catch (IOException e) {
            e.printStackTrace();
            destroy();
        }
        this.isPlaying = (mMediaPlayer != null);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mVoicePlayerListener != null && mMediaPlayer != null && isPlaying())
                mVoicePlayerListener.onSeekBarProgress(mMediaPlayer.getCurrentPosition());
            mHandler.postDelayed(mRunnable, sleep);
        }
    };


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //停止前发送进度.
            if (mVoicePlayerListener != null && mMediaPlayer != null && isPlaying())
                mVoicePlayerListener.onSeekBarProgress(mMediaPlayer.getCurrentPosition());

            if (msg.what == PLAY_STOP && mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }
            return false;
        }
    });


    //继续播放
    private void resume() {
        if (mMediaPlayer != null && (!isPlaying())) {
            this.isPlaying = true;
            mHandler.postDelayed(mRunnable, sleep);
            mMediaPlayer.start();
        }
    }


    //暂停
    private void pause() {
        this.isPlaying = false;
        mHandler.removeCallbacks(mRunnable);
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            if (mVoicePlayerListener != null)
                mVoicePlayerListener.pause();
        }
    }


    //停止播放
    public void destroy() {
        isPlaying = false;
        mDuration = 0;
        if (mMediaPlayer != null) {
            Message message = new Message();
            message.what = PLAY_STOP;
            mHandler.sendMessage(message);
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            if (mVoicePlayerListener != null)
                mVoicePlayerListener.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }


    /**
     * 是否正在运行
     *
     * @return true 正在运行，false停止运行
     */
    public boolean isRunning() {
        return (mMediaPlayer != null);
    }

    /**
     * 是否在播放中
     *
     * @return true 正在播放，false 停止播放
     */
    public boolean isPlaying() {
        return this.isPlaying;
    }

    /**
     * 获取当前播放位置
     *
     * @return 当前播放位置值
     */
    public int getCurrentPosition() {
        if (mMediaPlayer == null) {
            return 0;
        }
        return mMediaPlayer.getCurrentPosition();
    }

    /**
     * 播放文件的时长
     *
     * @return 文件时长
     */
    public int getDuration() {
        if (mMediaPlayer == null) {
            return mDuration;
        }

        return mMediaPlayer.getDuration();
    }

    /**
     * 左右声道大小
     *
     * @param leftVolume  左声道大小 0 - 1
     * @param rightVolume 右声道大小 0 - 1
     */
    public void setVolume(float leftVolume, float rightVolume) {
        if (mMediaPlayer != null) {
            mMediaPlayer.setVolume(leftVolume, rightVolume);
        }
    }

    /**
     * 设置唤醒方式 需要在清单文件AndroidManifest.xml中添加权限 <uses-permission android:name="android.permission.WAKE_LOCK" />
     *
     * @param context 上下文
     * @param mode    唤醒模式
     */
    public void setWakeMode(Context context, int mode) {
        if (mMediaPlayer != null)
            mMediaPlayer.setWakeMode(context, mode);
    }

    /**
     * 播放时不熄屏
     *
     * @param screenOn true 不息屏，false 息屏
     */
    public void setScreenOnWhilePlaying(boolean screenOn) {
        if (mMediaPlayer != null)
            mMediaPlayer.setScreenOnWhilePlaying(screenOn);
    }

    /**
     * 指定播放位置 毫秒
     *
     * @param msec 要播放的值
     */
    public void seekTo(int msec) {
        if (mMediaPlayer != null)
            mMediaPlayer.seekTo(msec);
    }

    /**
     * 是否循环播放
     *
     * @param looping true 循环播放，false 不循环
     */
    public void setLooping(boolean looping) {
        if (mMediaPlayer != null)
            mMediaPlayer.setLooping(looping);
    }


    /**
     * 设置生命管控（自动回调生命周期方法）
     */
    public void setLifecycleOwner(LifecycleOwner owner) {
        if (owner != null) {
            owner.getLifecycle().addObserver(this);
        }
    }


    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event) {
            case ON_RESUME:
                onResume();
                break;
            case ON_PAUSE:
                onPause();
                break;
            case ON_DESTROY:
                onDestroy();
                break;
            default:
                break;
        }
    }


    private void onResume() {
        resume();
    }


    private void onPause() {
        pause();
    }

    private void onDestroy() {
        destroy();
        mHandler.removeCallbacks(mRunnable);
    }


}
