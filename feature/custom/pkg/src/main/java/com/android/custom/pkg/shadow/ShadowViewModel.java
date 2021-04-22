package com.android.custom.pkg.shadow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.binding.drawables.DrawablesBindingAdapter;
import com.android.open9527.common.widget.image.RoundImageType;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class ShadowViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Shadow");

    public final ObservableField<Integer> valueShapeMode = new ObservableField<>(DrawablesBindingAdapter.ShapeMode.RECTANGLE);

    public final ObservableField<String> valueUrl = new ObservableField<>("https://fx.storage.shmedia.tech/95271a5e140347a396ac57b265eeca0d.jpg");
    public final ObservableField<String> valueRound = new ObservableField<>(RoundImageType.ROUND_TYPE_ROUND);

    public final ObservableField<String> valueCornerLeftTop = new ObservableField<>(RoundImageType.CORNER_TYPE_LEFT_TOP);
    public final ObservableField<String> valueCornerLeft = new ObservableField<>(RoundImageType.CORNER_TYPE_LEFT);
    public final ObservableField<String> valueCornerTop = new ObservableField<>(RoundImageType.CORNER_TYPE_TOP);
    public final ObservableField<String> valueCornerRightTop = new ObservableField<>(RoundImageType.CORNER_TYPE_RIGHT_TOP);
    public final ObservableField<String> valueCornerRight = new ObservableField<>(RoundImageType.CORNER_TYPE_RIGHT);
    public final ObservableField<String> valueCornerBottomLeft = new ObservableField<>(RoundImageType.CORNER_TYPE_BOTTOM_LEFT);
    public final ObservableField<String> valueCornerBottomRight = new ObservableField<>(RoundImageType.CORNER_TYPE_BOTTOM_RIGHT);
    public final ObservableField<String> valueCornerBottom = new ObservableField<>(RoundImageType.CORNER_TYPE_BOTTOM);

    public final ObservableField<String> valueCircle = new ObservableField<>(RoundImageType.ROUND_TYPE_CIRCLE);


}
