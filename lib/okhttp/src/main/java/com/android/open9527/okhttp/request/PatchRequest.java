package com.android.open9527.okhttp.request;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.model.HttpMethod;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class PatchRequest extends BodyRequest<PatchRequest> {

    public PatchRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    protected String getRequestMethod() {
        return HttpMethod.PATCH.toString();
    }
}