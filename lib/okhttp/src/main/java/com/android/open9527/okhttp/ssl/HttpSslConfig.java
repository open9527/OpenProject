package com.android.open9527.okhttp.ssl;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class HttpSslConfig {

    private final SSLSocketFactory sSLSocketFactory;
    private final X509TrustManager trustManager;

    HttpSslConfig(SSLSocketFactory sSLSocketFactory, X509TrustManager trustManager) {
        this.sSLSocketFactory = sSLSocketFactory;
        this.trustManager = trustManager;
    }

    public SSLSocketFactory getsSLSocketFactory() {
        return sSLSocketFactory;
    }

    public X509TrustManager getTrustManager() {
        return trustManager;
    }
}