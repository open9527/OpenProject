package com.android.open9527.common.widget.textview.timeline;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

import com.blankj.utilcode.util.StringUtils;
import com.android.open9527.common.widget.textview.timeline.delegate.TextViewDelegate;
import com.android.open9527.common.widget.textview.timeline.marker.AtMarker;
import com.android.open9527.common.widget.textview.timeline.marker.Marker;
import com.android.open9527.common.widget.textview.timeline.marker.TagMarker;

import java.util.List;

public class TimelineEditText extends AppCompatEditText {

    private TextViewDelegate mDelegate;
    private int mLastSelectionStart, mLastSelectionEnd;

    public TimelineEditText(Context context) {
        super(context);
        initialize();
    }

    public TimelineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        mDelegate = new TextViewDelegate(this);
        mLastSelectionStart = 0;
        mLastSelectionEnd = 0;

        this.addOnMarkerClickListener(mOnMarkerClickListener);
        this.setOnKeyListener(mOnKeyListener);

    }

    public void addOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        this.mDelegate.addOnMarkerClickListener(onMarkerClickListener);
    }

    public void setContent(String content) {
        this.mDelegate.setContent(content);

        mLastSelectionStart = 0;
        mLastSelectionEnd = 0;
    }


    //TODO:注意在setContent()方法之前调用
    public void setForegroundColor(int foregroundColor) {
        this.mDelegate.setForegroundColor(foregroundColor);
    }

    public String getContent() {
        return this.mDelegate.getContent();
    }

    @NonNull
    public List<Marker> getMarkerList() {
        return this.mDelegate.getMarkerList();
    }

    public boolean replaceMarker(Marker oldMarker, Marker newMarker) {
        return this.mDelegate.replaceMarker(oldMarker, newMarker);
    }

    public boolean replaceMarker(int start, int end, Marker marker) {
        MarkerRange range = expandMarkerRange(new MarkerRange(start, end), true, true);
        if (range == null) {
            return false;
        }

        return this.mDelegate.replaceMarker(range.start, range.end, marker);
    }

    /**
     * 点中标签后，弹出对话框去选择
     */
    private OnMarkerClickListener mOnMarkerClickListener = (View v, Marker marker) -> {
        /*
        int start, end;
        Editable editableText = getEditableText();
        if (editableText == null || (start = editableText.getSpanStart(marker)) < 0) {
            return;
        }
        end = editableText.getSpanEnd(marker);

        setSelection(start, end);
         */
    };

    /**
     * 按键事件
     * 1）"退格"：前一个字母是marker且没被全选中，则选中marker
     */
    private OnKeyListener mOnKeyListener = (View v, int keyCode, KeyEvent event) -> {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            return handleDelKeyEvent(event);
        }

        return false;
    };

    private boolean handleDelKeyEvent(KeyEvent event) {
        if (event.getAction() != KeyEvent.ACTION_DOWN) {
            return false;
        }

        MarkerRange currRange = new MarkerRange(this.getSelectionStart(), this.getSelectionEnd());
        if (currRange.start == currRange.end) {
            currRange.start = currRange.start - 1;
        }

        MarkerRange nextRange = expandMarkerRange(currRange, true, true);
        if (nextRange != null && currRange != nextRange) {
            setSelection(nextRange.start, nextRange.end);
            return true;
        }

        return false;
    }


    /**
     * 录入事件
     * 1）"#"：弹出标签选择框
     * 2）"@"：弹出@选择框
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (lengthAfter > 0) {
            String inputString = String.valueOf(text).substring(start, start + lengthAfter);
            if (StringUtils.equals("@", inputString)) {
                handleAtInput(text, start, lengthBefore, lengthAfter);

            } else if (StringUtils.equals("#", inputString)) {
                handlePoundInput(text, start, lengthBefore, lengthAfter);
            }
        }
    }

    //弹出选择人物对话框，如果选中人物，则增加 marker；如果没选人物，则加个@字母
    private boolean handleAtInput(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        MarkerRange range = expandMarkerRange(
                new MarkerRange(getSelectionStart(), getSelectionEnd()), true, true);
        if (range == null) {
            return false;
        }

        //这里弹出对话框选个人物

        if (range.start == range.end) {
            range.start = range.start - 1;
        }

        Marker marker = AtMarker.create("0003", "新增AT");
        mDelegate.replaceMarker(range.start, range.end, marker);

        return true;
    }

    //弹出选择话题对话框，如果选中话题，则增加 marker；如果没选话题，则加个#字母
    private boolean handlePoundInput(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        MarkerRange range = expandMarkerRange(
                new MarkerRange(getSelectionStart(), getSelectionEnd()), true, true);
        if (range == null) {
            return false;
        }

        //这里弹出对话框选个话题

        if (range.start == range.end) {
            range.start = range.start - 1;
        }

        Marker marker = TagMarker.create("0002", "新增TAG");
        mDelegate.replaceMarker(range.start, range.end, marker);

        return true;
    }


    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);

        MarkerRange currRange = new MarkerRange(selStart, selEnd);
        MarkerRange nextRange = null;

        if (selStart == selEnd) {
            //点击，选中光标位置的目标
            nextRange = expandMarkerRange(currRange, true, true);

        } else if (selStart < mLastSelectionStart && selEnd == mLastSelectionEnd) {
            //选择左边界左移，增加左侧选项
            nextRange = expandMarkerRange(currRange, true, false);

        } else if (selStart > mLastSelectionStart && selEnd == mLastSelectionEnd) {
            //选择左边界右移，减少左侧选项
            nextRange = reduceMarkerRange(currRange, true, false);

        } else if (selStart == mLastSelectionStart && selEnd > mLastSelectionEnd) {
            //选择右边界右移，增加右侧选项
            nextRange = expandMarkerRange(currRange, false, true);

        } else if (selStart == mLastSelectionStart && selEnd < mLastSelectionEnd) {
            //选择右边界左移，减少右侧选项
            nextRange = reduceMarkerRange(currRange, false, true);
        }

        if (nextRange != null && currRange != nextRange) {
            setSelection(nextRange.start, nextRange.end);

            mLastSelectionStart = nextRange.start;
            mLastSelectionEnd = nextRange.end;

        } else {
            mLastSelectionStart = currRange.start;
            mLastSelectionEnd = currRange.end;
        }
    }

    /**
     * 根据marker扩展边界，以保重marker全部处于边界中
     *
     * @param range       给定边界
     * @param expandLeft  向左扩充
     * @param expandRight 向右扩充
     * @return 成功：新的边界，失败null
     */
    private MarkerRange expandMarkerRange(MarkerRange range, boolean expandLeft, boolean expandRight) {
        Editable editableText = getEditableText();
        if (editableText == null) {
            return null;
        }

        int rangeStart = range.start, rangeEnd = range.end;
        Marker[] markers = editableText.getSpans(rangeStart, rangeEnd, Marker.class);
        if (markers != null && markers.length > 0) {
            for (Marker marker : markers) {
                int markerStart = editableText.getSpanStart(marker);
                int markerEnd = editableText.getSpanEnd(marker);

                //向左扩充，且rangeStart落在range中间
                if (expandLeft && markerStart < rangeStart && rangeStart < markerEnd) {
                    rangeStart = markerStart;
                }

                //向右扩充，且rangeEnd落在range中间
                if (expandRight && markerStart < rangeEnd && rangeEnd < markerEnd) {
                    rangeEnd = markerEnd;
                }
            }
        }

        if (rangeStart != range.start || rangeEnd != range.end) {
            return new MarkerRange(rangeStart, rangeEnd);
        }
        return range;
    }

    /**
     * 根据marker缩小边界，以保重marker全部处于边界中
     *
     * @param range       给定边界
     * @param reduceLeft  左侧缩小
     * @param reduceRight 右侧缩小
     * @return 成功：新的边界，失败null
     */
    private MarkerRange reduceMarkerRange(MarkerRange range, boolean reduceLeft, boolean reduceRight) {
        Editable editableText = getEditableText();
        if (editableText == null) {
            return null;
        }

        int rangeStart = range.start, rangeEnd = range.end;
        Marker[] markers = editableText.getSpans(rangeStart, rangeEnd, Marker.class);
        if (markers != null && markers.length > 0) {
            for (Marker marker : markers) {
                if (reduceLeft) {
                    int markerStart = editableText.getSpanStart(marker);
                    if (markerStart < rangeStart) {
                        rangeStart = editableText.getSpanEnd(marker);
                    }
                }

                if (reduceRight) {
                    int markerEnd = editableText.getSpanEnd(marker);
                    if (markerEnd > rangeEnd) {
                        rangeEnd = editableText.getSpanStart(marker);
                    }
                }
            }
        }

        if (rangeStart != range.start || rangeEnd != range.end) {
            return new MarkerRange(rangeStart, rangeEnd);
        }
        return range;
    }


    class MarkerRange {
        int start;
        int end;

        MarkerRange(int start, int end) {
            this.start = start;
            this.end = end;

            if (this.start > this.end) {
                this.start = this.start + this.end;
                this.end = this.start - this.end;
                this.start = this.start - this.end;
            }
        }
    }

}
