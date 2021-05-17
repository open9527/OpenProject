class Config {

    static applicationId = 'com.android.open9527'
    static appName = 'open9527'

    static compileSdkVersion = 30
    static buildToolsVersion = '30.0.3'
    static minSdkVersion = 21
    static targetSdkVersion = 30
    static versionCode = 1_000_000
    static versionName = '1.0.0'// E.g. 1.9.72 => 1,009,072

    static kotlinVersion = '1.4.10'
    static kotlinKtx = '1.3.2'

    static gradlePluginVersion = '3.6.4'
    static androidxVersion = '1.0.0'
    static androidxLifecycle = '2.2.0'
    //OkHttp3.12.x分支支持Android2.3+(API级别9+)和Java 7+。
    //java.lang.ExceptionInInitializerError
    static okHttp3 = '3.12.12'
    static glide = '4.11.0'

    static smartRefresh = '2.0.3'
    static umSdk = '7.1.4'
    static exoplayer = '2.14.0'


    static depConfig = [
            plugin_gradle                   : "com.android.tools.build:gradle:$gradlePluginVersion",

            api_gradle_plugin               : "com.blankj:api-gradle-plugin:1.5",

            kotlin_gradle_plugin            : "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion",

            androidx_core_ktx               : "androidx.core:core-ktx:$kotlinKtx",
            kotlin_stdlib_jdk7              : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion",

//            androidx_appcompat             : "androidx.appcompat:appcompat:1.2.0",
            androidx_appcompat              : "androidx.appcompat:appcompat:1.3.0-rc01",
            androidx_material               : "com.google.android.material:material:1.2.1",
            androidx_constraint             : "androidx.constraintlayout:constraintlayout:2.0.4",
            androidx_recyclerview           : "androidx.recyclerview:recyclerview:1.2.0",
            androidx_viewpager2             : "androidx.viewpager2:viewpager2:$androidxVersion",
            androidx_cardview               : "androidx.cardview:cardview:$androidxVersion",
            androidx_multidex               : "androidx.multidex:multidex:2.0.0",

            utilcode                        : "com.blankj:utilcodex:1.30.5",

            okhttp3                         : "com.squareup.okhttp3:okhttp:$okHttp3",
            gson                            : 'com.google.code.gson:gson:2.8.6',

            glide                           : "com.github.bumptech.glide:glide:$glide",
            glide_compiler                  : "com.github.bumptech.glide:compiler:$glide",
            glide_transformations           : "jp.wasabeef:glide-transformations:4.3.0",


            androidx_lifecycle_common       : "androidx.lifecycle:lifecycle-common:$androidxLifecycle",
            androidx_lifecycle_common_java8 : "androidx.lifecycle:lifecycle-common-java8:$androidxLifecycle",
            androidx_lifecycle_runtime      : "androidx.lifecycle:lifecycle-runtime:$androidxLifecycle",

            //包含了 ViewModel 和 LiveData
            androidx_lifecycle_extensions   : "androidx.lifecycle:lifecycle-extensions:$androidxLifecycle",
            //指明使用ViewModel
            androidx_lifecycle_viewmodel    : "androidx.lifecycle:lifecycle-viewmodel:$androidxLifecycle",
            //指明使用LiveData
            androidx_lifecycle_livedata     : "androidx.lifecycle:lifecycle-livedata:$androidxLifecycle",
            androidx_lifecycle_livedata_core: "androidx.lifecycle:lifecycle-livedata-core:$androidxLifecycle",

            //refresh
            smart_refresh_kernel            : "com.scwang.smart:refresh-layout-kernel:$smartRefresh",
            smart_refresh_header_classics   : "com.scwang.smart:refresh-header-classics:$smartRefresh",
            smart_refresh_footer_classics   : "com.scwang.smart:refresh-footer-classics:$smartRefresh",

            //UMShareSdk 必选 common, asms, share-core
            umsdk_common                    : "com.umeng.umsdk:common:9.3.6",
            umsdk_asms                      : "com.umeng.umsdk:asms:1.2.1",
            umsdk_share_core                : "com.umeng.umsdk:share-core:$umSdk",
            //分享面板功能，可选
            umsdk_share_board               : "com.umeng.umsdk:share_board:$umSdk",
            //QQ官方SDK依赖库
            umsdk_qq                        : "com.tencent.tauth:qqopensdk:3.51.2",
            umsdk_share_qq                  : "com.umeng.umsdk:share-qq:$umSdk",
            //微信官方SDK依赖库
            umsdk_wx                        : "com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.6.5",
            umsdk_share_wx                  : "com.umeng.umsdk:share-wx:$umSdk",
            //新浪微博官方SDK依赖库
            umsdk_sina                      : "com.sina.weibo.sdk:core:10.10.0:openDefaultRelease@aar",
            umsdk_share_sina                : "com.umeng.umsdk:share-sina:$umSdk",


            banner_viewpager                : "com.github.zhpanvip:BannerViewPager:3.4.0",

            persistent_cookie_jar           : "com.github.franmontiel:PersistentCookieJar:v1.0.1",

            photo_view                      : "com.github.chrisbanes:PhotoView:2.3.0",

            tbssdk                          : "com.tencent.tbs.tbssdk:sdk:43993",

            //   //视频播放组件
            //    api 'com.google.android.exoplayer:exoplayer-core:2.10.4'
            //    api 'com.google.android.exoplayer:exoplayer-dash:2.10.4'
            //    api 'com.google.android.exoplayer:exoplayer-ui:2.10.4'


            exoplayer_core                  : "com.google.android.exoplayer:exoplayer-core:$exoplayer",
            exoplayer_dash                  : "com.google.android.exoplayer:exoplayer-dash:$exoplayer",
            exoplayer_ui                    : "com.google.android.exoplayer:exoplayer-ui:$exoplayer",
            exoplayer_hls                   : "com.google.android.exoplayer:exoplayer-hls:$exoplayer",
            exoplayer_smoothstreaming       : "com.google.android.exoplayer:exoplayer-smoothstreaming:$exoplayer",
            exoplayer_rtsp                  : "com.google.android.exoplayer:exoplayer-rtsp:$exoplayer",
            exoplayer_rtmp                  : "com.google.android.exoplayer:extension-rtmp:$exoplayer",


    ]
}