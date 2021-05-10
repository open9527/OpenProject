package com.android.custom.pkg.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.recyclerview.widget.RecyclerView;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.dialog.BaseDialogFragment;
import com.android.open9527.dialog.DialogDataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.open9527.recycleview.layout_manager.WrapContentPickerLayoutManager;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author open_9527
 * Create at 2021/4/19
 **/
public class DateDialog extends BaseDialogFragment {

    private final ObservableInt valueStartYear = new ObservableInt(Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR) - 100);
    private final ObservableInt valueEndYear = new ObservableInt(Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR));
    private final ObservableInt valueYIndex = new ObservableInt(0);
    private final ObservableInt valueMIndex = new ObservableInt(0);
    private final ObservableInt valueDIndex = new ObservableInt(0);

    private final ObservableField<IDialog> valueIDialog = new ObservableField<>();

    public final ObservableArrayList<BaseBindingCell> valueYCells = new ObservableArrayList<>();
    public final ObservableArrayList<BaseBindingCell> valueMCells = new ObservableArrayList<>();
    public final ObservableArrayList<BaseBindingCell> valueDCells = new ObservableArrayList<>();

    private WrapContentPickerLayoutManager mYearManager;
    private WrapContentPickerLayoutManager mMonthManager;
    private WrapContentPickerLayoutManager mDayManager;

    private RecyclerView mYearView;
    private RecyclerView mMonthView;
    private RecyclerView mDayView;

    private static DateDialog newInstance(@NonNull Context context) {
        return new DateDialog(context);
    }

    public DateDialog(@NonNull Context context) {
        super(context);
        initData();
    }

    @Override
    public int bindTheme() {
        return R.style.AdaptiveHeightDialog;
    }

    @Override
    public int bindLayout() {
        return R.layout.date_dialog;
    }

    @Override
    public void setWindowStyle(@NonNull Window window) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.windowAnimations = R.style.SlideBottomDialogAnimationStyle;
        window.setAttributes(layoutParams);
    }


    @Override
    public DialogDataBindingConfig getDataBindingConfig() {
        return new DialogDataBindingConfig().addBindingParam(BR.dialog, this)
                .addBindingParam(BR.yLayoutManager,
                        mYearManager = new WrapContentPickerLayoutManager.Builder(mActivity)
                                .setOnPickerListener(pickerListener)
                        .build()
                )
                .addBindingParam(BR.yAdapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.mLayoutManager, mMonthManager = new WrapContentPickerLayoutManager.Builder(getContext())
                        .setOnPickerListener(pickerListener)
                        .build())
                .addBindingParam(BR.mAdapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.dLayoutManager, mDayManager = new WrapContentPickerLayoutManager.Builder(getContext())
                        .build())
                .addBindingParam(BR.dAdapter, new BaseBindingCellListAdapter<>());
    }

    @Override
    public void initView(@NonNull BaseDialogFragment dialog, @NonNull View contentView) {
        super.initView(dialog, contentView);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        mYearView = contentView.findViewById(R.id.rv_date_year);
        mMonthView = contentView.findViewById(R.id.rv_date_month);
        mDayView = contentView.findViewById(R.id.rv_date_day);


        if (valueYIndex.get() == 0 && valueMIndex.get() == 0 && valueDIndex.get() == 0) {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            setYear(calendar.get(Calendar.YEAR));
            setMonth(calendar.get(Calendar.MONTH) + 1);
            setDay(calendar.get(Calendar.DAY_OF_MONTH));
        }
        setPickIndex(mYearView, valueYIndex.get());
        setPickIndex(mMonthView, valueMIndex.get());
        setPickIndex(mDayView, valueDIndex.get());
    }

    private void initData() {
        // 年份
        valueYCells.clear();
        for (int i = valueStartYear.get(); i <= valueEndYear.get(); i++) {
            valueYCells.add(new DatePickCell(i + " " + "年"));
        }

        // 月份
        valueMCells.clear();
        for (int i = 1; i <= 12; i++) {
            valueMCells.add(new DatePickCell(i + " " + "月"));
        }

        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int day = calendar.getActualMaximum(Calendar.DATE);
        // 天数
        valueDCells.clear();
        for (int i = 1; i <= day; i++) {
            valueDCells.add(new DatePickCell(i + " " + "日"));
        }
    }

    private void setPickIndex(RecyclerView recyclerView, int index) {
        recyclerView.post(() -> {
            recyclerView.scrollToPosition(index);
            pickerListener.onPicked(recyclerView, index);
        });


    }


    public View.OnClickListener cancelClick = v -> {
        dismiss();
    };
    public View.OnClickListener confirmClick = v -> {
        IDialog iDialog = valueIDialog.get();
        if (iDialog != null) {
            iDialog.onSelected(valueStartYear.get() + mYearManager.getPickedPosition(), mMonthManager.getPickedPosition() + 1, mDayManager.getPickedPosition() + 1);
        }
        dismiss();
    };

    public WrapContentPickerLayoutManager.OnPickerListener pickerListener = new WrapContentPickerLayoutManager.OnPickerListener() {
        @Override
        public void onPicked(RecyclerView recyclerView, int position) {
            // 获取这个月最多有多少天
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.set(valueStartYear.get() + mYearManager.getPickedPosition(), mMonthManager.getPickedPosition(), 1);
            int day = calendar.getActualMaximum(Calendar.DATE);
            if (valueDCells.size() != day) {
                valueDCells.clear();
                for (int i = 1; i <= day; i++) {
                    valueDCells.add(new DatePickCell(i + " " + "日"));
                }
            }
        }
    };


    public class DatePickCell extends BaseBindingCell<DatePickCell> {

        public final ObservableField<String> valueDate = new ObservableField<>();

        public DatePickCell(String date) {
            super(R.layout.date_pick_cell);
            valueDate.set(date);
        }

        @Override
        public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
            holder.addBindingParam(BR.cell, this);
        }

        @Override
        public String getUUID() {
            return valueDate.get();
        }
    }

    public interface IDialog {
        void onSelected(int year, int month, int day);
    }

    public static DateDialog with(Context context) {
        return DateDialog.newInstance(context);
    }

    public DateDialog setStartYear(int year) {
        if (year > 0) {
            valueStartYear.set(year);
        }
        return this;
    }

    public DateDialog setEndYear(int year) {
        if (year > 0) {
            valueEndYear.set(year);
        }
        return this;
    }

    public DateDialog setYear(int year) {
        int index = year - valueStartYear.get();
        if (index < 0) {
            index = 0;
        } else if (index > valueYCells.size() - 1) {
            index = valueYCells.size() - 1;
        }
        valueYIndex.set(index);
        return this;
    }

    public DateDialog setMonth(int month) {
        int index = month - 1;
        if (index < 0) {
            index = 0;
        } else if (index > valueMCells.size() - 1) {
            index = valueMCells.size() - 1;
        }
        valueMIndex.set(index);
        return this;
    }

    public DateDialog setDay(int day) {
        int index = day - 1;
        if (index < 0) {
            index = 0;
        } else if (index > valueDCells.size() - 1) {
            index = valueDCells.size() - 1;
        }
        valueDIndex.set(index);
        return this;
    }


    public DateDialog setListener(@NonNull IDialog listener) {
        valueIDialog.set(listener);
        return this;
    }

    public DateDialog showDateDialog() {
        show();
        return this;
    }
}
