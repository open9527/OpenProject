package com.android.open9527.image.pkg;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.event.ProtectedUnPeekLiveData;
import com.android.open9527.common.event.UnPeekLiveData;


/**
 * @author open_9527
 * Create at 2021/2/1
 **/
public class SharedElementViewModel extends ViewModel {

    //    private final UnPeekLiveData<LocationChangeEvent> locationChangeEventLiveData = new UnPeekLiveData<>();
    private final UnPeekLiveData<LocationChangeEvent> locationChangeEventLiveData = new UnPeekLiveData.Builder<LocationChangeEvent>().setAllowNullValue(false).create();

    public ProtectedUnPeekLiveData<LocationChangeEvent> getLocationChangeEventLiveData() {
        return locationChangeEventLiveData;
    }

    public void requestLocationChangeEventLiveData(@NonNull LocationChangeEvent locationChangeEvent) {
        locationChangeEventLiveData.postValue(locationChangeEvent);
    }

}
