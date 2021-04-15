package com.android.open9527.common.widget.textview;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class FoldTextView extends AppCompatTextView {

    private final String TAG = "FoldTextView";
    private float maxLine = 2;
    /* 是否折叠，默认折叠 */
    private boolean isFold = true;
    /* 初始化缓存标识 */
    private boolean flag;
    private String ellipsize_end_text = "...";
    private String foldText = "全部";
    private String expandText = "收起";
    //    private TextViewDelegate mDelegate;
    private CharSequence text;
    private int mPaintColor = Color.RED;

    public FoldTextView(Context context) {
        this(context, null);
    }

    public FoldTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FoldTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        mDelegate = new TextViewDelegate(this);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (TextUtils.isEmpty(text)) {
            //判断空字符，有时候会有空字符进来，但是其实内容都已正确测量，会导致获取索引时超出数组
            super.setText(text, type);
        } else {
            //一定要先调用，否则后面的getLayout().getLineCount()测量不准确
            super.setText(text, type);
            //首次加载缓存，否则会有异步加载问题，内容会闪烁
            if (!flag) {
                getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        getViewTreeObserver().removeOnPreDrawListener(this);
                        flag = true;
                        setContent(text, type);
                        return true;
                    }
                });
            } else {
                setContent(text, type);
            }
        }
    }

    private void setContent(CharSequence val, BufferType type) {
        text = val;
        //得到文本内容的总长度
        //float textWidth = getPaint().measureText(val.toString());
        //测量实际view的宽度
        //int width = getMeasuredWidth();
        if (getLayout() != null) {
            //计算内容实际行数，自带其实有getCountLine方法，但是改方法有bug，末尾如果是空格，如果刚好快到指定行数了，然后末尾加入空格，此时改方法不起作用
            //float line = textWidth / width;
            float line = getLayout().getLineCount();
            //ELLIPSIZE_END模式来处理
            if (line > maxLine) {
                //一行可以容纳多少个字
                //int num = Math.round(width / getTextSize());
                //在最大行的末尾减去6个字符
                //int end = (int)(num * maxLine - 6);
                SpannableStringBuilder builder = new SpannableStringBuilder();
                //如果折叠，截取字符，否则加载原文
                if (isFold) {
                    int end = getLayout().getLineEnd((int) maxLine - 1) - ellipsize_end_text.length() - foldText.length();
                    Log.e(TAG, "end " + end);
                    if (end > 0) {
                        CharSequence title = val.subSequence(0, end);
                        builder.append(title);
                    }
                    builder.append(ellipsize_end_text);
                    builder.append(foldText);
                    builder.setSpan(new FoldSpan(), builder.length() - foldText.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                } else {
                    builder.append(val);
                    builder.append(expandText);
                    builder.setSpan(new FoldSpan(), builder.length() - expandText.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
                //不设置点击不生效
                setMovementMethod(LinkMovementMethod.getInstance());
                super.setText(builder, type);
            } else {
                super.setText(val, type);
            }
            Log.e(TAG, "maxLine " + maxLine + " line " + line + " isFold " + isFold + " text " + val);
        } else {
            super.setText(val, type);
        }
    }

    /**
     * 需要在setText前调用
     */
    public void setMaxLine(int line) {
        maxLine = line;
    }

    public void setPaintColor(@ColorInt int color) {
        mPaintColor = color;
    }

//    public void addOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
//        this.mDelegate.addOnMarkerClickListener(onMarkerClickListener);
//    }
//
//    public void setContent(String content) {
//        this.mDelegate.setContent(content == null ? "" : content);
//    }
//
//    public String getContent() {
//        return this.mDelegate.getContent();
//    }
//
//    //TODO:注意在setContent()方法之前调用
//    public void setForegroundColor(int foregroundColor) {
//        this.mDelegate.setForegroundColor(foregroundColor);
//    }


    private class FoldSpan extends ClickableSpan {

        @Override
        public void onClick(View view) {
            isFold = !isFold;
            setText(text);
            Log.e(TAG, "click " + isFold + " text " + getText());
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.setColor(mPaintColor);
            // 设置下划线 true显示、false不显示
            paint.setUnderlineText(false);
        }
    }

}
