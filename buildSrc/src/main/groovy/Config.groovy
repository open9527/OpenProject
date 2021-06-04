class Config {

    static applicationId = 'com.android.open9527'
    static appName = 'open9527'

    static compileSdkVersion = 30
    static buildToolsVersion = '30.0.3'
    static minSdkVersion = 21
    static targetSdkVersion = 30
    static versionCode = 1_000_000
    static versionName = '1.0.0'// E.g. 1.9.72 => 1,009,072

    static kotlinVersion = '1.3.50'
    static kotlinKtx = '1.3.2'

    static gradlePluginVersion = '3.6.4'
    static androidxVersion = '1.0.0'
    static androidxLifecycle = '2.2.0'
    //OkHttp3.12.x分支支持Android2.3+(API级别9+)和Java 7+。
    //java.lang.ExceptionInInitializerError
    static okHttp3 = '3.12.12'
    static glide = '4.12.0'
    static glideTransformations = '4.3.0'

    static smartRefresh = '2.0.3'
    static umSdk = '7.1.4'
    static exoplayer = '2.14.0'


    static depConfig = [

            feature_launcher_app            : new DepConfig(true, true, ":feature:launcher:app"),
//
            feature_okhttp_app              : new DepConfig(true, true, ":feature:okhttp:app"),
            feature_okhttp_export           : new DepConfig(true, true, ":feature:okhttp:export"),
            feature_okhttp_pkg              : new DepConfig(true, true, ":feature:okhttp:pkg"),

            feature_permission_app          : new DepConfig(true, true, ":feature:permission:app"),
            feature_permission_export       : new DepConfig(true, true, ":feature:permission:export"),
            feature_permission_pkg          : new DepConfig(true, true, ":feature:permission:pkg"),

            feature_wanandroid_app          : new DepConfig(true, true, ":feature:wanandroid:app"),
            feature_wanandroid_export       : new DepConfig(true, true, ":feature:wanandroid:export"),
            feature_wanandroid_pkg          : new DepConfig(true, true, ":feature:wanandroid:pkg"),

            feature_image_app               : new DepConfig(true, true, ":feature:image:app"),
            feature_image_export            : new DepConfig(true, true, ":feature:image:export"),
            feature_image_pkg               : new DepConfig(true, true, ":feature:image:pkg"),

            feature_annotation_app          : new DepConfig(true, true, ":feature:annotation:app"),
            feature_annotation_export       : new DepConfig(true, true, ":feature:annotation:export"),
            feature_annotation_pkg          : new DepConfig(true, true, ":feature:annotation:pkg"),

            feature_appmanager_app          : new DepConfig(true, true, ":feature:appmanager:app"),
            feature_appmanager_export       : new DepConfig(true, true, ":feature:appmanager:export"),
            feature_appmanager_pkg          : new DepConfig(true, true, ":feature:appmanager:pkg"),

            feature_webview_app             : new DepConfig(true, true, ":feature:webview:app"),
            feature_webview_export          : new DepConfig(true, true, ":feature:webview:export"),
            feature_webview_pkg             : new DepConfig(true, true, ":feature:webview:pkg"),
//
            feature_custom_app              : new DepConfig(true, true, ":feature:custom:app"),
            feature_custom_export           : new DepConfig(true, true, ":feature:custom:export"),
            feature_custom_pkg              : new DepConfig(true, true, ":feature:custom:pkg"),

            feature_video_app               : new DepConfig(true, true, ":feature:video:app"),
            feature_video_export            : new DepConfig(true, true, ":feature:video:export"),
            feature_video_pkg               : new DepConfig(true, true, ":feature:video:pkg"),

            feature_kotlin_app              : new DepConfig(false, true, ":feature:kotlin:app"),
            feature_kotlin_export           : new DepConfig(false, true, ":feature:kotlin:export"),
            feature_kotlin_pkg              : new DepConfig(false, true, ":feature:kotlin:pkg"),


            plugin_gradle                   : new DepConfig(pluginPath: "com.android.tools.build:gradle:$gradlePluginVersion"),
            plugin_api                      : new DepConfig(pluginPath: "com.blankj:api-gradle-plugin:1.5", pluginId: "com.blankj.api"),
            plugin_kotlin                   : new DepConfig(pluginPath: "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"),

            lib_page                        : new DepConfig(true, true, ":lib:page"),
            lib_base                        : new DepConfig(true, true, ":lib:base"),
            lib_common                      : new DepConfig(true, true, ":lib:common"),
            lib_dialog                      : new DepConfig(true, true, ":lib:dialog"),
            lib_permission                  : new DepConfig(true, true, ":lib:permission"),
            lib_crash                       : new DepConfig(true, true, ":lib:crash"),
            lib_recycleview                 : new DepConfig(true, true, ":lib:recycleview"),
            lib_okhttp                      : new DepConfig(true, true, ":lib:okhttp"),
            lib_glide                       : new DepConfig(true, true, ":lib:glide"),
            lib_webview                     : new DepConfig(true, true, ":lib:webview"),
            lib_router                      : new DepConfig(true, true, ":lib:router"),
            lib_umeng                       : new DepConfig(true, true, ":lib:umeng"),
            lib_filter                      : new DepConfig(true, true, ":lib:filter"),
            lib_video                       : new DepConfig(true, true, ":lib:video"),

            apt_annotation                  : new DepConfig(true, true, ":apt:annotation"),
            apt_compiler                    : new DepConfig(true, true, ":apt:compiler"),

            lib_kt_page                     : new DepConfig(false, true, ":lib_kt:page"),
            lib_kt_base                     : new DepConfig(false, true, ":lib_kt:base"),
            lib_kt_common                   : new DepConfig(false, true, ":lib_kt:common"),
            lib_kt_permission               : new DepConfig(false, true, ":lib_kt:permissions"),


            kotlin_core_ktx                 : new DepConfig("androidx.core:core-ktx:$kotlinKtx"),
            kotlin_stdlib_jdk7              : new DepConfig("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"),

            androidx_appcompat              : new DepConfig("androidx.appcompat:appcompat:1.2.0"),
            androidx_material               : new DepConfig("com.google.android.material:material:1.3.0"),
            androidx_constraint             : new DepConfig("androidx.constraintlayout:constraintlayout:2.0.4"),
            androidx_recyclerview           : new DepConfig("androidx.recyclerview:recyclerview:1.2.0"),
            androidx_viewpager2             : new DepConfig("androidx.viewpager2:viewpager2:$androidxVersion"),
            androidx_cardview               : new DepConfig("androidx.cardview:cardview:$androidxVersion"),
            androidx_multidex               : new DepConfig("androidx.multidex:multidex:2.0.1"),

            utilcode                        : new DepConfig("com.blankj:utilcodex:1.30.5"),

            okhttp3                         : new DepConfig("com.squareup.okhttp3:okhttp:$okHttp3"),
            gson                            : new DepConfig("com.google.code.gson:gson:2.8.7"),

            glide                           : new DepConfig("com.github.bumptech.glide:glide:$glide"),
            glide_compiler                  : new DepConfig("com.github.bumptech.glide:compiler:$glide"),
            glide_transformations           : new DepConfig("jp.wasabeef:glide-transformations:$glideTransformations"),


            androidx_lifecycle_common       : new DepConfig("androidx.lifecycle:lifecycle-common:$androidxLifecycle"),
            androidx_lifecycle_common_java8 : new DepConfig("androidx.lifecycle:lifecycle-common-java8:$androidxLifecycle"),
            androidx_lifecycle_runtime      : new DepConfig("androidx.lifecycle:lifecycle-runtime:$androidxLifecycle"),

            //包含了 ViewModel 和 LiveData
            androidx_lifecycle_extensions   : new DepConfig("androidx.lifecycle:lifecycle-extensions:$androidxLifecycle"),
            //指明使用ViewModel
            androidx_lifecycle_viewmodel    : new DepConfig("androidx.lifecycle:lifecycle-viewmodel:$androidxLifecycle"),
            //指明使用LiveData
            androidx_lifecycle_livedata     : new DepConfig("androidx.lifecycle:lifecycle-livedata:$androidxLifecycle"),
            androidx_lifecycle_livedata_core: new DepConfig("androidx.lifecycle:lifecycle-livedata-core:$androidxLifecycle"),

            //refresh
            smart_refresh_kernel            : new DepConfig("com.scwang.smart:refresh-layout-kernel:$smartRefresh"),
            smart_refresh_header_classics   : new DepConfig("com.scwang.smart:refresh-header-classics:$smartRefresh"),
            smart_refresh_footer_classics   : new DepConfig("com.scwang.smart:refresh-footer-classics:$smartRefresh"),

            //UMShareSdk 必选 common, asms, share-core
            umsdk_common                    : new DepConfig("com.umeng.umsdk:common:9.3.6"),
            umsdk_asms                      : new DepConfig("com.umeng.umsdk:asms:1.2.1"),
            umsdk_share_core                : new DepConfig("com.umeng.umsdk:share-core:$umSdk"),
            //分享面板功能，可选
            umsdk_share_board               : new DepConfig("com.umeng.umsdk:share_board:$umSdk"),
            //QQ官方SDK依赖库
            umsdk_qq                        : new DepConfig("com.tencent.tauth:qqopensdk:3.51.2"),
            umsdk_share_qq                  : new DepConfig("com.umeng.umsdk:share-qq:$umSdk"),
            //微信官方SDK依赖库
            umsdk_wx                        : new DepConfig("com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.6.5"),
            umsdk_share_wx                  : new DepConfig("com.umeng.umsdk:share-wx:$umSdk"),
            //新浪微博官方SDK依赖库
            umsdk_sina                      : new DepConfig("com.sina.weibo.sdk:core:10.10.0:openDefaultRelease@aar"),
            umsdk_share_sina                : new DepConfig("com.umeng.umsdk:share-sina:$umSdk"),

            banner_viewpager                : new DepConfig("com.github.zhpanvip:BannerViewPager:3.5.1"),

            persistent_cookie_jar           : new DepConfig("com.github.franmontiel:PersistentCookieJar:v1.0.1"),

            photo_view                      : new DepConfig("com.github.chrisbanes:PhotoView:2.3.0"),

            tbssdk                          : new DepConfig("com.tencent.tbs.tbssdk:sdk:43993"),

            // 视频播放组件 exoplayer
            exoplayer_core                  : new DepConfig("com.google.android.exoplayer:exoplayer-core:$exoplayer"),
            exoplayer_dash                  : new DepConfig("com.google.android.exoplayer:exoplayer-dash:$exoplayer"),
            exoplayer_ui                    : new DepConfig("com.google.android.exoplayer:exoplayer-ui:$exoplayer"),
            exoplayer_hls                   : new DepConfig("com.google.android.exoplayer:exoplayer-hls:$exoplayer"),
            exoplayer_smoothstreaming       : new DepConfig("com.google.android.exoplayer:exoplayer-smoothstreaming:$exoplayer"),
            exoplayer_rtsp                  : new DepConfig("com.google.android.exoplayer:exoplayer-rtsp:$exoplayer"),
            exoplayer_rtmp                  : new DepConfig("com.google.android.exoplayer:extension-rtmp:$exoplayer"),


    ]
}