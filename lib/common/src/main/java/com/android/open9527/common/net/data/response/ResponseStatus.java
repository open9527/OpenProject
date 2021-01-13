package com.android.open9527.common.net.data.response;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public class ResponseStatus {

    private String responseCode = "";
    private boolean success = true;
    private Enum source = ResultSource.NETWORK;

    public ResponseStatus() {
    }

    public ResponseStatus(String responseCode, boolean success) {
        this.responseCode = responseCode;
        this.success = success;
    }

    public ResponseStatus(String responseCode, boolean success, Enum source) {
        this(responseCode, success);
        this.source = source;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public Enum getSource() {
        return source;
    }
}
