package com.android.open9527.common.net.data.response;


/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 * eg:
 * 专用于数据层返回结果给 domain 层或 ViewModel 用
 * 规定不在数据层通过 liveData 返回结果
 **/

public class DataResult<T> {

    private T mEntity;
    private ResponseStatus mResponseStatus;

    public DataResult(T entity, ResponseStatus responseStatus) {
        mEntity = entity;
        mResponseStatus = responseStatus;
    }

    public T getResult() {
        return mEntity;
    }

    public ResponseStatus getResponseStatus() {
        return mResponseStatus;
    }

    public interface Result<T> {

        void onResult(DataResult<T> dataResult);
    }
}
