class Config {

    static applicationId = 'com.android.open9527'
    static appName = 'open9527'

    static compileSdkVersion = 30
    static buildToolsVersion = '30.0.2'
    static minSdkVersion = 21
    static targetSdkVersion = 30
    static versionCode = 1_000_000
    static versionName = '1.0.0'// E.g. 1.9.72 => 1,009,072

    static gradlePluginVersion = '3.6.3'
    static androidxVersion = '1.0.0'
    static androidxLifecycle = '2.2.0'
    //OkHttp3.12.x分支支持Android2.3+(API级别9+)和Java 7+。
    //java.lang.ExceptionInInitializerError
    static okHttp3 = '3.12.12'
    static glide = '4.11.0'

    static smartRefresh = '2.0.3'

    static depConfig = [
            plugin_gradle                  : "com.android.tools.build:gradle:$gradlePluginVersion",

            androidx_appcompat             : "androidx.appcompat:appcompat:1.2.0",
            androidx_material              : "com.google.android.material:material:1.2.1",
            androidx_constraint            : "androidx.constraintlayout:constraintlayout:2.0.4",
//            androidx_recyclerview          : "androidx.recyclerview:recyclerview:1.1.0",
            androidx_recyclerview          : "androidx.recyclerview:recyclerview:1.2.0-beta01",
            androidx_viewpager2            : "androidx.viewpager2:viewpager2:$androidxVersion",
            androidx_cardview              : "androidx.cardview:cardview:$androidxVersion",
            androidx_multidex              : "androidx.multidex:multidex:2.0.0",

            utilcode                       : "com.blankj:utilcodex:1.30.4",

            okhttp3                        : "com.squareup.okhttp3:okhttp:$okHttp3",
            gson                           : 'com.google.code.gson:gson:2.8.6',

            glide                          : "com.github.bumptech.glide:glide:$glide",
            glide_compiler                 : "com.github.bumptech.glide:compiler:$glide",
            glide_transformations          : "jp.wasabeef:glide-transformations:4.3.0",



            androidx_lifecycle_common      : "androidx.lifecycle:lifecycle-common:$androidxLifecycle",
            androidx_lifecycle_common_java8: "androidx.lifecycle:lifecycle-common-java8:$androidxLifecycle",
            androidx_lifecycle_runtime     : "androidx.lifecycle:lifecycle-runtime:$androidxLifecycle",
            androidx_lifecycle_viewmodel   : "androidx.lifecycle:lifecycle-viewmodel:$androidxLifecycle",

            smart_refresh_kernel           : "com.scwang.smart:refresh-layout-kernel:$smartRefresh",
            smart_refresh_header_classics  : "com.scwang.smart:refresh-header-classics:$smartRefresh",
            smart_refresh_footer_classics  : "com.scwang.smart:refresh-footer-classics:$smartRefresh",


            banner_viewpager               : "com.github.zhpanvip:BannerViewPager:3.4.0",

            persistent_cookie_jar          : "com.github.franmontiel:PersistentCookieJar:v1.0.1"
            //implementation  'com.scwang.smart:refresh-layout-kernel:2.0.3'      //核心必须依赖
            //implementation  'com.scwang.smart:refresh-header-classics:2.0.3'    //经典刷新头
            //implementation  'com.scwang.smart:refresh-header-radar:2.0.3'       //雷达刷新头
            //implementation  'com.scwang.smart:refresh-header-falsify:2.0.3'     //虚拟刷新头
            //implementation  'com.scwang.smart:refresh-header-material:2.0.3'    //谷歌刷新头
            //implementation  'com.scwang.smart:refresh-header-two-level:2.0.3'   //二级刷新头
            //implementation  'com.scwang.smart:refresh-footer-ball:2.0.3'        //球脉冲加载
            //implementation  'com.scwang.smart:refresh-footer-classics:2.0.3'    //经典加载

    ]
}