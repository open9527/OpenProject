package com.android.kotlin.pkg.net

import okhttp3.Cookie
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


/**
 *@author   open_9527
 *Create at 2021/8/17
 **/
private class OkhttpTrustManager : X509TrustManager {

    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}

private val cookieStore by lazy {
    HashMap<HttpUrl?, List<Cookie>>()
}

// 就在这里实例化okhttp 其他的东西用到即可添加.用不到注释掉也无大碍.
val OkHttpClientUtils: OkHttpClient by lazy {
    OkHttpClient().newBuilder().apply {
        this.connectTimeout(40, TimeUnit.SECONDS)
        this.readTimeout(40, TimeUnit.SECONDS)
        this.writeTimeout(40, TimeUnit.SECONDS)
        // 如果后台需要用到cookie 就加上就可以

//        cookieJar(object : CookieJar {
//            override fun loadForRequest(url: HttpUrl): List<Cookie> {
//                val cookies: List<Cookie>? = cookieStore.get(CloudUrlIinterface.url.toHttpUrlOrNull())
//                if (cookies == null) {
//                    // println("没加载到cookie")
//                }
//                return cookies ?: ArrayList()
//
//            }
//
//            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
//                cookieStore.put(url, cookies)
//                cookieStore.put(CloudUrlIinterface.url.toHttpUrlOrNull(), cookies)
//                /*for (cookie in cookies) {
//                    println("cookice name:" + cookie.name)
//                    println("cookece path:" + cookie.path)
//                }*/
//            }
//        })
        ///
        val trustAllCerts: Array<TrustManager> = arrayOf(OkhttpTrustManager())
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        hostnameVerifier(HostnameVerifier { _: String?, _: SSLSession? -> true })
    }.build()

}



