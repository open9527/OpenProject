# router
# *表示只是保持该包下的类名，而子包下的类名还是会被混淆；
# **星表示把本包和所含子包下的类名都保持；
-keep class com.open9527.router.**

# AOP
-adaptclassstrings
-keepattributes InnerClasses, EnclosingMethod, Signature, *Annotation*
-keepnames @org.aspectj.lang.annotation.Aspect class * {
    public <methods>;
}

# okhttp
#-keep class com.android.open9527.okhttp.**{ *; }
#-keepclassmembernames class com.android.open9527.okhttp.** {
#    <fields>;
#}

# BannerViewPager
-keep class androidx.recyclerview.widget.**{*;}
-keep class androidx.viewpager2.widget.**{*;}

# persistent_cookie_jar
-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# VideoView
#-keep com.android.open9527.video.** { *; }
#-dontwarn com.android.open9527.video.**