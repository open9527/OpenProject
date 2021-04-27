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

//    static gradlePluginVersion = '3.2.1'
    static gradlePluginVersion = '3.6.4'
    static androidxVersion = '1.0.0'
    static androidxLifecycle = '2.2.0'
    //OkHttp3.12.x分支支持Android2.3+(API级别9+)和Java 7+。
    //java.lang.ExceptionInInitializerError
    static okHttp3 = '3.12.12'
    static glide = '4.11.0'

    static smartRefresh = '2.0.3'


    static depConfig = [
            plugin_gradle                  : "com.android.tools.build:gradle:$gradlePluginVersion",

            api_gradle_plugin              : "com.blankj:api-gradle-plugin:1.5",

            kotlin_gradle_plugin           : "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion",

            androidx_core_ktx              : "androidx.core:core-ktx:$kotlinKtx",
            kotlin_stdlib_jdk7             : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion",

//            androidx_appcompat             : "androidx.appcompat:appcompat:1.2.0",
            androidx_appcompat             : "androidx.appcompat:appcompat:1.3.0-beta01",
            androidx_material              : "com.google.android.material:material:1.2.1",
            androidx_constraint            : "androidx.constraintlayout:constraintlayout:2.0.4",
            androidx_recyclerview          : "androidx.recyclerview:recyclerview:1.2.0",
            androidx_viewpager2            : "androidx.viewpager2:viewpager2:$androidxVersion",
            androidx_cardview              : "androidx.cardview:cardview:$androidxVersion",
            androidx_multidex              : "androidx.multidex:multidex:2.0.0",

            utilcode                       : "com.blankj:utilcodex:1.30.5",

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

            persistent_cookie_jar          : "com.github.franmontiel:PersistentCookieJar:v1.0.1",

            "photo_view"                   : "com.github.chrisbanes:PhotoView:2.3.0"

    ]
}