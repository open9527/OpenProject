package com.android.custom.pkg.video;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.LogUtils;

import java.util.Locale;

/**
 * @author open_9527
 * Create at 2021/8/13
 **/
public class TextToSpeechPlayer implements LifecycleEventObserver {
    private static final String TAG = "TextToSpeechPlayer";

    private Context mContext;
    private TextToSpeechPlayerListener mTextToSpeechPlayerListener;
    private String mSpeakText = "";
    private TextToSpeech mSpeech = null;

    private TextToSpeechPlayer(Context context) {
        mContext = context;
    }

    public static TextToSpeechPlayer with(Context context) {
        return new TextToSpeechPlayer(context);
    }

    public TextToSpeechPlayer addTextToSpeechPlayerListener(TextToSpeechPlayerListener textToSpeechPlayerListener) {
        mTextToSpeechPlayerListener = textToSpeechPlayerListener;
        return this;
    }

    public TextToSpeechPlayer setSpeakText(String speakText) {
        mSpeakText = speakText;
        return this;
    }

    public TextToSpeechPlayer build(LifecycleOwner owner) {
        setLifecycleOwner(owner);
        createTextToSpeechPlayer();
        return this;
    }


    private void createTextToSpeechPlayer() {
        mSpeech = new TextToSpeech(mContext, new onTTSInitListener(),"");
        mSpeech.setOnUtteranceProgressListener(new onTTSUtteranceListener());
    }

    private void startSpeak() {
        startSpeak(mSpeakText);
    }

    private void startSpeak(String string) {
        if (TextUtils.isEmpty(string)) {
            if (mTextToSpeechPlayerListener != null) {
                mTextToSpeechPlayerListener.onError();
            }
            return;
        }
        // text 需要转成语音的文字
        // queueMode 队列方式：
        // QUEUE_ADD：播放完之前的语音任务后才播报本次内容
        // QUEUE_FLUSH：丢弃之前的播报任务，立即播报本次内容
        // params 设置TTS参数，可以是null。
        // KEY_PARAM_STREAM：音频通道，可以是：STREAM_MUSIC、STREAM_NOTIFICATION、STREAM_RING等
        // KEY_PARAM_VOLUME：音量大小，0-1f
        // utteranceId：当前朗读文本的id
//        Bundle bundle = new Bundle();
//        bundle.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, string);
        mSpeech.speak(string, TextToSpeech.QUEUE_FLUSH, null, string);
        if (mTextToSpeechPlayerListener != null) {
            mTextToSpeechPlayerListener.start();
        }
    }

    class onTTSInitListener implements TextToSpeech.OnInitListener {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                int result = mSpeech.setLanguage(Locale.CHINA);
                mSpeech.setPitch(1.0f);
                mSpeech.setSpeechRate(1.0f);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    if (mTextToSpeechPlayerListener != null) {
                        mTextToSpeechPlayerListener.onError();
                    }
                } else {
                    if (mTextToSpeechPlayerListener != null) {
                        mTextToSpeechPlayerListener.prepared();
                    }
                    startSpeak();
                }
            } else {
                if (mTextToSpeechPlayerListener != null) {
                    mTextToSpeechPlayerListener.onError();
                }
            }
        }
    }


    class onTTSUtteranceListener extends UtteranceProgressListener {
        @Override
        public void onStart(String utteranceId) {
            LogUtils.i(TAG, "onStart: utteranceId=" + utteranceId);
            if (mTextToSpeechPlayerListener != null) {
                mTextToSpeechPlayerListener.start();
            }
        }

        @Override
        public void onStop(String utteranceId, boolean interrupted) {
            LogUtils.i(TAG, "onStop: utteranceId=" + utteranceId);
            super.onStop(utteranceId, interrupted);
        }

        @Override
        public void onDone(String utteranceId) {
            LogUtils.i(TAG, "onDone: utteranceId=" + utteranceId);
            if (mTextToSpeechPlayerListener != null) {
                mTextToSpeechPlayerListener.onCompletion();
            }
            destroy();
        }

        @Override
        public void onError(String utteranceId) {
            LogUtils.i(TAG, "onError: utteranceId=" + utteranceId);
            if (mTextToSpeechPlayerListener != null) {
                mTextToSpeechPlayerListener.onError();
            }
            destroy();
        }
    }


    private void resume() {
        if (mSpeech != null&& !mSpeech.isSpeaking()) {
            startSpeak();
        }
    }

    private void pause() {
        if (mSpeech != null && mSpeech.isSpeaking()) {
            mSpeech.stop();
            mSpeech.shutdown();
        }
    }

    public void destroy() {
        if (mSpeech != null) {
            mSpeech.stop();
            mSpeech.shutdown();
            mSpeech = null;
        }
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
    }

}
